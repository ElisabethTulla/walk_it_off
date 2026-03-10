package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;

import java.util.List;

public class AchievementService {

    AchievementRepository achievementRepository = new AchievementRepository();


    //create Achievement
    public void createAchievement(String name, Integer requiredSteps, double requiredKm,
                                  Integer requiredDays, String type){

        //create Achievement Object
        Achievement newAchievement = new Achievement(name, requiredSteps, requiredKm, requiredDays, type);

        //create Achievement in DB
        achievementRepository.createAchievement(newAchievement);
        System.out.println("New achievement has been created");
        System.out.println(newAchievement);

        //todo return achievement ID !
    }

    //show Achievements from user
    public void showUserAchievements(User user) {
        List<Achievement> unlockedAchievements = achievementRepository.getUserAchievements(user);
        System.out.println("Your achievements: \n");
        for (Achievement a : unlockedAchievements) {
            System.out.println(a);
        }
    }

    //show all possible achievements:
    public void showAllAchievements() {
        List<Achievement> allAchievements = achievementRepository.getAllAchievements();
        System.out.println("List of all achievements: \n");
        for (Achievement a : allAchievements) {
            System.out.println(a);
        }
    }

}
