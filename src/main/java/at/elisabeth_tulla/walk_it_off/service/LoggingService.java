package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;
import at.elisabeth_tulla.walk_it_off.repository.LoggingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoggingService {

    LoggingRepository loggingRepo = new LoggingRepository();
    ChallengeRepository challengeRepo = new ChallengeRepository();
    AchievementRepository achievementRepo = new AchievementRepository();

    ChallengeService challengeService = new ChallengeService();

    /*
    public String checkActivity(String activityName) {

        return switch (activityName.toUpperCase()) {
            case "WALKING" -> "GUI pop-up for user input walking";
            case "RUNNING" -> "GUI pop-up for user input running";
            case "BIKING" -> "GUI pop-up for user input biking";
            case "SWIMMING" -> "GUI pop-up for user input swimming";
            default -> "Please choose an activity.";
        };
    }

     */

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


    /*
    public HashMap<LocalDateTime, Integer> checkActiveChallenges(User user) {
        return challengeRepo.getOngoingChallenges(user);
    }

     */

}
