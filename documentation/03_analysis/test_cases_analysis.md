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
- Application environment has the necessary database drivers installed (PostgreSQL JDBC or H2 embedded/server).
- Database client tools (e.g. pgAdmin, or H2 Console) are available for verification.

**Test Steps:**
1. Update the application configuration to use either PostgreSQL or H2 as the primary data store.
2. Apply a secure admin username and password in the connection string/properties.
3. Run the application.
4. Attempt to connect to the database using valid credentials.
5. Inspect the installed database version to confirm it is a free/open-source edition.
7. Trigger a user registration via the application and query the newly created database tables.

**Expected Results:**
- Database schema initializes successfully without connection or migration errors.
- Authentication is enforced: connections succeed with valid credentials 
  and are explicitly rejected when the password is omitted or incorrect.
- License/version check confirms the deployment is a free community edition (e.g., PostgreSQL Community, H2 Open Source).
- User data is successfully written to the configured tables and can be retrieved, 
  confirming the database is ready for login authentication and account data access.

**Test Data:**
- Target Engine: PostgreSQL 15 (Community)
- Connection URL: `jdbc:postgresql://localhost:5432/app_users_db`
- DB Username: `db_admin`
- DB Password: `Secur3DbP@ss!`
- Registration Payload: `POST /api/auth/register` → `{"username": "testuser", "email": "test@example.com", "password": "ValidP@ss123"}`


### Use Case UC-02
### TC-A-002: Verify Database Table Creation, Relationships, and Query Efficiency
**Related Requirement:** FR-001 Database
**Related Use Case:** US-002
**Priority:** High
**Type:** Functional

**Preconditions:**
- Database instance (PostgreSQL or H2) is running and authenticated.
- Schema migration framework (e.g., Flyway, Liquibase, or ORM auto-ddl) is configured.
- Database client or query console is accessible for schema inspection and DML execution.

**Test Steps:**
1. Execute the database migration script to generate the initial schema.
2. Query the system catalog to verify the existence of the following tables: `user_walkitoff`, `activity`, `challenge`, 
  and `achievement`.
3. Verify the existence of intermediate junction tables: `user_challenge` and `user_achievement`.
4. Inspect table schemas to confirm correct data types, `NOT NULL` constraints, and primary key definitions.
5. Insert a test user into `user_walkitoff`, then insert test records into `challenge` and `achievement`.
6. Map the user to the challenge and achievement via `user_challenge` and `user_achievement`, 
  ensuring composite keys and foreign keys are enforced.
7. Insert a sample activity record into `activity` linked to the `user_walkitoff` ID, then execute a `JOIN` query across `user_walkitoff`, `activity`, and the junction tables. Run an execution plan (`EXPLAIN`/`EXPLAIN ANALYZE`) to verify index usage.

**Expected Results:**
- All six tables are created successfully with zero syntax or permission errors.
- `user_walkitoff` and `activity` tables correctly persist and reference user identifiers.
- `challenge` and `achievement` tables exist with appropriate schema structures.
- `user_challenge` and `user_achievement` successfully act as many-to-many junction tables with proper foreign key references and composite primary keys.
- Database rejects any INSERT/UPDATE that violates referential integrity (e.g., orphaned foreign keys or duplicate junction mappings).
- Execution plan confirms that appropriate indexes are utilized for JOINs, validating that relational data retrieval will not degrade application performance.

**Test Data:**
- `user_walkitoff`: `(id: 1, email: 'perf.test@walkitoff.com', username: 'perf_user01', created_at: '2026-04-07')`
- `challenge`: `(id: 101, name: 'Spring Step Goal', duration_days: 30)`
- `achievement`: `(id: 201, title: 'Consistency Badge', icon_url: '/icons/bronze.png')`
- `user_challenge`: `(user_id: 1, challenge_id: 101)`
- `user_achievement`: `(user_id: 1, achievement_id: 201)`
- `activity`: `(id: 5001, user_id: 1, step_count: 10250, date: '2026-04-07', calories_burned: 410)`


### Use Case UC-03
### TC-A-003: Verify Database Configuration Resolution Priority and Connection Fallback
**Related Requirement:** FR-001 Database
**Related Use Case:** US-003
**Priority:** Low
**Type:** Functional

**Preconditions:**
- Application environment supports externalized configuration (e.g., Spring Boot `.properties`/`.yaml`, Node `.env`, or equivalent).
- A reachable test database instance (or connection stub) is available on distinct endpoints/ports to differentiate configuration sources.
- Administrator access to modify system environment variables, application property files, and view startup logs.

**Test Steps:**
1. Configure baseline default credentials directly in the application's base configuration (`db.url`, `db.user`, `db.pass`).
2. Create a property file (e.g., `config/application-db.properties`) with a distinct set of database connection parameters.
3. Export session-level environment variables with a third distinct set of database connection parameters.
4. Start the application and capture the initialization logs to identify which configuration source was resolved.
5. Monitor the database connection logs to confirm a successful handshake using the resolved credentials.
6. Unset the environment variables, restart the application, and repeat steps 4–5 to verify property file fallback.
7. Remove the custom property file (or clear its DB keys), restart the application, and repeat steps 4–5 to verify fallback to default variables.

**Expected Results:**
- Application startup logs explicitly indicate the configuration hierarchy is evaluated in order: Environment Variables → Property File → Defaults.
- When environment variables are present, they override both the property file and default values, and the application successfully connects using the environment-specified URL and credentials.
- When environment variables are absent but property file keys exist, the system correctly reads the property file, ignores defaults, and establishes a valid connection.
- When both environment variables and property file entries are missing, the system seamlessly falls back to the default configuration values and connects without error.
- No configuration resolution warnings, null pointer exceptions, or connection failures occur during any fallback stage.

**Test Data:**
- **Defaults:** `url=jdbc:h2:mem:default_db`, `user=default_user`, `pass=DefaultP@ss1`
- **Property File:** `url=jdbc:postgresql://localhost:5432/prop_config_db`, `user=prop_user`, `pass=Pr0pS3cure!`
- **Environment Variables:** `DB_URL=jdbc:postgresql://localhost:5432/env_config_db`, `DB_USER=env_admin`, `DB_PASS=EnvK3y2026!`

## Coverage Analysis
[Analysis of test coverage for requirements and use cases]
