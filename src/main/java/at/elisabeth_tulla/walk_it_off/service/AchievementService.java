package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;

import java.util.List;

/***
 * This service creates Achievements and hands over lists of Achievements to the Graphical User Interface.
 */

public class AchievementService {

    AchievementRepository achievementRepository = new AchievementRepository();

    /***
     * This method creates an Achievement Object and hands it over to the AchievementRepository.
     * @param name String attribute of Achievement Object
     * @param requiredSteps Integer attribute of Achievement Object
     * @param requiredKm double attribute of Achievement Object
     * @param requiredDays Integer attribute of Achievement Object
     * @param type String attribute of Achievement Object (challenge/user)
     * @return Integer ID attribute of Achievement
     */
    public Integer createAchievement(String name, Integer requiredSteps, double requiredKm,
                                  Integer requiredDays, String type){

        Achievement newAchievement = new Achievement(name, requiredSteps, requiredKm, requiredDays, type);

        achievementRepository.createAchievement(newAchievement);

        return newAchievement.getId();
    }

    /***
     * This method fetches a List of User Achievements from the AchievementRepository.
     * @param user User Object
     * @return List of Achievements of User
     */
    public List<Achievement> showUserAchievements(User user) {
        return achievementRepository.getUserAchievements(user);
    }

    /***
     * This method fetches a List of all Achievements from the AchievementRepository.
     * @return List of all Achievements
     */
    public List<Achievement> showAllAchievements() {
                return achievementRepository.getAllAchievements();
    }
}
