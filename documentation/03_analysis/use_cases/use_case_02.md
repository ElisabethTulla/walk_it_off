# Use Case 02: Register

## Overview
**ID:** UC-02
**Name:** Register
**Primary Actor:** User
**Brief Description:** Registers a new User in order to than access the application via login.

## Preconditions
- User must have email
- User must choose a valid password

## Postconditions
- User is registered in the database
- The login will grant access to the application

## Main Success Scenario
1. The Hyperlink "register new user" will lead to the registration form
2. The user fills out the form
3. The password is valid
4. The user is now registered

## Alternative Flows

### Alternative Flow 1: Not a valid password
**Condition:** The password doesn't match the conditions for a valid password
1. Try a different password that fulfils the requirements
2. Return to step X in main flow

## Exception Flows

### Exception Flow 1: User doesn't fill out the form correctly
**Condition:** User doesn't fill out all textfields
1. The application doesn't have all the data required to create and register a new user
2. User can't be registered
3. Use case ends in failure

## Special Requirements
- User must fill out the registration form
- User must choose a valid password

## Frequency of Use
Whenever a new User wants to access the application for the first time.

