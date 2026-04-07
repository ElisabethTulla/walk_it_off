# Architecture Overview

## Introduction
This document provides a high-level overview of the system architecture.

## Architecture Goals
- Simplicity and Maintainability
- Performance
- Security

## Architecture Style
The overall architecture style is monolithic. 
The application is self-contained and independent from other applications.

## High-Level Architecture Diagram
OUT OF SCOPE

## System Components

### Component 1: application walk it off
**Purpose:** runs the implemented features
**Responsibilities:**
- starts the Jfx GUI
- connects to the database
- reliably executes the implementation

**Technologies:**

- libraries used:
   *      java.sql
   *      java.time
   *      java.util
   *      java.io
 
- dependencies used:
   *      maven
   *      javafx
   *      Junit5

- database used:
    *      postgreSQL


## Key Architectural Decisions

1. Award achievements as soon as the goal is met
   - **Rationale:** This decision was made because it provides instant gratification within the user, 
        as soon as activity is being logged and goal is reached. Also, it simplifies the code.
   - **Alternatives Considered:** Award achievements only when the challenge is officially over.
   
2. Clear modular structure and separation of concerns (controllers, services, repositories)
   - **Rationale:** Makes the code easy to understand and modify.
   - **Alternatives Considered:** package structure divided into "running, walking, comparing,..."

## Quality Attributes

### Performance
In order to provide a good performance SQL statements join tables and summ up steps and kilometers in the database.

### Security
Only the repositories have access to the database. In order to show data in the GUI, 
the controller accesses the service layer, which then accesses the repository.
When a user tries to log in with a wrong password or e-mail, 
he/she is not informed whether the e-mail or the password was wrong in order to hinder hackers.

### Scalability
PostgreSQL provides upscaling options in order to accommodate a bigger user base.

### Maintainability
The application is kept easily maintainable through its single codebase with clear modular structure.
The Java Version 25 also provides long term support.

## Constraints and Limitations
- Time
- Jfx graphical user interface

## Future Considerations
- Expanding to comparing services with statistics of age-groups,...
- Expanding to read and log data from GAMIN and similar services.
- Launching in Appstore
