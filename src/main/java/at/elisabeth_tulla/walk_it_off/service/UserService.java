package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;
import at.elisabeth_tulla.walk_it_off.repository.ComparingRepository;
import at.elisabeth_tulla.walk_it_off.repository.UserRepository;
import at.elisabeth_tulla.walk_it_off.util.ValidationManager;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class UserService {

    UserRepository userRepo = new UserRepository();
    ValidationManager valid = new ValidationManager();
    AchievementRepository achievementRepo = new AchievementRepository();


    public boolean registerUser(String firstName, String lastName, String email, String password,
            LocalDate birthdayDate, String gender){

/*  MOVED TO REGISTER CONTROLLER!!!

        //check if email already exists in the DB:
        boolean emailAlreadyExists = valid.checkEmail(email);

        if (emailAlreadyExists){
            System.out.println("This e-mail is already registered.");
            return;
        }

        //check if password is valid:
        boolean isValidPassword = valid.validatePassword(password);

        if (!isValidPassword){
            System.out.println("The password must be between 8 - 20 characters long, \n" +
                    "must contain at least one digit, one lower case, \n" +
                    "one upper case character and one special character. \n" +
                    "No space between characters.");
            return;
        }
 */

           // Integer age = calculateAge(birthYear, birthMonth, birthDay);
            //todo Integer age = calculateAge(birthdayDate); ... WE DON'T NEED AGE IN DB

            //create user:
            User newUser = new User(firstName, lastName, email, password, birthdayDate, gender);

            //register user in DB:
            boolean registered = userRepo.registerNewUser(newUser);
            System.out.println("Welcome " + newUser.getFirstName() + "!");

            achievementRepo.unlockAchievement(newUser, 13);
        System.out.println("Congratulations! You just made the first step towards your goals!");

        return registered;
    }

    public Integer calculateAge(Integer birthYear, Integer birthMonth, Integer birthDay){
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears();
    }

    public User login(String email, String password){

        //fetch user from DB:
        User user1 = userRepo.getUser(email);

        if (user1 == null){
            System.out.println("No user with this e-mail address was found.");
            return null;
        }

        boolean correctPassword = userRepo.checkPassword(user1, password);
        //Integer invalidPasswordCounter = 0;

        if(!correctPassword){
            System.out.println("Invalid password!");
            return null;
        } else {
            System.out.println("Hello, " + user1.getFirstName() + "!");

            return user1;
        }
    }
}
