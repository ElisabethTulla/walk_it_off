# Use Case 07: Enter Challenge

## Overview
**ID:** UC-07
**Name:** Enter Challenge
**Primary Actor:** User
**Brief Description:** User participates in Challenge

## Preconditions
- User must be registered
- User must be logged in
- User must have the first Achievement, which gets unlocked by registering

## Postconditions
- User has entered a Challenge
- User can now log Activity to contribute to completing the Challenge and unlocking an Achievement.

## Main Success Scenario
1. The button "Enter Challenge" will lead to the ChallengeView
2. The user chooses a Challenge from the Challenge table
3. The user types the ChallengeID into the textfield and clicks the button "enter Challenge"
4. The user successfully entered the Challenge

## Alternative Flows

### Alternative Flow 1: don't enter a Challenge
**Condition:** The user doesn't want to enter any of the Challenges from the table
1. User can create a new Challenge with the form in the ChallengeView
2. User fills out the form, loads RewardAchievementId into the form and clicks button "create Challenge"
3. Return to step 1 in main flow

## Exception Flows

### Exception Flow 1: Wrong ChallengeID
**Condition:** User put in a non-existent ChallengeID into the textfield
1. The Challenge doesn't exist
2. The User can't be entered into the Challenge
3. Use case ends in failure

## Special Requirements
- User must choose a Challenge from the table
- User must fill out the textfield with an available ChallengeID

## Frequency of Use
Whenever a User wants to enter a Challenge.

