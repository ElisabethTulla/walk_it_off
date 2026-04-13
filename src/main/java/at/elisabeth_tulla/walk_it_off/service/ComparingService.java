package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.ComparingRepository;
import at.elisabeth_tulla.walk_it_off.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/***
 * This service performs calculations and comparisons of User Data.
 */

public class ComparingService {

    ComparingRepository compRepo =  new ComparingRepository();
    UserRepository userRepo = new UserRepository();

    /***
     * This method reformats the LocalDate startdate and enddate to LocalDateTime
     * and hands them over to the ComparingRepository.
     * @param user User Object
     * @param startdate LocalDate that marks the start of the timeframe
     * @param enddate LocalDate that marks the end of the timeframe
     * @return summarized STEPS in specific timeframe as Integer
     */
    public Integer sumUpStepsTimeframe
    (User user, LocalDate startdate, LocalDate enddate) {

        return compRepo.getStepsSumDateToDate
                (user, startdate.atStartOfDay(),
                        enddate.atTime(23, 59));
    }

    /***
     * This method hands over the user to the ComparingRepository.
     * @param user User Object
     * @return summarized STEPS overall as Integer
     */
    public Integer sumUpAllSteps(User user){
        return compRepo.getStepsSumAll(user);
    }

    /***
     * This method reformats the LocalDate startdate and enddate to LocalDateTime
     * and hands them over to the ComparingRepository.
     * @param user User Object
     * @param startdate LocalDate that marks the start of the timeframe
     * @param enddate LocalDate that marks the end of the timeframe
     * @return Map of all STEPS in specific timeframe as HashMap with LocalDateTime as key and Integer as value
     */
    public HashMap<LocalDateTime, Integer> mapStepsTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        return compRepo.getStepsDateToDate(user, startdate.atStartOfDay(), enddate.atTime(23, 59));
    }

    /***
     * This method reformats the LocalDate startdate and enddate to LocalDateTime
     * and hands them over to the ComparingRepository.
     * @param user User Object
     * @param startdate LocalDate that marks the start of the timeframe
     * @param enddate LocalDate that marks the end of the timeframe
     * @return summarized KILOMETER in specific timeframe as double
     */
    public double sumUpKmTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        return compRepo.getKmSumDateToDate(user, startdate.atStartOfDay(), enddate.atTime(23, 59));
    }

    /***
     * This method hands over the user to the ComparingRepository.
     * @param user User Object
     * @return summarized KILOMETER overall as double
     */
    public double sumUpAllKm(User user){
        return compRepo.getKmSumAll(user);
    }

    /***
     * This method reformats the LocalDate startdate and enddate to LocalDateTime
     * and hands them over to the ComparingRepository.
     * @param user User Object
     * @param startdate that marks the start of the timeframe
     * @param enddate that marks the end of the timeframe
     * @return Map of all KILOMETERS in specific timeframe as HashMap with LocalDateTime as key and Double as value
     */
    public HashMap<LocalDateTime, Double> mapKmsTimeframe(User user, LocalDate startdate, LocalDate enddate) {

        return compRepo.getKmsDateToDate(user, startdate.atStartOfDay(), enddate.atTime(23, 59));
    }

    /*
     *  LOGGING ACTIVITY: ( -> NICE TO HAVE...) -> not yet connected to the GUI...
     *
     *  show how often steps/runs were logged overall ("SELECT COUNT(steps_logged) FROM activity WHERE user_id =?";)
     *     -> maybe achievement: you have been active 100 times! (...), you have been active 10 days in a row!(...)
     *
     *  compare number of runs/walks from timeframe to runs/walks from different timeframe
     *              (this month you went for a run 10 times!, that's 3 more runs than in ...)
     */

    /***
     * This method hands over the parameters to the ComparingRepository to receive the counted Activity.
     * @param user User Object
     * @param activity name of the Activity as String
     * @param startDate LocalDateTime that marks the start of the timeframe
     * @param endDate LocalDateTime that marks the end of the timeframe
     * @return counted Activity as Integer
     */
    public Integer getActivityCount(User user, String activity, LocalDateTime startDate, LocalDateTime endDate) {

        Integer count = compRepo.getActivityCount(user, activity, startDate, endDate);

        return count;
    }

    /*
     * COMPARISONS:   -> not yet connected to the GUI...
     */

    /***
     * This method compares the summarized STEPS of two different timeframes.
     * @param user User Object
     * @param startdate1 LocalDate that marks the start of the first timeframe
     * @param enddate1 LocalDate that marks the end of the first timeframe
     * @param startdate2 LocalDate that marks the start of the second timeframe
     * @param enddate2 LocalDate that marks the end of the second timeframe
     * @return the difference in STEPS as Integer
     */
    public Integer compareStepsSumTimeframes(User user, LocalDate startdate1, LocalDate enddate1,
                                          LocalDate startdate2, LocalDate enddate2){

        Integer timeframe1 = sumUpStepsTimeframe(user, startdate1, enddate1);
        Integer timeframe2 = sumUpStepsTimeframe(user, startdate2, enddate2);

        return timeframe2 - timeframe1;

        /*
        //MOVE TO ACCOUNT CONTROLLER:
        if (differenceSteps == 0){
            *.setText("You have matched your previous results!");
        } else if (differenceSteps <0) {
            *setText("Walk some more steps / Run some more kilometers to match your previous results.");
        } else {
            *setText("You out-walked / -ran yourself! Great Job!");
        }
         */
    }

    /***
     * This method compares the summarized KILOMETERS of two different timeframes.
     * @param user User Object
     * @param startdate1 LocalDate that marks the start of the first timeframe
     * @param enddate1 LocalDate that marks the end of the first timeframe
     * @param startdate2 LocalDate that marks the start of the second timeframe
     * @param enddate2 LocalDate that marks the end of the second timeframe
     * @return the difference in KILOMETER as double
     */
    public double compareKmsSumTimeframes(User user, LocalDate startdate1, LocalDate enddate1,
                                          LocalDate startdate2, LocalDate enddate2) {

        double timeframe1 = sumUpKmTimeframe(user, startdate1, enddate1);
        double timeframe2 = sumUpKmTimeframe(user, startdate2, enddate2);

        return timeframe2 - timeframe1;
    }

    /***
     * This method fetches otherUser from UserRepository with the email and creates a User-Object.
     * It then compares the summarized STEPS of both Users.
     * @param currentUser User Object
     * @param email Attribute of User Object from otherUser, to whom the comparison is being made
     * @param startdate LocalDate that marks the start of the timeframe
     * @param enddate LocalDate that marks the end of the timeframe
     * @return the difference in STEPS as Integer
     */
    public Integer compareSumUpStepsTimeframeUsers
    (User currentUser, String email, LocalDate startdate, LocalDate enddate) {

        User otherUser = userRepo.getUser(email);

        Integer currentUserSteps = sumUpStepsTimeframe
                (currentUser, startdate, enddate);
        Integer otherUserSteps = sumUpStepsTimeframe
                (otherUser, startdate, enddate);

        return currentUserSteps - otherUserSteps;
        /*
        //MOVE TO COMPARING CONTROLLER:
        if (diffStepsUsers >0){
            *.setText("You walked/ran " + diffSteps
            + " more steps than " + otherUser.getFirstName());
        } else if (diffStepsUsers == 0){
            *.setText("You have matched your results perfectly!");
        } else {
            Integer diffStepsPositive = diffStepsUsers * (-1);
            *.setText(otherUser.getFirstName() + " walked/ran "
            + diffStepsPositive + " steps/kilometers more than you.");
        }*/
    }

    /***
     * This method fetches otherUser from UserRepository with the email and creates a User-Object.
     * It then compares the summarized KILOMETER of both Users.
     * @param currentUser User Object
     * @param email Attribute of User Object from otherUser, to whom the comparison is being made
     * @param startdate LocalDate that marks the start of the timeframe
     * @param enddate LocalDate that marks the end of the timeframe
     * @return difference in KILOMETERS as double
     */
    public double compareSumUpKmTimeframeUsers(User currentUser, String email, LocalDate startdate, LocalDate enddate) {

        User otherUser = userRepo.getUser(email);

        double currentUserKms = sumUpKmTimeframe(currentUser, startdate, enddate);
        double otherUserKms = sumUpKmTimeframe(otherUser, startdate, enddate);

        return currentUserKms - otherUserKms;

    }
}
