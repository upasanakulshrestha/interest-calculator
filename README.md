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

use daily to read the daily end-of-day message which returns amount with interest for day (assumed the interest as 0.01) // still under progress,


TODO:
1. save the daily end of day details under AccountHistory table 
2. on month end day for each records present under AccountHistory, return the last amount present in the table for all accounts.
