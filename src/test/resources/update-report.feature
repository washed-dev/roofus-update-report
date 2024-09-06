#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#Case Zero: Smoke Test
#Case One: Tag one image with one tag
#Case Two: Tag one image with multiple tags
#Case Three: Untag one image from one tag
#Case Four: Untag one image from multiple tags
#Case Five: Untag one image from multiple tags but not all tags
#Case Five: Tag multiple images with one tag
#Case Seven: Tag multiple images with multiple tags
#Case Eight: Tag multiple images with different tags
#Case Nine: Tag and untag one image
#Case Ten: Tag and untag multiple images
#Case Eleven: Tag no images
@tag
Feature: Update Report
  This API will be used to tag and untag images in RoofLink reports given an inspection id, image id, and tag id

  @addtag
  Scenario: Tag one image
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes
	
	@removetag
	Scenario: Untag one image
	@
  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
