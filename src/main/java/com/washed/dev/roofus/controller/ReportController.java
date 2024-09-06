package com.washed.dev.roofus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washed.dev.roofus.to.RelatePhotosRequest;
import com.washed.dev.roofus.to.UpdateReportRequest;
// adding in a comment to test out pull request
@RestController
public class ReportController {

	@Value("${token}")
	private String token;
	@Value("${rooflinkRoot}")
	private String rooflinkRoot;
	private RestTemplate restTemplate = getRestTemplate();

	@GetMapping("/test")
	public ResponseEntity<String> testEndpoint() {
		return testShit();
	}
	
	//Tomorrow we make this request and update a report
	@PostMapping("/update-report/{job}")
	public ResponseEntity<String> updateReport(@RequestParam String jobId, @RequestBody RelatePhotosRequest relatePhotosRequest) {
		//If report doesn't exist, create one
		
		//If report does exist, complete the required updates
		//Create http client
		HttpEntity<RelatePhotosRequest> requestEntity = new HttpEntity<RelatePhotosRequest>(relatePhotosRequest);
		String updateReportURI = updateReportURI(relatePhotosRequest.getLineItemId());
		System.out.println(updateReportURI.toString());
		//Upsert report
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(requestEntity.getBody()));
		} catch(JsonProcessingException e) {
			System.err.println("error");
		}
		String reesponse = restTemplate.exchange(updateReportURI, HttpMethod.PATCH, requestEntity, String.class).getBody();
		//Return response
		return new ResponseEntity<String>(reesponse, HttpStatus.OK);
	}
	
//	private RelatePhotosRequest relatePhotosRequest(UpdateReportRequest updateReportRequest) {
//		RelatePhotosRequest relatePhotosRequest = new RelatePhotosRequest();
//		ArrayList<String> photos = new ArrayList<String>();
//		photos.add("20365817");
//		relatePhotosRequest.setPhotos(photos);
//		return relatePhotosRequest;
//	}
	
	private String updateReportURI(String lineItemId) {
		return new StringBuilder().append(rooflinkRoot).append("jobs/inspections/lineitems/").append(lineItemId).append("/relate_photos/").toString();
//		return new DefaultUriBuilderFactory().builder().scheme().build(rooflinkRoot, "jobs/inspections/lineitems", 
//				"/3650800", "/relate_photos");//.build();
	}

	@PostMapping("/update-report")
	public ResponseEntity<String> testShit() {
		// Given
		RelatePhotosRequest relatePhotosRequest = new RelatePhotosRequest();
		ArrayList<String> photos = new ArrayList<String>();
		photos.add("20041540");
		relatePhotosRequest.setPhotos(photos);
		relatePhotosRequest.setLineItemId("3650800");
		//When
		return updateReport("", relatePhotosRequest);
	}
	
	private RestTemplate getRestTemplate() {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add((request, body, execution) -> {
			HttpHeaders headers = request.getHeaders();
			headers.add("Authorization", "Bearer " + token);
			headers.setContentType(MediaType.APPLICATION_JSON);
//			headers.add("Content-Type", "application-json");
			return execution.execute(request, body);
		});
		RestTemplate restTemplate = new RestTemplate(new JdkClientHttpRequestFactory());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}
}
