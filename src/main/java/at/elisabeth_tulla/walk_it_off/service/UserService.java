package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

public class UserService {

    UserRepository userRepo = new UserRepository();
    AchievementRepository achievementRepo = new AchievementRepository();


    public boolean registerUser(String firstName, String lastName, String email, String password,
            LocalDate birthdayDate, String gender){

            User newUser = new User(firstName, lastName, email, password, birthdayDate, gender);

            boolean registered = userRepo.registerNewUser(newUser);

            achievementRepo.unlockAchievement(newUser, 13);

        return registered;
    }

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
            System.out.println("Hello, " + user1.getFirstName() + "!");

            return user1;
        }
    }
}
