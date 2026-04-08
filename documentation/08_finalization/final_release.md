# Final Release

## Release Information
**Version:** 1.0.0
**Release Date:** 10.04.2026
**Release Name/Codename:** walk it off
**Release Type:** Major

## Release Overview
This release delivers the first version of this project as endproject of the wifi java software engineering course.

## Release Objectives
1. working application
2. usable graphical user interface
3. connected database

## Features Delivered

For a complete list of delivered Features visit Github:
https://github.com/users/ElisabethTulla/projects/3/views/1?filterQuery=iteration

### Feature 1: Database
**Description:** is essential for all User data to be saved permanently
**User Stories Completed:**
- [US-001]: create database
- [US-002]: create database tables and relations
- [US-002]: implement database configuration and connection

**Benefits:**
- connected database to store all data

### Feature 2: User registration
**Description:** provides the possibility for a new User to use the application as intended
**User Stories Completed:**
- all atteched user stories

**Benefits:**
- [Benefit 1]

### Feature 3: User login
**Description:** protects user data with password and gives access to the features of the application
**User Stories Completed:**
- all attached user stories

**Benefits:**
- [Benefit 1]

### Feature 4: Activity logging
**Description:** provides the collecting of steps and kilometers in the database
**User Stories Completed:**
- all attached user stories

**Benefits:**
- [Benefit 1]

### Feature 5: entering Challenges
**Description:** enables the possibility to unlock more achievements
**User Stories Completed:**
- all attached user stories

**Benefits:**
- [Benefit 1]

### Feature 6: unlocking Achievements
**Description:** rewards the User for logging Activity and participating in Challenges
**User Stories Completed:**
- all attached user stories

## Release Metrics
OUT OF SCOPE

### Development Metrics
| Metric | Value |
|--------|-------|
| Total Sprints | [Number] |
| Total Story Points Delivered | [Number] |
| Average Velocity | [Number] |
| Total Features Delivered | [Number] |
| Total User Stories Completed | [Number] |
| Total Bugs Fixed | [Number] |

### Quality Metrics
| Metric | Value |
|--------|-------|
| Code Coverage | [Percentage]% |
| Test Cases Executed | [Number] |
| Test Pass Rate | [Percentage]% |
| Critical Bugs | 0 |
| High Priority Bugs | 0 |
| Medium Priority Bugs | [Number] |
| Low Priority Bugs | [Number] |

### Performance Metrics
| Metric | Target | Actual | Status |
|--------|--------|--------|--------|
| Response Time | [Target] | [Actual] | [Met/Not Met] |
| Throughput | [Target] | [Actual] | [Met/Not Met] |
| Error Rate | [Target] | [Actual] | [Met/Not Met] |

## Release Components

### Software Components
- **Application:** 1.0.0
- **Database:** postgresql 42.7.4
- **Dependencies:** 
  - JUNIT 5.10.2
  - fasterxml.jackson 2.19.2
  - javaFX modules 25.0.2

### Documentation
- [x] User Manual
- [x] Installation Guide
- [ ] Administrator Guide
- [x] Documentation
- [x] Release Notes

## Known Issues and Limitations

### Known Issues
#### Issue 1: Comparing Features not yet connected to GUI
**Severity:** Low
**Impact:** limits the features of the application
**Workaround:** implementation of the ComparingController
**Planned Fix:** tba

### Limitations
- limited time
- no API(Rest Services)
- only community editions and free to use tools (with limited features)

## System Requirements

### Hardware Requirements

**Minimum:**
* CPU: 1-core processor (≈1 GHz)
* RAM: 512 MB
* Disk Space: 100–200 MB

**Recommended:**
* CPU: 2-core processor (≥2 GHz)
* RAM: 1–2 GB
* Disk Space: 500 MB+

### Software Requirements
- Operating System: linux/windows/macOs
- Database: postgresql or h2
- Other Dependencies: maven, junit, javafx

## Installation and Deployment
see docs/architecture/setup.md

### Deployment Procedure
1. [Step 1]
2. [Step 2]
3. [Step 3]

### Rollback Procedure
1. [Step 1]
2. [Step 2]
3. [Step 3]

### Post-Deployment Verification
- [x] Application starts successfully
- [x] Database migration completed
- [x] All critical features functional
- [ ] Performance metrics within acceptable range
- [ ] Security checks passed

## Testing Summary

### Test Execution Summary
- **Total Test Cases:** 3 JUNIT tests
- **Executed:** 3
- **Passed:** 3
- **Failed:** 0
- **Blocked:** 0
- **Not Executed:** 0

### Test Coverage
- **Unit Tests:** 5 %
- **Manual Tests:** 100 %
- **Integration Tests:** 0%
- **System Tests:** 0 %
- **Acceptance Tests:** 100 %

## Security Assessment
OUT OF SCOPE

### Security Testing Completed
- [] Vulnerability Scanning
- [] Penetration Testing
- [] Security Code Review
- [] Dependency Security Audit

### Security Issues
[List any security issues found and their resolution status]

## Compliance and Legal
OUT OF SCOPE

### Compliance Requirements Met
- [ ] [Compliance requirement 1]
- [ ] [Compliance requirement 2]

### Licenses
- [List of third-party licenses used]

## Release Approval
OUT OF SCOPE

### Sign-off
- [ ] **Product Owner:** [Name] - Date: [YYYY-MM-DD]
- [ ] **Development Team Lead:** [Name] - Date: [YYYY-MM-DD]
- [ ] **QA Lead:** [Name] - Date: [YYYY-MM-DD]
- [ ] **Security Officer:** [Name] - Date: [YYYY-MM-DD]
- [ ] **Project Manager:** [Name] - Date: [YYYY-MM-DD]

## Release Communication

### Stakeholder Notification
- [ ] Internal stakeholders notified
- [ ] External stakeholders notified
- [ ] End users notified
- [ ] Support team briefed

### Release Announcement
[Link to or content of release announcement]

## Support and Maintenance

### Support Contact Information
- **Email:** tulla.elisabeth@gmx.at

## Next Steps

### Future Releases
- **Version 2.0.0:** tba
  - prevent empty textfields
  - implement a Logger to differentiate between wrong email/password during failed login
  - compare Achievements to other users
  - compare stats to user-groups (statistics)
  - log time with runs and compare improvements
  - add maxTime to challenges
  - ChallengeProgressView + Controller
  - UpdateUserDateView + Controller

### Continuous Improvement
- Security
- Scalability

## Appendices
OUT OF SCOPE

### Appendix A: Detailed Change Log
[Link to detailed change log]

### Appendix B: Migration Guide
[Link to migration guide if applicable]

### Appendix C: Training Materials
[Link to training materials]
