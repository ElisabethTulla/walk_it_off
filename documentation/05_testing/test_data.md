# Test Data

## Overview
This document describes the test data used for testing the system.

Test Users are created in the class at.elisabeth_tulla.walk_it_off/CreateTestUsers
or registered while running the application.
Test Achievements and Challenges were created in the pgAdmin4 or using the feature "create new Challenge"
of the application.

## Test Data Strategy

### Data Generation Approach
- Running the class at.elisabeth_tulla.walk_it_off/CreateTestUsers.
- Using the "Register new User" feature of the application.
- Using the "Create new Challenge" feature of the application. 

### Data Management
The test data is saved to the database walk_it_off connected to the application.

## Test Data Sets

### Test Data Set 1: Test Users
**Purpose:** This Data Set is used to generate Test User-Objects and save them to the database.
**Source:** Elisabeth Tulla has created these Test Users. (at.elisabeth_tulla.walk_it_off/CreateTestUsers)
**Size:** 5

**Sample Data:**
| ID | firstName | lastName | email                  | password   | birthDate   | gender |
|----|-----------|----------|------------------------|------------|-------------|--------|
| 1  | Elvis     | Tulla    | elvis@tulla.at         | Postgres1! | 2018, 1, 18 | male   |
| 2  | Felix     | Tulla    | felix@tulla.at         | Postgres1! | 2015, 1, 6  | male   |
| 3  | Elisabeth | Tulla    | tulla.elisabeth@gmx.at | postgres   | 1992, 1, 16 | female |
| 4  | Oliver    | Tulla    | oliver@tulla.at        | Postgres1! | 1992, 7, 21 | male   |
| 5  | Nachi     | Tulla    | nachi@tulla.at         | Postgres1! | 2016, 6, 3  | male   |

**Usage:**
- Login
- logging Activity
- entering Challenge
- showing Stats
- changing personal Info


### Test Data Set 2: Achievements
**Purpose:** This Data Set is used to generate Test Achievements and save them to the database 
 in order to test creating Challenges and unlocking Achievements for the User.
**Source:** database walk_it_off, table achievement (PostgreSQL), created in the database by Elisabeth Tulla.
**Size:** 27

**Sample Data:**
| ID | name             | required_steps | required_days_active | achievement_type | required_km |
| -- | ---------------- | -------------- | -------------------- | ---------------- | ----------- |
| 2  | GOLD month       | 310000         | 0                    | challenge        | 0.00        |
| 3  | SILVER month     | 248000         | 0                    | challenge        | 0.00        |
| 4  | BRONZE month     | 155000         | 0                    | challenge        | 0.00        |
| 5  | GOLD week        | 70000          | 0                    | challenge        | 0.00        |
| 6  | SILVER week      | 56000          | 0                    | challenge        | 0.00        |
| 7  | BRONZE week      | 35000          | 0                    | challenge        | 0.00        |
| 8  | MARATHON         | 0              | 0                    | challenge        | 42.19       |
| 9  | HALF-MARATHON    | 0              | 0                    | challenge        | 21.09       |
| 10 | QUARTER-MARATHON | 0              | 0                    | challenge        | 10.50       |
| 11 | 5K               | 0              | 0                    | challenge        | 5.00        |
| 12 | 10 000 STEPS     | 10000          | 0                    | user             | 0.00        |
| 13 | The first step!  | 0              | 0                    | user             | 0.00        |
| 14 | Ran20kmIn1Week   | 0              | 0                    | challenge        | 20.00       |
| 15 | 3K               | 0              | 0                    | challenge        | 3.00        |
| 17 | 3K               | 0              | 0                    | challenge        | 3.00        |
| 18 | 20000 steps      | 20000          | 0                    | challenge        | 0.00        |
| 24 | test             | 1000           | 0                    | challenge        | 0.00        |
| 25 | SpeedWalker      | 25000          | 0                    | challenge        | 0.00        |
| 26 | SpeedRunner      | 0              | 0                    | challenge        | 10.00       |
| 27 | bestWalk         | 20000          | 0                    | challenge        | 0.00        |

**Usage:**
- creating Challenge
- unlocking Achievement


### Test Data Set 3: Challenges
**Purpose:** This Data Set is used to generate Test Challenges and save them to the database
in order for the User to enter Challenges.
**Source:** database walk_it_off, table challenge (PostgreSQL), created in the database by Elisabeth Tulla.
**Size:** 30

**Sample Data:**
| ID | name                      | required_steps | required_achievement_id | min_number_participants | max_number_participants | goal_steps | goal_distance_km | started_at          | goal_end            | rewards_achievement_id | required_km |
| -- | ------------------------- | -------------- | ----------------------- | ----------------------- | ----------------------- | ---------- | ---------------- | ------------------- | ------------------- | ---------------------- | ----------- |
| 4  | January155                | 0              | 12                      | 1                       | 9999999                 | 155000     | 0.00             | 2026-01-01 00:00:00 | 2026-01-31 23:59:00 | 4                      | 0.00        |
| 6  | January310                | 0              | 12                      | 1                       | 9999999                 | 310000     | 0.00             | 2026-01-01 00:00:00 | 2026-01-31 23:59:00 | 2                      | 0.00        |
| 7  | January248                | 0              | 12                      | 1                       | 9999999                 | 248000     | 0.00             | 2026-01-01 00:00:00 | 2026-01-31 23:59:00 | 3                      | 0.00        |
| 8  | February140               | 0              | 12                      | 1                       | 9999999                 | 140000     | 0.00             | 2026-02-01 00:00:00 | 2026-02-28 23:59:00 | 4                      | 0.00        |
| 9  | February224               | 0              | 12                      | 1                       | 9999999                 | 224000     | 0.00             | 2026-02-01 00:00:00 | 2026-02-28 23:59:00 | 3                      | 0.00        |
| 10 | February280               | 0              | 12                      | 1                       | 9999999                 | 280000     | 0.00             | 2026-02-01 00:00:00 | 2026-02-28 23:59:00 | 2                      | 0.00        |
| 11 | March155                  | 0              | 12                      | 1                       | 9999999                 | 155000     | 0.00             | 2026-03-01 00:00:00 | 2026-03-31 23:59:00 | 4                      | 0.00        |
| 12 | March248                  | 0              | 12                      | 1                       | 9999999                 | 248000     | 0.00             | 2026-03-01 00:00:00 | 2026-03-31 23:59:00 | 3                      | 0.00        |
| 13 | March310                  | 0              | 12                      | 1                       | 9999999                 | 310000     | 0.00             | 2026-03-01 00:00:00 | 2026-03-31 23:59:00 | 2                      | 0.00        |
| 14 | April150                  | 0              | 12                      | 1                       | 9999999                 | 150000     | 0.00             | 2026-04-01 00:00:00 | 2026-04-30 23:59:00 | 4                      | 0.00        |
| 15 | April240                  | 0              | 12                      | 1                       | 9999999                 | 240000     | 0.00             | 2026-04-01 00:00:00 | 2026-04-30 23:59:00 | 3                      | 0.00        |
| 16 | April300                  | 0              | 12                      | 1                       | 9999999                 | 300000     | 0.00             | 2026-04-01 00:00:00 | 2026-04-30 23:59:00 | 2                      | 0.00        |
| 17 | Marathon                  | 0              | 11                      | 1                       | 9999999                 | 0          | 42.19            | 2026-02-24 00:00:00 | 2026-02-24 23:59:00 | 8                      | 0.00        |
| 18 | Half-Marathon             | 0              | 11                      | 1                       | 9999999                 | 0          | 21.09            | 2026-02-24 00:00:00 | 2026-02-24 23:59:00 | 9                      | 0.00        |
| 19 | Quarter-Marathon          | 0              | 11                      | 1                       | 9999999                 | 0          | 10.50            | 2026-02-24 00:00:00 | 2026-02-24 23:59:00 | 10                     | 0.00        |
| 21 | 5K                        | 0              | 12                      | 1                       | 9999999                 | 0          | 5.00             | 2026-02-24 00:00:00 | 2026-02-24 23:59:00 | 11                     | 0.00        |
| 22 | 5K                        | 0              | 12                      | 1                       | 9999999                 | 0          | 5.00             | 2026-02-25 00:00:00 | 2026-02-25 23:59:00 | 11                     | 0.00        |
| 23 | Run20Kms                  | 0              | 13                      | 1                       | 9999999                 | 0          | 20.00            | 2026-03-09 00:00:00 | 2026-03-15 23:59:00 | 14                     | 0.00        |
| 25 | 5K                        | 0              | 12                      | 1                       | 9999999                 | 0          | 5.00             | 2026-03-12 00:00:00 | 2026-03-12 23:59:00 | 11                     | 0.00        |
| 26 | Walk 10.000 Steps With Me | 0              | 12                      | 1                       | 9999999                 | 10000      | 0.00             | 2026-03-12 00:00:00 | 2026-03-12 23:59:00 | 12                     | 0.00        |
| 27 | 3K                        | 0              | 13                      | 1                       | 999999                  | 0          | 3.00             | 2026-03-12 00:00:00 | 2026-03-12 23:59:00 | 17                     | 0.00        |
| 28 | 20000 steps               | 0              | 12                      | 1                       | 999999                  | 20000      | 0.00             | 2026-03-13 00:00:00 | 2026-03-13 23:59:00 | 18                     | 0.00        |
| 29 | SpeedWalker               | 0              | 13                      | 1                       | 999999                  | 25000      | 0.00             | 2026-04-01 00:00:00 | 2026-04-01 23:59:00 | 25                     | 0.00        |
| 30 | SpeedRun                  | 0              | 13                      | 1                       | 999999                  | 0          | 10.00            | 2026-04-01 00:00:00 | 2026-04-01 23:59:00 | 26                     | 0.00        |

**Usage:**
- User entering Challenge

## Boundary Value Test Data

OUT OF SCOPE

### Boundary Test Set 1: [Field/Parameter Name]
**Type:** [Numeric/String/Date/etc.]

| Test Case | Input Value | Expected Result | Notes |
|-----------|-------------|-----------------|-------|
| Minimum - 1 | [Value] | [Result] | Below minimum |
| Minimum | [Value] | [Result] | At minimum |
| Minimum + 1 | [Value] | [Result] | Just above minimum |
| Maximum - 1 | [Value] | [Result] | Just below maximum |
| Maximum | [Value] | [Result] | At maximum |
| Maximum + 1 | [Value] | [Result] | Above maximum |

## Invalid Test Data

OUT OF SCOPE

### Invalid Data Set 1: [Name]
**Purpose:** Test error handling

| Test Case | Input | Expected Error | Error Message |
|-----------|-------|----------------|---------------|
| [Case 1] | [Invalid input] | [Error type] | [Message] |
| [Case 2] | [Invalid input] | [Error type] | [Message] |

## Special Test Data

OUT OF SCOPE

### Edge Cases
- [Edge case 1]: [Data]
- [Edge case 2]: [Data]

### Null/Empty Values
- [Test scenario 1]: [Data]
- [Test scenario 2]: [Data]

### Special Characters
- [Test scenario 1]: [Data]
- [Test scenario 2]: [Data]

## Performance Test Data

OUT OF SCOPE

### Load Test Data
**Volume:** [Number of records]
**Characteristics:** [Description]

### Stress Test Data
**Volume:** [Number of records]
**Characteristics:** [Description]

## Test Data Refresh

OUT OF SCOPE

### Refresh Frequency
[How often test data is refreshed]

### Refresh Procedure
1. [Step 1]
2. [Step 2]
3. [Step 3]

## Data Privacy and Security

OUT OF SCOPE

### Sensitive Data Handling
[How sensitive data is handled in testing]

### Data Masking
[Describe data masking approach]

### Compliance
[Relevant compliance requirements]

## Test Data Storage

### Location
The Data is stored in the database walk_it_off in PostgreSQL

### Access Control
Elisabeth Tulla

### Backup
The Test Users are also present in the repository on Github.
