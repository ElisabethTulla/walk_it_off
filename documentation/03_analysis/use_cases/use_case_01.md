# Use Case 01: Log in

## Overview
**ID:** UC-01
**Name:** Log in
**Primary Actor:** User
**Brief Description:** Login to application to access account

## Preconditions
- User must be registered
- User must know email and password

## Postconditions
- Access to the user account is granted
- The application can be used as intended by the user

## Main Success Scenario
1. The entered email is registered and found in the database
2. The entered password matches the correct password in the database
3. The login button leads to the AccountView
4. The user can now access all features of the application

## Alternative Flows

### Alternative Flow 1: Enter different email
**Condition:** If the entered email isn't found in the database
1. try a different email
2. Return to step 1 in main flow

### Alternative Flow 2: Enter different password
**Condition:** If the entered password doesn't match the correct password in the database
1. try a different password
2. Return to step 2 in main flow

## Exception Flows

### Exception Flow 1: User not registered
**Condition:** If the user doesn't yet have an account
1. Unknown email is entered
2. Unknown password is entered
3. Use case ends in failure

## Special Requirements
- User must be registered already
- User must know the correct email and password

## Frequency of Use
Everytime a User uses the application


