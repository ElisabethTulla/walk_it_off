package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

/***
 * This service creates Users and hands over Users to the Graphical User Interface.
 */

public class UserService {

    UserRepository userRepo = new UserRepository();
    AchievementRepository achievementRepo = new AchievementRepository();

    /***
     * This Method creates a new User-Object, hands it over to the UserRepository
     * and unlocks the first Achievement for this User.
     * @param firstName String attribute of User Object
     * @param lastName String attribute of User Object
     * @param email String attribute of User Object
     * @param password String attribute of User Object
     * @param birthdayDate LocalDate attribute of User Object
     * @param gender String attribute of User Object
     */
    public void registerUser(String firstName, String lastName, String email, String password,
            LocalDate birthdayDate, String gender){

            User newUser = new User(firstName, lastName, email, password, birthdayDate, gender);

            userRepo.registerNewUser(newUser);

            achievementRepo.unlockAchievement(newUser, 13);
    }

    /***
     * This Method fetches a User from the UserRepository using the email and creates a User-Object.
     * It then checks if the User-Object is null and if the password is correct.
     * @param email String attribute of User Object
     * @param password String attribute of User Object
     * @return User-Object
     */
    public User login(String email, String password){

        User user1 = userRepo.getUser(email);

        if (user1 == null){
            //todo Logger log to console
            System.out.println("No user with this e-mail address was found.");
            return null;
        }

        boolean correctPassword = userRepo.checkPassword(user1, password);

        if(!correctPassword){
            //todo Logger logging to console...
            System.out.println("Invalid password!");
            return null;
        } else {
            return user1;
        }
    }
}
