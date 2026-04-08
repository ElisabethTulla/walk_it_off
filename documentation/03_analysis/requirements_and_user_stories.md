# Requirements and User Stories

## Overview

This document captures the functional and non-functional requirements as well as user stories for the project.

## Functional Requirements

### FR-001: Database
**Description:** is essential for all User data to be saved permanently
**Priority:** High
**Status:** Implemented

### FR-002: User registration
**Description:** provides the possibility for a new User to use the application as intended
**Priority:** High
**Status:** Implemented

### FR-003: User login
**Description:** protects user data with password and gives access to the features of the application
**Priority:** High
**Status:** Implemented

### FR-004: Activity logging
**Description:** provides the collecting of steps and kilometers in the database
**Priority:** High
**Status:** Implemented

### FR-005: entering Challenges
**Description:** enables the possibility to unlock more achievements
**Priority:** Medium
**Status:** Implemented

### FR-006: unlocking Achievements
**Description:** rewards the User for logging Activity and participating in Challenges
**Priority:** Medium
**Status:** Implemented

## Non-Functional Requirements

### NFR-001: Usability
**Description:** The application should be easy to use, intuitive and provide a logical path to all features.
**Priority:** High
**Status:** Implemented

### NFR-002: Scalability
**Description:** The application should be build in a way, where it can handle growth.
**Priority:** Low
**Status:** Draft

### NFR-003: Security
**Description:** The application should be accessible only via a login process to protect it against unauthorized access
**Priority:** Medium
**Status:** Implemented

### NFR-001: Performance
**Description:** The application should be responsive and fast.
**Priority:** Low
**Status:** Implemented

## User Stories

A detailed overview of all User Stories (and Features) alongside the planned iterations
can be found on Github through the following link:
https://github.com/users/ElisabethTulla/projects/3/views/1

### US-001: create database
**As a** user
**I want** my data to be saved to at database
**So that** I can be recognized as user at the login and can access any of my data via my account.

**Acceptance Criteria:**
- the database must either be on postgreSql or h2
- the database must be free to use
- the database should be scalable
- password protected

**Priority:** High
**Estimation:** 3

**Belongs to Feature:** database setup

### US-002: create database tables and relations
**As a** user
**I want** my data to be saved in an efficient way
**So that** the performance of the application doesn't suffer

**Acceptance Criteria:**
- the database must save every registering User to table user_walkitoff
- the database must save every logged Activity to table activity
- the database must have tables of Challenges and Achievements
- the database must have intermediate tables for user_challenge and user_achievement

**Priority:** High
**Estimation:** 7

**Belongs to Feature:** database setup

### US-003: implement database configuration and connection
**As a** user/administrator
**I want** the possibility of sourcing the url, user and password for the connection to the database
via property-file of environment-variable
**So that** I can be flexible and adapt to changes to the connection of the database.

**Acceptance Criteria:**
- checking for environment-variables before connecting
- checking for property-files before connecting
- using the default variables as last option

**Priority:** Low
**Estimation:** 4

**Belongs to Feature:** database setup


