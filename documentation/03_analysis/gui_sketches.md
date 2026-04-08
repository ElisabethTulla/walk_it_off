# GUI Sketches

## Overview

Any sketches of the graphical user interface were drawn by hand.

## Design Principles
- Easy to use
- Intuitive design
- Easy to read fonts and sizes of text, buttons and tables

## Main Views

### View 1: WelcomeView
**Purpose:** This is the first View the user encounters when opening the application.
**User:** End-user

**Components:**
- MenuBar
- Textfield for email
- Textfield for password
- Button for Login
- Hyperlink for Registration

**Interactions:**
- Input of email an password
- Clicking of button "login"
- Clicking of hyperlink "registe new user"

### View 2: RegisterView
**Purpose:** This View enables the user to fill out the form and register as a new user of the application
**User:** End-user

**Components:**
- MenuBar
- Textfields for firstName, lastName, email, password, gender
- Textarea with description of requirements for a valid password
- DatePicker for birthday
- Button "create User"

**Interactions:**
- Input in Textfields
- Clicking of button "create User"

### View 3: AccountView
**Purpose:** This View enables the user to log Activities, enter Challenges and view stats 
(active Challenges, unlocked Achievements, logged Activities in specific timeframe)
**User:** End-user

**Components:**
- MenuBar
- Welcome Text
- Button "log Activity"
- Button "enter Challenge"
- Table "active Challenges"
- Button "Your Achievements"
- Table with unlocked Achievements
- DatePickers for startDate and endDate of timeframe
- RadioButtons with Activities
- Table for Activities
- Button "create User"
- Comparing Features (disabled...)

**Interactions:**
- Button "log Activity"
- Button "enter Challenge"
- Button "Your Achievements"
- DatePickers for startDate and endDate
- RadioButtons "Yours Steps" & "Your Kilometers"
- Comparing Features (disabled...)

### View 4: ActivityView
**Purpose:** This View enables the user to log Activities
**User:** End-user

**Components:**
- MenuBar
- RadioButtons "Steps" & "Kilometers"
- Textfields for value input
- Button "submit"

**Interactions:**
- Input in Textfield
- Clicking of button "submit"
- 
### View 5: ChallengeView
**Purpose:** This View enables the user to enter into Challenges and create new Challenges
**User:** End-user

**Components:**
- MenuBar
- Table "all Challenges"
- Textfield for ChallengeId
- Button "enter Challenge"
- Textfields to create new Achievement and new Challenge
- Button "load new AchievementId"
- Button "create Challenge"
- Table "all Achievements"

**Interactions:**
- Input in Textfield
- Clicking of button "enter Challenge"
- Input in Textfields to create Achievement and Challenge
- Clicking of button "load new AchievementId"
- Clicking of button "create Challenge"

## Navigation Flow
The user navigates between different Views by clicking buttons, hyperlinks or closing pop-ups

## Responsive Design Considerations
The UI keeps to a minimum height and width and uses computed sizes for most components. 
Tables have a minimum height. 

## Accessibility Requirements
- [Requirement 1]
- [Requirement 2]
