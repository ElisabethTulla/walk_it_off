package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.ComparingRepository;
import at.elisabeth_tulla.walk_it_off.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ComparingService {

    ComparingRepository compRepo =  new ComparingRepository();
    UserRepository userRepo = new UserRepository();

    public void getActivityCount(User user, String activity, LocalDateTime startDate, LocalDateTime endDate) {

        Integer count = compRepo.getActivityCount(user, activity, startDate, endDate);

        if (count == null) {
            System.out.println("You didn't log any " + activity );
        } else if (count == 1) {
            System.out.println("You went " + activity + " once.");
        } else
            System.out.println("You went " + activity + " " + count + " times.");
    }

    /***
     * STEPS:
     */

    //showStepsSummarized in specific timeframe (GUI input!):
    public Integer sumUpStepsTimeframe(User user, char activityCounter, Integer startYear, Integer startMonth, Integer startDay,
                                       Integer endYear, Integer endMonth, Integer endDay) {

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(endYear, endMonth, endDay).atTime(23, 59);

        System.out.println("All Steps walked between " + startDate + " and " + endDate + ": "
                + compRepo.getStepsSumDateToDate(user, startDate, endDate));

        // activityCounter:
        if (activityCounter == 'y') {
            getActivityCount(user, "walking", startDate, endDate);
        }

        return compRepo.getStepsSumDateToDate(user, startDate, endDate);
    }

    //all Steps Sum overall:
    public void sumUpAllSteps(User user){
        System.out.println("Overall walked Steps: " + compRepo.getStepsSumAll(user));
    }

    public void mapStepsTimeframe(User user, Integer startYear, Integer startMonth, Integer startDay,
                                   Integer endYear, Integer endMonth, Integer endDay){

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(endYear, endMonth, endDay).atTime(23, 59);

        HashMap<LocalDateTime, Integer> stepsMap = compRepo.getStepsDateToDate(user, startDate, endDate);

        if (!stepsMap.isEmpty()){
            for (Map.Entry<LocalDateTime, Integer> entry : stepsMap.entrySet()) {
                System.out.println(entry.getKey() +  ": " + entry.getValue() + " steps");
            }
        } else
            System.out.println("No steps found");
        //todo show Steps in grafic/table in GUI
    }

    /***
     *  RUNS:
     */

    // ran km in timeframe (GUI input):
    public double sumUpKmTimeframe(User user, char activityCounter, Integer startYear, Integer startMonth, Integer startDay,
                                   Integer endYear, Integer endMonth, Integer endDay) {

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(endYear, endMonth, endDay).atTime(23, 59);

        System.out.println("Kilometers ran between " + startDate + " and " + endDate + ": "
                + compRepo.getKmSumDateToDate(user, startDate, endDate));

        // activityCounter:
        if (activityCounter == 'y') {
            getActivityCount(user, "running", startDate, endDate);
        }

        return compRepo.getKmSumDateToDate(user, startDate, endDate);
    }

    //all Km Sum overall:
    public void sumUpAllKm(User user){
        System.out.println("Overall ran kilometers: " + compRepo.getKmSumAll(user));
    }

    public void mapRunsTimeframe(User user, Integer startYear, Integer startMonth, Integer startDay,
                                  Integer endYear, Integer endMonth, Integer endDay){

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(endYear, endMonth, endDay).atTime(23, 59);

        HashMap<LocalDateTime, Double> runsMap = compRepo.getRunsDateToDate(user, startDate, endDate);

        if (!runsMap.isEmpty()){
            for (Map.Entry<LocalDateTime, Double> entry : runsMap.entrySet()) {
                System.out.println(entry.getKey() +  ": " + entry.getValue() + " kilometers ran");
            }
        } else
            System.out.println("No runs found");
        //todo show Steps in grafic/table in GUI
    }

    /***
     *  LOGGING ACTIVITY:
     */

    //todo show how often steps/runs were logged overall ("SELECT COUNT(steps_logged) FROM activity WHERE user_id =?";)
    // -> maybe achievement: you have been active 100 times! (...), you have been active 10 days in a row!(...)

    //todo compare number of runs/walks from timeframe to runs/walks from different timeframe
    //          (this month you went for a run 10 times!, that's 3 more runs than in ...)


    /***
     * COMPARISONS:
     */

    //compare allStepsSum from timeframe to allSteps from different timeframe (GUI input):
    public void compareStepsSumTimeframes(User user, Integer startYear1, Integer startMonth1, Integer startDay1,
                                          Integer endYear1, Integer endMonth1, Integer endDay1, Integer startYear2, Integer startMonth2, Integer startDay2,
                                          Integer endYear2, Integer endMonth2, Integer endDay2){

        //todo GUI User input choice: compare day to day / week to week / month to month / year to year
        // -> CHANGE PARAMETERS from at.elisabeth_tulla.walk_it_off.model.User input ... is there a simpler way?
        // -> calculate the wright start- and endDates for the selected timeframes (to give to sumUpStepsTimeframe)

        Integer Timeframe1 = sumUpStepsTimeframe(user, 'n', startYear1, startMonth1, startDay1, endYear1, endMonth1, endDay1);
        Integer Timeframe2 = sumUpStepsTimeframe(user, 'n', startYear2, startMonth2, startDay2, endYear2, endMonth2, endDay2);

        if (Timeframe1 == 0 && Timeframe2 == 0){
            System.out.println("Go for a walk and change your future!");
        } else if (Timeframe1.equals(Timeframe2)){
            System.out.println("You have matched your previous results! Keep it up!");
        } else if (Timeframe1 > Timeframe2) {
            System.out.println("Walk some more steps to match your previous results.");
        } else if (Timeframe2 > Timeframe1) {
            System.out.println("You out-walked yourself! Great Job!");
        }
    }

    public void compareRunsSumTimeframes(User user, Integer startYear1, Integer startMonth1, Integer startDay1,
                                         Integer endYear1, Integer endMonth1, Integer endDay1, Integer startYear2, Integer startMonth2, Integer startDay2,
                                         Integer endYear2, Integer endMonth2, Integer endDay2) {

        //todo same as compareStepsSumTimeframes above

        double Timeframe1 = sumUpKmTimeframe(user, 'n', startYear1, startMonth1, startDay1, endYear1, endMonth1, endDay1);
        double Timeframe2 = sumUpKmTimeframe(user, 'n', startYear2, startMonth2, startDay2, endYear2, endMonth2, endDay2);

        if (Timeframe1 == 0 && Timeframe2 == 0){
            System.out.println("Start your next run and change your future!");
        } else if (Timeframe1 == Timeframe2){
            System.out.println("You have matched your previous results! Keep it up!");
        } else if (Timeframe1 > Timeframe2) {
            System.out.println("Run some more kilometers to match your previous results.");
        } else if (Timeframe2 > Timeframe1) {
            System.out.println("You out-ran yourself! Great Job!");
        }
    }

    public void compareSumUpStepsTimeframeUsers(User currentUser, String email, char activityCounter, Integer startYear, Integer startMonth, Integer startDay,
                                                Integer endYear, Integer endMonth, Integer endDay) {

        //fetch other user from DB:
        User otherUser = userRepo.getUser(email);

        System.out.println("Steps walked by " + currentUser.getFirstName() + ":");
        Integer currentUserSteps = sumUpStepsTimeframe(currentUser, activityCounter, startYear, startMonth, startDay, endYear, endMonth, endDay);
        System.out.println("Steps walked by " + otherUser.getFirstName() + ":");
        Integer otherUserSteps = sumUpStepsTimeframe(otherUser, activityCounter, startYear, startMonth, startDay, endYear, endMonth, endDay);

        Integer diffSteps = currentUserSteps - otherUserSteps;

        if (currentUserSteps > otherUserSteps){
            System.out.println("You walked " + diffSteps + " more steps than " + otherUser.getFirstName());
        } else if (currentUserSteps == otherUserSteps){
            System.out.println("You have matched your results perfectly!");
        } else {
            Integer diffStepsPositive = diffSteps * (-1);
            System.out.println(otherUser.getFirstName() + " walked " + diffStepsPositive + " steps more than you.");
        }
    }

    public void compareSumUpKmTimeframeUsers(User currentUser, String email, char activityCounter, Integer startYear, Integer startMonth, Integer startDay,
                                             Integer endYear, Integer endMonth, Integer endDay) {

        //fetch other user from DB:
        User otherUser = userRepo.getUser(email);

        System.out.println("Kilometers ran by " + currentUser.getFirstName() + ":");
        double currentUserKms = sumUpKmTimeframe(currentUser, activityCounter, startYear, startMonth, startDay, endYear, endMonth, endDay);
        System.out.println("Kilometers ran by " + otherUser.getFirstName() + ":");
        double otherUserKms = sumUpKmTimeframe(otherUser, activityCounter, startYear, startMonth, startDay, endYear, endMonth, endDay);

        double diffKms = currentUserKms - otherUserKms;

        if (currentUserKms > otherUserKms){
            System.out.println("You ran " + diffKms + " more kilometers than " + otherUser.getFirstName());
        } else if (currentUserKms == otherUserKms){
            System.out.println("You have matched your results perfectly!");
        } else {
            double diffKmsPositive = diffKms * (-1);
            System.out.println(otherUser.getFirstName() + " ran " + diffKmsPositive + " kilometers more than you.");
        }
    }



    //todo junit tests für methoden, die nicht direkt auf die db zugreifen



}
