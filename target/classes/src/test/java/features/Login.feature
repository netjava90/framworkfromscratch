Feature: Application Login
Scenario: Login with valid credentials
Given Open any Browser
And Navigate to Login page
When User enters username as "akselaymar@gmail.com" and password as "Amchiche5" into the fields
And User Clicks on Login button
Then Verify user is able to successfully login
