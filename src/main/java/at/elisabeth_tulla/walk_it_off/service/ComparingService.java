package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.ComparingRepository;
import at.elisabeth_tulla.walk_it_off.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ComparingService {

    ComparingRepository compRepo =  new ComparingRepository();
    UserRepository userRepo = new UserRepository();

    public Integer getActivityCount(User user, String activity, LocalDateTime startDate, LocalDateTime endDate) {

        Integer count = compRepo.getActivityCount(user, activity, startDate, endDate);

        return count;
    }

    /***
     * STEPS:
     */

    //showStepsSummarized in specific timeframe:
    public Integer sumUpStepsTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        LocalDateTime startDate = startdate.atStartOfDay();
        LocalDateTime endDate = enddate.atTime(23, 59);

        return compRepo.getStepsSumDateToDate(user, startDate, endDate);
    }

    //all Steps Sum overall:
    public Integer sumUpAllSteps(User user){
        return compRepo.getStepsSumAll(user);
    }

    //map of all Steps in specific timeframe:
    public HashMap<LocalDateTime, Integer> mapStepsTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        LocalDateTime startDate = startdate.atStartOfDay();
        LocalDateTime endDate = enddate.atTime(23, 59);

        return compRepo.getStepsDateToDate(user, startDate, endDate);
    }

    /***
     *  RUNS:
     */

    // ran km in timeframe (GUI input):
    public double sumUpKmTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        LocalDateTime startDate = startdate.atStartOfDay();
        LocalDateTime endDate = enddate.atTime(23, 59);

        return compRepo.getKmSumDateToDate(user, startDate, endDate);
    }

    //all Km Sum overall:
    public double sumUpAllKm(User user){
        return compRepo.getKmSumAll(user);
    }

    //map runs in specific timeframe:
    public HashMap<LocalDateTime, Double> mapRunsTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        LocalDateTime startDate = startdate.atStartOfDay();
        LocalDateTime endDate = enddate.atTime(23, 59);

        return compRepo.getRunsDateToDate(user, startDate, endDate);
    }

    /***
     *  LOGGING ACTIVITY: (NICE TO HAVE..)
     *
     *  show how often steps/runs were logged overall ("SELECT COUNT(steps_logged) FROM activity WHERE user_id =?";)
     *     -> maybe achievement: you have been active 100 times! (...), you have been active 10 days in a row!(...)
     *
     *  compare number of runs/walks from timeframe to runs/walks from different timeframe
     *              (this month you went for a run 10 times!, that's 3 more runs than in ...)
     */

    /***
     * COMPARISONS:
     */

    //compare StepsSums from two different timeframes:
    public Integer compareStepsSumTimeframes(User user, LocalDate startdate1, LocalDate enddate1,
                                          LocalDate startdate2, LocalDate enddate2){

        //todo GUI User input choice: compare day to day / week to week / month to month / year to year
        // -> make sure the two timeframes match each other (eg. don't compare a week to a month)

        Integer timeframe1 = sumUpStepsTimeframe(user, startdate1, enddate1);
        Integer timeframe2 = sumUpStepsTimeframe(user, startdate2, enddate2);

        return timeframe2 - timeframe1;

        /*
        //MOVE TO COMPARING CONTROLLER:
        if (differenceSteps == 0){
            *.setText("You have matched your previous results!");
        } else if (differenceSteps <0) {
            *setText("Walk some more steps / Run some more kilometers to match your previous results.");
        } else {
            *setText("You out-walked / -ran yourself! Great Job!");
        }
         */
    }

    //compare KmSums from two different timeframes:
    public double compareRunsSumTimeframes(User user, LocalDate startdate1, LocalDate enddate1,
                                         LocalDate startdate2, LocalDate enddate2) {

        double timeframe1 = sumUpKmTimeframe(user, startdate1, enddate1);
        double timeframe2 = sumUpKmTimeframe(user, startdate2, enddate2);

        return timeframe2 - timeframe1;
    }

    public Integer compareSumUpStepsTimeframeUsers(User currentUser, String email, LocalDate startdate, LocalDate enddate) {

        //fetch other user from DB:
        User otherUser = userRepo.getUser(email);

        Integer currentUserSteps = sumUpStepsTimeframe(currentUser, startdate, enddate);
        Integer otherUserSteps = sumUpStepsTimeframe(otherUser, startdate, enddate);

        return currentUserSteps - otherUserSteps;

        /*
        //MOVE TO COMPARING CONTROLLER:
        if (diffStepsUsers >0){
            *.setText("You walked/ran " + diffSteps + " more steps than " + otherUser.getFirstName());
        } else if (diffStepsUsers == 0){
            *.setText("You have matched your results perfectly!");
        } else {
            Integer diffStepsPositive = diffStepsUsers * (-1);
            *.setText(otherUser.getFirstName() + " walked/ran " + diffStepsPositive + " steps/kilometers more than you.");
        }
         */
    }

    public double compareSumUpKmTimeframeUsers(User currentUser, String email, LocalDate startdate, LocalDate enddate) {

        User otherUser = userRepo.getUser(email);

        double currentUserKms = sumUpKmTimeframe(currentUser, startdate, enddate);
        double otherUserKms = sumUpKmTimeframe(otherUser, startdate, enddate);

        return currentUserKms - otherUserKms;

    }
}
