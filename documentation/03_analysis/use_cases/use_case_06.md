# Use Case 06: Log Activity

## Overview
**ID:** UC-06
**Name:** Log Activity
**Primary Actor:** User
**Brief Description:** Logs Activity such as steps or kilometers to the database

## Preconditions
- User must be registered
- User must be logged in

## Postconditions
- User has logged an Activity to the database
- The logged Activity may have contributed to completing a Challenge and unlocking an Achievement.

## Main Success Scenario
1. The button "log Activity" will lead to the Activity form
2. The user chooses whether he/she wants to enter steps or kilometers
3. The user enters the value he/she wants to log
4. The user successfully logged the Activity

## Alternative Flows

### Alternative Flow 1: User puts wrong format in textfield
**Condition:** The User chose steps as the Activity but puts a decimal number in the textfield
1. delete wrong number format or change the Activity to Kms
2. put in the right number format for the chosen Activity
3. Return to step X in main flow

## Exception Flows

### Exception Flow 1: User doesn't fill out the textfield
**Condition:** User doesn't fill out the textfield
1. The application doesn't have all the data required to log the Activity
2. Activity doesn't get saved to the database
3. Use case ends in failure

## Special Requirements
- User must choose the type of Activity
- User must fill out the textfield with the correct value format

## Frequency of Use
Whenever a User wants to log steps or kilometers as Activity

