# Use Case 13: View all Challenges

## Overview
**ID:** UC-13
**Name:** View all Challenges
**Primary Actor:** User
**Brief Description:** Provides a tables of all Challenges

## Preconditions
- User must be registered
- User must be logged in
- User must have clicked the button "Enter Challenge" to view the table

## Postconditions
- User has knowledge of all Challenges
- User can now look up the ChallengeId of a Challenge he/she might want to enter in the table

## Main Success Scenario
1. The user can click the button "Enter Challenge" to find the table with all Challenges
2. The user can put in the right ChallengeId to enter a Challenge

## Alternative Flows

### Alternative Flow 1: No entering in Challenge
**Condition:** User currently doesn't want to enter a Challenge from the table
1. The user can create a new Challenge via the available form
2. The user can now find the new Challenge in the table and use the ChallengeId to enter it

## Special Requirements
- User must be registered and logged in to access the button "Enter Challenge"

## Frequency of Use
Whenever a User wants to enter a Challenge or wants to look at all available Challenges

