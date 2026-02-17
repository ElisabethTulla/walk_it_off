import java.time.LocalDate;
import java.time.LocalDateTime;

public class ComparingService {

    ComparingRepository compRepo =  new ComparingRepository();

    /***
     * STEPS:
     */

    //showStepsSummarized in specific timeframe (GUI input!):
    public Integer showStepsSumDateToDate(User user, Integer startYear, Integer startMonth, Integer startDay,
                                       Integer endYear, Integer endMonth, Integer endDay) {

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(endYear, endMonth, endDay).atTime(23, 59);

        //todo Show in GUI:
        System.out.println("All Steps walked between " + startDate + " and " + endDate + ": "
                + compRepo.getStepsSumDateToDate(user, startDate, endDate));

        return compRepo.getStepsSumDateToDate(user, startDate, endDate);
    }

    //all Steps Sum overall:
    public void showStepsSumAll(User user){
        System.out.println("Overall walked Steps: " + compRepo.getStepsSumAll(user));
    }

    //todo show all Steps (NOT added!) in grafic/table from specific timeframe (GUI input) (Repo: hashmap, hashset?)


    /***
     *  RUNS:
     */

    //todo show ran km in timeframe (GUI input)

    //todo show ran km overall!

    //todo show all runs (NOT added) in grafic/table from specific timeframe

    //todo compare runs from timeframe to runs from different timeframe
    //          (this month you went for a run 10 times!, that's 3 more runs than in ...)

    /***
     *  LOGGING ACTIVITY:
     */

    //todo show how often steps/runs were logged overall ("SELECT COUNT(steps_logged) FROM activity WHERE user_id =?";)
    // -> maybe achievement: you have been active 100 times! (...), you have been active 10 days in a row!(...)

    /***
     * CHALLANGES + ACHIEVEMENTS:
     */

    //todo show Achievements

    //todo show current Challenges progress

    //todo show all Challenges + attached Achievements

    /***
     * COMPARISONS:
     */

    //compare allStepsSum from timeframe to allSteps from different timeframe (GUI input):
    public void compareStepsSumTimeframes(User user, Integer startYear1, Integer startMonth1, Integer startDay1,
                                          Integer endYear1, Integer endMonth1, Integer endDay1, Integer startYear2, Integer startMonth2, Integer startDay2,
                                          Integer endYear2, Integer endMonth2, Integer endDay2){

        //todo GUI User input choice: compare day to day / week to week / month to month / year to year
        // -> CHANGE PARAMETERS from User input ... is there a simpler way?
        // -> calculate the wright start- and endDates for the selected timeframes (to give to showStepsSumDateToDate)

        Integer Timeframe1 = showStepsSumDateToDate(user, startYear1, startMonth1, startDay1, endYear1, endMonth1, endDay1);
        Integer Timeframe2 = showStepsSumDateToDate(user, startYear2, startMonth2, startDay2, endYear2, endMonth2, endDay2);

        if (Timeframe1.equals(Timeframe2)){
            System.out.println("You have matched your previous results! Keep it up!");
        } else if (Timeframe1 > Timeframe2) {
            System.out.println("Walk some more steps to match your previous results.");
        } else
            System.out.println("You have out-walked yourself! Great Job!");

    }

    //todo compare allSteps "currentUser" to allSteps "differentUser" in timeframe (GUI input)

    //todo compare allWalked/run distance "currentUser" to allWwalked/run distance "differentUser" in timeframe (GUI input)



    //todo junit tests für methoden, die nicht direkt auf die db zugreifen



}
