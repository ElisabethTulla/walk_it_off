import java.sql.Date;

public class ComparingService {

    ComparingRepository compRepo =  new ComparingRepository();

    //todo showAllSteps in specific timeframe (timeframe GUI input!)
    public void showSteps(User user, Date startDate, Date endDate) {


        //todo Show in GUI
        System.out.println(compRepo.getSteps(user, startDate, endDate));
    }

    //todo compare allSteps from timeframe to allSteps from different timeframe (GUI input)

    //todo show walked/run distance in timeframe (GUI input)

    //todo show Achievements

    //todo show current Challenges progress

    //todo show all Challenges + attached Achievements

    //todo compare allSteps "currentUser" to allSteps "differentUser" in timeframe (GUI input)

    //todo compare allWalked/run distance "currentUser" to allWwalked/run distance "differentUser" in timeframe (GUI input)



}
