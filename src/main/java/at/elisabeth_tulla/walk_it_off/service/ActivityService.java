package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.ActivityRepository;

public class ActivityService {

    ActivityRepository activityRepo = new ActivityRepository();

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
        //create at.elisabeth_tulla.walk_it_off.model.Activity:
        Activity activity1 = new Activity(user.getId(), activity, steps);

        activityRepo.loggActivity(user, activity1);
    }

    public void loggRunning(User user, String activity, double distanceInKm){
        //create at.elisabeth_tulla.walk_it_off.model.Activity:
        Activity activity1 = new Activity(user.getId(), activity, distanceInKm);

        activityRepo.loggActivity(user, activity1);
    }

}
