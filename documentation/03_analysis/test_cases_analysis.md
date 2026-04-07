# Test Cases Analysis

## Overview
This document contains test cases derived from the analysis phase, based on requirements and user stories.

## Test Cases by User Stories

### Use Case UC-01
### TC-A-001: Verify Database Initialization, Security, and Configuration for User Storage
**Related Requirement:** FR-001 Database
**Related Use Case:** US-001
**Priority:** High
**Type:** Functional

**Preconditions:**
- Application environment has the necessary database drivers installed (PostgreSQL or H2)
- Database client tools (eg. pgAdmin, or H2 Console) are available for verification.

**Test Steps:**
1. Update the application configuration to use either PostgreSQL or H2 as the primary data store
2. Apply a secure admin username and password in the connection string/properties-file/environment variable
3. Run the application.
4. Attempt to connect to the database
5. Inspect the installed database version to confirm it is a free/open-source edition
7. Register a new user via the application and query the newly created database tables

**Expected Results:**
- Database schema initializes successfully without connection errors
- Authentication is enforced: connections successful with valid credentials 
  and rejected when the password is incorrect
- version check confirms the deployment is a free community edition (eg., PostgreSQL Community, H2 Open Source)
- User data is successfully written to the configured tables and can be retrieved, 
  confirming the database is ready for login authentication and account data access.

**Test Data:**
- Target Engine: PostgreSQL (Community)
- Connection URL: `jdbc:postgresql://localhost:5432/walkitoff`
- DB Username: `postgres`
- DB Password: `postgres`

### Use Case UC-02
### TC-A-002: Verify Database Table Creation, Relationships, and Query Efficiency
**Related Requirement:** FR-001 Database
**Related Use Case:** US-002
**Priority:** High
**Type:** Functional

**Preconditions:**
- Database PostgreSQL is running and connected.
- install pgAdmin4

**Test Steps:**
1. Generate the table schemas found in at.elisabeth_tulla.walk_it_off/sql/create_tables.sql.
2. View the tables in pgAdmin4 to verify the existence of the following tables: `user_walkitoff`, `activity`, `challenge`, 
  and `achievement`.
3. Verify the existence of intermediate junction tables: `user_challenge` and `user_achievement`.
4. Inspect table schemas to confirm correct data types, constraints, primary key and foreign key definitions.
5. Insert a test user into `user_walkitoff`, then insert test records into `challenge` and `achievement`.
6. Map the user to the challenge and achievement via `user_challenge` and `user_achievement`, 
  ensuring foreign keys are enforced.
7. Insert a sample activity record into `activity` linked to the `user_walkitoff` ID and view the Data of the tables.

**Expected Results:**
- All six tables are created successfully
- `user_walkitoff`, `activity`, `challenge` and `achievement` tables exist with appropriate schema structures.
- `user_challenge` and `user_achievement` successfully act as many-to-many junction tables 
    with proper foreign key references and primary keys.

**Test Data:**
- see 05_testing/test_data.md

### Use Case UC-03
### TC-A-003: Verify Database Configuration and Connection
**Related Requirement:** FR-001 Database
**Related Use Case:** US-003
**Priority:** Low
**Type:** Functional

**Preconditions:**
- Application environment supports externalized configuration (`.properties`, environment-variable).

**Test Steps:**
1. Configure default credentials directly in the application's configuration (`db_url`, `db_user`, `db_password`).
2. Create a property file (eg., `config.properties`) with a set of database connection parameters.
3. Export session-level environment variables with a third distinct set of database connection parameters.
4. Start the application and capture the initialization logs to identify which configuration source was resolved.
5. Monitor the database connection logs to confirm the use of the resolved credentials.
6. Unset the environment variables, restart the application, and repeat steps 4–5 to verify property file fallback.
7. Remove the custom property file, restart the application, and repeat steps 4–5 to verify fallback to default variables.

**Expected Results:**
- Application startup logs explicitly indicate the configuration in order: 
  Environment Variables → Property File → Defaults.
- When environment variables are present, they override both the property file and default values, 
  and the application successfully connects using the environment-specified URL and credentials.
- When environment variables are absent but property file keys exist, the system correctly reads the property file, 
  ignores defaults, and establishes a valid connection.
- When both environment variables and property file entries are missing, 
  the system falls back to the default configuration values and connects without error.

**Test Data:**
- `url=jdbc:postgresql://localhost:5432/walkitoff`
- `user=postgres`
- `password=postgres`

