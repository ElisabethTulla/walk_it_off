# walk_it_off

## Overview

This is a Java training project demonstrating software development best practices, including proper project structure, documentation, and development workflows.

## Quickstart

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/ElisabethTulla/walk_it_off.git
cd walk_it_off
```

2. Build the project:
```bash
mvn clean install
```

3. Run tests:
```bash
mvn test
```

## Tech Stack

- **Language:** Java 11
- **Build Tool:** Maven
- **Version Control:** Git

## Documentation

For detailed documentation, please refer to:

- [Setup Guide](docs/setup.md) - Detailed setup instructions
- [Architecture Overview](docs/architecture/overview.md) - System architecture
- [API Documentation](docs/api/endpoints.md) - API endpoints
- [User Guide](docs/user-guide.md) - How to use the software
- [Contributing Guide](CONTRIBUTING.md) - Development guidelines

## Project Structure

```
├── README.md                       # This file
├── CONTRIBUTING.md                 # Development guidelines
├── LICENSE                         # License information
├── pom.xml                         # Maven configuration
├── docs/                           # Technical documentation
│   ├── setup.md
│   ├── architecture/
│   ├── api/
│   └── user-guide.md
└── documentation/                  # Project management docs
|-- src/                            # Source code
    |-- main
    |   |-- java
    |       |-- at.elisabeth_tulla.walk_it_off
    |       |   |-- config
    |       |   |   |-- DatabaseConfig.java
    |       |   |-- jfx_gui
    |       |   |   |-- AccountController.java
    |       |   |   |-- ActivityController.java
    |       |   |   |-- ChallengeController.java
    |       |   |   |-- JfxMainApp.java
    |       |   |   |-- ManageAccountController.java
    |       |   |   |-- RegisterController.java
    |       |   |   |-- UserController.java
    |       |   |   |-- WelcomeController.java
    |       |   |-- model
    |       |   |   |-- Achievement.java
    |       |   |   |-- Activity.java
    |       |   |   |-- Challenge.java
    |       |   |   |-- User.java
    |       |   |-- repositroy
    |       |   |   |-- AchievementRepository.java
    |       |   |   |-- ActivityRepository.java
    |       |   |   |-- ChallengeRepository.java
    |       |   |   |-- ComparingRepository.java
    |       |   |   |-- ManageAccountRepository.java
    |       |   |   |-- UserRepository.java
    |       |   |-- service
    |       |   |   |-- AchievementService.java
    |       |   |   |-- ActivityService.java
    |       |   |   |-- ChallengeService.java
    |       |   |   |-- ComparingService.java
    |       |   |   |-- ManageAccountService.java
    |       |   |   |-- UserService.java
    |       |   |-- sql
    |       |   |   |-- create_tables.sql
    |       |   |   |-- insert_user.sql
    |       |   |-- util
    |       |   |   |-- ValidationManager.java
    |       |   |-- CreateTestUsers.java
    |   |-- resources
    |   |   |-- AccountView.fxml
    |   |   |-- ActivityView.fxml
    |   |   |-- ChallengeView.fxml
    |   |   |-- ManageAccountView.fxml
    |   |   |-- RegisterView.fxml
    |   |   |-- WelcomeView.fxml
    |-- test                        # Unit tests
        |-- java
            |-- at.elisabeth_tulla.walk_it_off
                |-- model
                |   |-- UserTest.java
                |-- service
                |   |-- ChallengeServiceTest.java
                |-- util
                    |-- ValidationManagerTest.java 
        
```

## License

See [LICENSE](LICENSE) file for details.

## Contact

For questions or support, please refer to the project documentation.
