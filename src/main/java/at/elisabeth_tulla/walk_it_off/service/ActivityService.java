package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

/***
 * This service creates Activities and checks if they unlocked Achievements.
 */

public class ActivityService {

    ActivityRepository activityRepo = new ActivityRepository();
    AchievementRepository achievementRepo = new AchievementRepository();
    ChallengeService challengeService = new ChallengeService();

    /***
     * This method creates a new Activity Object and hands it over to the ActivityRepository.
     * It also fetches a List of Achievements that have been unlocked with this new logged Activity
     * and unlocks an Achievement and adds it to the List of Achievements,
     * if the logged Activity STEPS exceed or match the value of 10000.
     * @param user User Object
     * @param activity Activity Object
     * @param steps Integer value of STEPS to log as Activity
     * @return List of Achievements that have been unlocked
     */
    public List<Achievement> logWalking(User user, String activity, Integer steps) {

        Activity activity1 = new Activity(user.getId(), activity, steps);

        activityRepo.logActivity(user, activity1);

        List<Achievement> achievements = getAchievementsFromChallenges(user);

        if (steps >= 10000) {
            achievementRepo.unlockAchievement(user, 12);
            achievements.add(achievementRepo.getAchievement(12));
        }

        return achievements;
    }

    /***
     * This method creates a new Activity Object and hands it over to the ActivityRepository.
     * It also fetches a List of Achievements that have been unlocked with this new logged Activity
     * @param user User Object
     * @param activity Activity Object
     * @param distanceInKm double value of KILOMETERS to log as Activity
     * @return List of Achievements that have been unlocked
     */
    public List<Achievement> logRunning(User user, String activity, double distanceInKm) {

        Activity activity1 = new Activity(user.getId(), activity, distanceInKm);

        activityRepo.logActivity(user, activity1);
        return getAchievementsFromChallenges(user);
    }

    /***
     * This method fetches a List of active Challenges from the ChallengeService
     * and hands it back to the ChallengeServicechecks to check for the possible
     * completion of each Challenge. It receives Achievements for every Challenge
     * puts all Achievements (that are not null and therefor unlocked) in a List of unlocked Achievements.
     * @param user User Object
     * @return List of unlocked Achievements
     */
    private List<Achievement> getAchievementsFromChallenges(User user) {

        List<Challenge> activeChallenges = challengeService.getActiveChallenges(user);

        List<Achievement> achievedAchievements = new ArrayList<>();

        //check, if any ChallengeGoal was reached:
        for (Challenge challenge : activeChallenges) {
            Achievement achievement = challengeService.checkChallengeSuccess(user, challenge);

            if (achievement != null) {
                achievedAchievements.add(achievement);
            }
        }
        return achievedAchievements;
    }

}
