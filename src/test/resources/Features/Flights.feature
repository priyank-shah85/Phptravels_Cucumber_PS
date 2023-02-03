Feature: temp one

@temp
Scenario: temp one
	And user opens Admin portal "https://phptravels.net/admin" in new tab
	And user login with "admin@phptravels.com" and "demoadmin" credentials
	And admin user expands Hotels module
	And admin user clicks on Hotels sub-module
	And admin user clicks on Hotels option
	And admin user clicks on SEARCH link
	And admin user enters "beach" as search keyword
	And admin user clicks on GO button
	And admin user noted down Stars and Discount for record with "Name" as "Jumeirah Beach Hotel"