Feature: On successful login as a customer, verifying the Dashboard.
I want to verify different elements from Dashboard.

Background:
	Given user navigates to "https://phptravels.net/login"
	When user validates the page heading as "Login"
	And user accepts all cookies
	And user enters Email as "user@phptravels.com"
	And user enters Password as "demouser"
	And user clicks on Login button

@smoke @sanity @regression @Critical
Scenario: Verifying the welcome text.
	Then user will see welcome text as "Hi, Demo Welcome Back"

@sanity @regression @Major
Scenario: Verifying the Wallet Balance against Admin portal.
	And user sees "Wallet Balance" flex card
	And user opens Admin portal "https://phptravels.net/admin" in new tab
	And user login with "admin@phptravels.com" and "demoadmin" credentials
	And admin user expands Accounts module
	And admin user clicks on Customers sub-module
	And admin user clicks on SEARCH link
	And admin user enters "user@phptravels.com" as search keyword
	And admin user selects "Email" as a field to perform search
	And admin user clicks on GO button
	And admin user noted down Currency and Balance for record with "Email" as "user@phptravels.com"
	And user closes the Admin portal and move back to Customer portal
	Then user will see same Currency and Balance under Wallet Balance flex card

@Sanity @regression @Critical
Scenario: User can update own profile;
	And user clicks on My Profile option
	And user updates the First Name
	And user updates the Last Name
	And user updates the Phone
	And user updates the Postal Code
	And user updates the Address
	And user updates the Address 2
	And user clicks on Update Profile button
	Then user can see "Profile updated successfully." message with updated values
	
