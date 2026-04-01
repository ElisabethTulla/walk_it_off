package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;
import at.elisabeth_tulla.walk_it_off.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

/***
 * This service creates Activities and checks if they unlocked Achievements.
 */

public class ActivityService {

    ActivityRepository loggingRepo = new ActivityRepository();
    AchievementRepository achievementRepo = new AchievementRepository();
    ChallengeService challengeService = new ChallengeService();

    public List<Achievement> loggWalking(User user, String activity, Integer steps) {

        Activity activity1 = new Activity(user.getId(), activity, steps);

        loggingRepo.loggActivity(user, activity1);

        List<Achievement> achievements = getAchievementsFromChallenges(user);

        if (steps >= 10000) {
            achievementRepo.unlockAchievement(user, 12);
            achievements.add(achievementRepo.getAchievement(12));
        }

        return achievements;
    }

    public List<Achievement> loggRunning(User user, String activity, double distanceInKm) {

        Activity activity1 = new Activity(user.getId(), activity, distanceInKm);

        loggingRepo.loggActivity(user, activity1);
        return getAchievementsFromChallenges(user);
    }

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
