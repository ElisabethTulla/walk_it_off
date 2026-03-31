# Architecture Overview

## Introduction
This document provides a high-level overview of the system architecture.

## Architecture Goals
- [Goal 1]
- [Goal 2]
- [Goal 3]

## Architecture Style
[Describe the overall architecture style: layered, microservices, event-driven, etc.]

## High-Level Architecture Diagram
[Insert architecture diagram here]

## System Components

### Component 1: application walk it off
**Purpose:** [Description]
**Responsibilities:**
- [Responsibility 1]
- [Responsibility 2]

**Technologies:**
- libraries used:
   *      * - java.sql
   *      * - java.time
   *      * - java.util
   *      * - java.io
   *      * - javafx
   *      * - Junit)[Technology 1]
- [Technology 2]

### Component 2: [Name]
**Purpose:** [Description]
**Responsibilities:**
- [Responsibility 1]
- [Responsibility 2]

**Technologies:**
- [Technology 1]
- [Technology 2]

## Key Architectural Decisions

1. [Decision 1] Award achievements as soon as the goal is met
   - **Rationale:** This decision was made because it provides instant gratification within the user, as soon as activity is being logged and goal is reached. Also it simplifies the code.
   - **Alternatives Considered:** Award achievements only when the challenge is officially over.
   
2. [Decision 2]
   - **Rationale:** [Why this decision was made]
   - **Alternatives Considered:** [Other options]

## Quality Attributes

### Performance
[Performance requirements and how the architecture addresses them]
In order to provide a good performance sql statements join tables and summ up steps and kilometers in the database.

### Security
[Security requirements and how the architecture addresses them]
Only the repositories have access to the database. In order to show data in the GUI, the controller accesses the service layer, which then accesses the repository.
When a users tries to login with a wrong password or e-mail, he/she is not informed whether the e-mail or the password was wrong in order to hinder hackers.

### Scalability
[Scalability requirements and how the architecture addresses them]

### Maintainability
[Maintainability requirements and how the architecture addresses them]
Java Version 25

## Constraints and Limitations
- [Constraint 1]
- [Constraint 2]

## Future Considerations
- Expanding to comparing services
- Expanding to read and log data from GAMIN
- Launching in Appstore
