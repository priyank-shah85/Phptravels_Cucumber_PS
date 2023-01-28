Feature: Verify various scenarios for Hotel booking.
In order to book a hotel
I want to
Search for the hotel and make booking

Background:
	Given user navigates to "https://phptravels.net/login"
	When user validates the page heading as "Login"
	And user accepts all cookies
	And user enters Email as "pshah@phptravels.com"
	And user enters Password as "pshahuser"
	And user clicks on Login button
	And user clicks on Hotels link
	
@regression @Minor
Scenario: User redirects to Hotels page.
	Then user redirects to "Search Hotels" page
	And user can see page heading as "SEARCH FOR BEST HOTELS"
	
@smoke @sanity @regression @Critical
Scenario: User can search for list of hotels.
	And user can see page heading as "SEARCH FOR BEST HOTELS"
	And user can see City Name field title as "Search by City"
	And user clicks on City Name field
	And user can see "Please enter 3 or more characters" text as field value
	And user enters "Dub" in City Name field
	And user selects "Dubai,United Arab Emirates" from suggestions
	And user selects next month's start date as Checkin date
	And user selects Checkout date as 3 days after Checkin date
	And user opens Travellers entry box
	And user selects "2" rooms
	And user selects "4" adults
	And user selects "2" children
	And user selects first child age as "6" and second child age as "8"
	And user selects Nationality as "India"
	And user clicks on Search icon
	Then user redirects to search results page with heading as "SEARCH HOTELS IN DUBAI"
	And user can see list of hotels as search results
	
@regression @Major
Scenario Outline: User can see exact number of required characters while entering City Name.
	And user clicks on City Name field
	And user enters "<keyword>" in City Name field
	And user can see "<message>" text as field value
	
	Examples:
	|keyword 	|message 														|
	|Du				|Please enter 1 or more characters	|
	|					|Please enter 3 or more characters	|
	|D				|Please enter 2 or more characters	|
	
@sanity @regression @Critical
Scenario: User can verify hotel details like name, image, description & price in list.
	And user clicks on City Name field
	And user enters "Dubai" in City Name field
	And user selects "Dubai,United Arab Emirates" from suggestions
	And user selects next month's start date as Checkin date
	And user selects Checkout date as 3 days after Checkin date
	And user clicks on Search icon
	Then user can see "Jumeirah Beach Hotel" under search results
	And user can see desired image for "Jumeirah Beach Hotel" hotel
	And user can see "5" stars rating for "Jumeirah Beach Hotel" hotel
	And user can see starting price of "USD 72.60" of "3 Nights" for "Jumeirah Beach Hotel" hotel
	
@smoke @sanity @regression @Critical
Scenario Outline: User can reach to hotel details page by clicking on Details button.
	And user clicks on City Name field
	And user enters "<keyword>" in City Name field
	And user selects "<selection>" from suggestions
	And user clicks on Search icon
	And user clicks on Details button for "<hotel name>" hotel
	Then user can see "<hotel name>" name under "Hotel Details" tab
	
	Examples:
	|keyword 	|selection 									|hotel name 					|
	|Dubai		|Dubai,United Arab Emirates	|Jumeirah Beach Hotel |
	|Istan		|Istanbul,Turkey						|Alzer Hotel Istanbul	|
	|Sing			|Singapore,Singapore				|Rendezvous Hotels		|
	
