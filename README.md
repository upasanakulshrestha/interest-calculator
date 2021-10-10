# interest-calculator
Still under development
technologies used Java , SpringBoot, WebFlux, Postgre

Setup:
Create database calculator
Schema.sql will create the tables.

Run the application
open http://localhost:8080/swagger-ui/index.html

There are api to create and fetch account details
use /create to create the account
can fetch the account based on accountId and bsb

use /dailyRequest to read the daily end-of-day message which returns amount with interest for day (assumed the interest as 0.01) 
save the daily end of day details under AccountHistory table only for accounts present in account_details table


TODO:

1. on month end day (assuming coming from JMS with updated amount for eachAccount) for each records present under AccountHistory, return the last amount present in the table for all accounts.
2. validations on account creation
3. (Also send the JMS message)
