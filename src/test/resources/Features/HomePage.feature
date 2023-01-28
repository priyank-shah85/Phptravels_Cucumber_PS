Feature: Opening main page and verifying login to application as  Customer.

In order to perform successful login
As a customer user
I want to login with valid credentials

Background:
	Given user navigates to "https://phptravels.net/login"
	When user validates the page heading as "Login"
	And user accepts all cookies

@smoke @sanity @regression @Critical
Scenario: Successful login with valid credentials.
	And user enters Email as "user@phptravels.com"
	And user enters Password as "demouser"
	And user clicks on Login button
	Then user redirects to Dashboard with title "Dashboard - Best Halal Trip"

@regression @Major
Scenario Outline: Verify that user cannot login with invalid credentials.
	And user enters Email as "<email>"
	And user enters Password as "<password>"
	And user clicks on Login button
	Then user will see validation as "Wrong credentials. try again!"
	
	Examples:
	| email 							| password 	|
	| a@a.com 						| abcdefg 	|
	| ps@phptravels.com 	| demouser 	|
	| user@phptravels.com | test@123 	|

@regression @Minor
Scenario: Verify that Remember Me checkbox remain unchecked on clicking Reset Password link.
	And user clicks on Reset Password link
	And user closes the pop up
	Then user will see Remember Me checkbox as unchecked
	
@sanity @regression @Major
Scenario: Reset Password with valid email address.
	And user clicks on Reset Password link
	And user will see placeholder in Email field as "your@email.com"
	And user enters the email as "user1@phptravels.com"
	And user clicks on Reset button
	Then user will see confirmation as "Password reset check your mailbox"
	
@regression @Minor
Scenario Outline: Verify all link from page header.
	Then user will see the "<link>" on header
	
	Examples:
	| link 						|
	| Hotels 					|
	| Tours 					|
	| Transfers 			|
	| Visa 						|
	| Flights					|
	|	FAQ							|
	| How To Book			|
	| Privacy Policy	|
	| Become Supplier	|
	| Supplier Login	|
	| Optional				|
	| Careers And Jobs|
	
@regression @Minor
Scenario: Use of Go top button
	And user scrolls till Go top button is visible
	And user clicks on Go top button
	Then user scrolls till top of the page
	
