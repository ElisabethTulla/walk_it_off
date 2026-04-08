# Test Cases

## Overview
This document contains JUNIT5 test cases for the project execution phase.
Other forms of testing were executed manually (also using console logs). 

For Test Cases based on User Stories see 03_analysis/test_cases_analysis!


## Test Case Template

### TC-XXX: [Test Case Name]
**Type:** [Unit/Integration/System/Acceptance]
**Priority:** [High/Medium/Low]
**Status:** [Draft/Ready/Executed/Passed/Failed]
**Related Requirement:** [Requirement ID]
**Related Use Case:** [Use Case ID]

**Preconditions:**
- [Precondition 1]
- [Precondition 2]

**Test Steps:**
1. [Step 1]
   - **Expected:** [Expected result]
2. [Step 2]
   - **Expected:** [Expected result]
3. [Step 3]
   - **Expected:** [Expected result]

**Test Data:**
- Input: [Test input data]
- Expected Output: [Expected output]

**Postconditions:**
- [Postcondition 1]
- [Postcondition 2]

**Execution History:**
| Date | Tester | Result | Comments |
|------|--------|--------|----------|
| [Date] | [Name] | [Pass/Fail] | [Comments] |

---

### JUNIT 5 Test Cases

### TC-U-001: Verify Correct Age Calculation from User Date of Birth
**Type:** Unit
**Priority:** Medium
**Status:** Ready

**Preconditions:**
- JUnit 5 test runner is configured and project dependencies are successfully resolved.
- The `User` entity class and `getAge()` method are implemented and available.

**Test Steps:**
1. Instantiate a new `User` object via its parameterized constructor, passing `test`, `tester`, `test@tester.at`, `Postgres1!`, `LocalDate.of(1992, 1, 1)`, and `female`.
   - **Expected:** The `User` instance is successfully created in memory with no errors or runtime exceptions.
2. Invoke the `getAge()` method on the initialized `User` object.
   - **Expected:** The method executes the internal `Period.between` logic, 
                  calculates the years from `1992-01-01` to LocalDate.now(), and returns an `Integer` value.
3. Validate the result using JUnit's `assertEquals(34, testUser.getAge())` assertion.
   - **Expected:** The assertion evaluates to `true`, the test method completes successfully.

**Test Data:**
- Input: `firstName="test"`, `lastName="tester"`, `email="test@tester.at"`, `password="Postgres1!"`, 
        `birthdayDate=1992-01-01`, `gender="female"`
- Expected Output: `34`

**Execution History:**
| Date      | Tester          | Result | Comments |
| 20.03.26  | Elisabeth Tulla | Pass   |          |


### TC-U-002: Verify Challenge End Date Calculation Logic
**Type:** Unit
**Priority:** Medium
**Status:** Ready

**Preconditions:**
- JUnit 5 test environment and project dependencies are properly configured.
- The `ChallengeService` class and its `calculateEndDate(LocalDate, int)` method are compiled and accessible.

**Test Steps:**
1. Instantiate the `ChallengeService` class using its default constructor.
   - **Expected:** Service object is created successfully.
2. Invoke `calculateEndDate()` passing `LocalDate.of(2026, 3, 1)` as the start date and `1` as the duration in days.
   - **Expected:** The method executes the date arithmetic logic, correctly applies the time boundary (23:59), 
                   and returns a `LocalDateTime` object representing the challenge end.
3. Validate the returned value using `assertEquals(LocalDateTime.of(2026, 3, 1, 23, 59), result)`.
   - **Expected:** The assertion evaluates to `true`, the test passes successfully, and no `AssertionError` is triggered.

**Test Data:**
- Input: `startDate = 2026-03-01`, `durationDays = 1`
- Expected Output: `2026-03-01T23:59`

**Postconditions:**
- The `ChallengeService` instance falls out of test scope and is cleared by the garbage collector.

**Execution History:**
| Date      | Tester          | Result | Comments |
| 20.03.26  | Elisabeth Tulla | Pass   |          |


### TC-U-003: Verify Password ValidationManager
**Type:** Unit
**Priority:** High
**Status:** Ready
**Related Requirement:** FR-002, FR-003
**Related Use Case:** UC-01, UC-02

**Preconditions:**
- JUnit 5 test environment and project dependencies are properly configured.
- The `ValidationManager` class and its `validatePassword(String)` method are compiled and accessible.
- Password policy rules (min/max length, character complexity,... →Regex) are implemented.

**Test Steps:**
1. Instantiate the `ValidationManager` class using its default constructor.
   - **Expected:** Manager object is created successfully.
2. Invoke `validatePassword()` with four invalid test cases: `"password"`, `"ThisPasswordIsWayTooLong1!"`, 
   `"NOLOWERCASE1!"`, and `"123456789"`
   - **Expected:** Each invocation returns `false`, and all four `assertFalse` assertions pass, 
                   confirming rejection of passwords.
3. Invoke `validatePassword()` with three valid test cases: `"Password123!"`, `"Postgres2?"`, and `"StrongPassword*8"`.
   - **Expected:** Each invocation returns `true`, and all three `assertTrue` assertions pass, 
                   confirming acceptance of passwords that satisfy all policy constraints.

**Test Data:**
- Input (Invalid): `"password"`, `"ThisPasswordIsWayTooLong1!"`, `"NOLOWERCASE1!"`, `"123456789"`
- Input (Valid): `"Password123!"`, `"Postgres2?"`, `"StrongPassword*8"`
- Expected Output: `false` for invalid inputs; `true` for valid inputs

**Postconditions:**
- The `ValidationManager` instance falls out of test scope and is cleared by the garbage collector.

**Execution History:**
| Date      | Tester          | Result | Comments |
| 20.03.26  | Elisabeth Tulla | Pass   |          |


## Functional Test Cases

### Feature: [Feature Name]

#### TC-F-001: [Test Case Name]
**Type:** Functional
**Priority:** High
**Status:** Draft
**Related Requirement:** FR-001

**Preconditions:**
- [Precondition 1]

**Test Steps:**
1. [Step 1]
   - **Expected:** [Expected result]
2. [Step 2]
   - **Expected:** [Expected result]

**Test Data:**
- Input: [Test data]
- Expected Output: [Expected output]


## Integration Test Cases
OUT OD SCOPE

### Integration: [Component A → Component B]

#### TC-I-001: [Test Case Name]
[Test case details]

#### TC-I-002: [Test Case Name]
[Test case details]

## System Test Cases
OUT OF SCOPE

#### TC-S-001: [Test Case Name]
[Test case details]

#### TC-S-002: [Test Case Name]
[Test case details]

## Acceptance Test Cases
OUT OF SCOPE

#### TC-A-001: [Test Case Name]
[Test case details]

#### TC-A-002: [Test Case Name]
[Test case details]

## Regression Test Cases
OUT OF SCOPE

#### TC-R-001: [Test Case Name]
[Test case details]

