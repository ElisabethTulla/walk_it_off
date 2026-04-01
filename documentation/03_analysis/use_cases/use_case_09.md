# Use Case 09: View Stats

## Overview
**ID:** UC-09
**Name:** View Stats
**Primary Actor:** User
**Brief Description:** Provides tables of active Challenges, 
unlocked Achievements and logged Activities in specific timeframe to the user.

## Preconditions
- User must be registered
- User must be logged in
- User must be in own account

## Postconditions
- User has knowledge of active Challenges
- User has knowledge of unlocked Achievements 
- User has knowledge of logged Activities in timeframe of own choosing

## Main Success Scenario
1. The login will lead to the account of the user
2. The table of active Challenges will load in automatically
3. The user can click the button "Your Achievements" to fill in a table with unlocked Achievements
4. The user can put in two dates via the DatePicker and choose steps or kilometers 
to fill in a table with the logged Activities of this timeframe

## Alternative Flows

### Alternative Flow 1: No active Challenges
**Condition:** User is currently not entered in any challenges
1. The table with active Challenges will be empty

### Alternative Flow 2: No Activities logged in chosen timeframe
**Condition:** User hasn't logged any chosen type of Activity in the chosen timeframe
1. The table with the Activities will be empty

## Special Requirements
- User must be participating in a Challenge in order for a Challenge to be shown in the table
- User must have logged Activity in the choosen timeframe in order for any Activity to be shown in the table

## Frequency of Use
Whenever a User accesses his/her account.

