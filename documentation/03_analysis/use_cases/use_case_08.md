# Use Case 08: Create Challenge

## Overview
**ID:** UC-08
**Name:** Create Challenge
**Primary Actor:** User
**Brief Description:** User creates a new Challenge

## Preconditions
- User must be registered
- User must be logged in

## Postconditions
- User has created a Challenge (and a new Achievement)
- User can now enter the new Challenge and unlocking a new Achievement.

## Main Success Scenario
1. The button "Enter Challenge" will lead to the ChallengeView
2. The user chooses to create new Challenge
3. The user fills in the textfields and loads the new AchievementId 
4. The user clicks the button "create Challenge"
5. The user has successfully created a new Challenge (and Achievement)

## Exception Flows

### Exception Flow 1: AchievementId doesn't load in
**Condition:** User didn't fill out the form correctly
1. The new Achievement can't be created
2. Without the Achievement, the new Challenge can't be created
3. Use case ends in failure

## Special Requirements
- User must fill out the form correctly
- User must choose between goalSteps or goalKms in the Form.

## Frequency of Use
Whenever a User wants to create a new Challenge.

