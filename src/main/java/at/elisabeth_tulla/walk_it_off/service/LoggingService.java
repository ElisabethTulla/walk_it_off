package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;
import at.elisabeth_tulla.walk_it_off.repository.LoggingRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class LoggingService {

    LoggingRepository loggingRepo = new LoggingRepository();
    ChallengeRepository challengeRepo = new ChallengeRepository();
    AchievementRepository achievementRepo = new AchievementRepository();

    public String checkActivity(String activityName) {

        return switch (activityName.toUpperCase()) {
            case "WALKING" -> "GUI pop-up for user input walking";
            case "RUNNING" -> "GUI pop-up for user input running";
            case "BIKING" -> "GUI pop-up for user input biking";
            case "SWIMMING" -> "GUI pop-up for user input swimming";
            default -> "Please choose an activity.";
        };
    }

    public void loggWalking(User user, String activity, Integer steps){
        //create Activity:
        Activity activity1 = new Activity(user.getId(), activity, steps);

        if (steps >= 10000){
            achievementRepo.unlockAchievement(user, 12);
            System.out.println("Congratulations! You unlocked 10.000 steps today!");
        }

        //todo check for active Challenges
        HashMap<LocalDateTime, Integer> activeChallenges = checkActiveChallenges(user);

        //todo for each Challenge in List of Challenges:

        // todo ----> ob die Challenge geschafft wurde (und das Achievement geearned wurde),
        //  wird anhand der logged activity aus tabelle activity und dem dazugehörigen Zeitraum berechnet
        //   ---> check if completed (if yes -> log to unser_achievement as unlocked AND to user_challenge as active = false)

        loggingRepo.loggActivity(user, activity1);
    }

    public void loggRunning(User user, String activity, double distanceInKm){
        //create Activity:
        Activity activity1 = new Activity(user.getId(), activity, distanceInKm);

        //todo check for active Challenges
        HashMap<LocalDateTime, Integer> activeChallenges = checkActiveChallenges(user);

        //todo for each Challenge in List of Challenges:
        // ---> log km to active Challenges
        // ---> check if completed (if yes -> log to unser_achievement)

        loggingRepo.loggActivity(user, activity1);
    }

    public HashMap<LocalDateTime, Integer> checkActiveChallenges(User user){
        return challengeRepo.getActiveChallenges(user);
    }

}
