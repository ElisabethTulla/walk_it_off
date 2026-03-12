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
    public List<Achievement> showUserAchievements(User user) {
        return achievementRepository.getUserAchievements(user);
    }

    //show all possible achievements:
    public List<Achievement> showAllAchievements() {
                return achievementRepository.getAllAchievements();
    }
}
