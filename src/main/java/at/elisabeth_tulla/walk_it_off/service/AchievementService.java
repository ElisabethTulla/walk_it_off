package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;

import java.util.List;

public class AchievementService {

    AchievementRepository achievementRepository = new AchievementRepository();

    public Integer createAchievement(String name, Integer requiredSteps, double requiredKm,
                                  Integer requiredDays, String type){

        Achievement newAchievement = new Achievement(name, requiredSteps, requiredKm, requiredDays, type);

        achievementRepository.createAchievement(newAchievement);

        return newAchievement.getId();

    }

    public List<Achievement> showUserAchievements(User user) {
        return achievementRepository.getUserAchievements(user);
    }

    public List<Achievement> showAllAchievements() {
                return achievementRepository.getAllAchievements();
    }
}
