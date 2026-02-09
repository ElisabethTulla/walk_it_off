import java.sql.Timestamp;

public class Activity {

    //Attributes: type [ENUMS: WALKING,...], stepsLogged, distanceLoggedKm, loggedAt, stepsAll

    private Integer id;
    private String activityName;
    private Integer steps = null;
    private double distanceInKm = 0;
    private Timestamp loggedAt;
    private Integer stepsAll = null;
    private double distanceInKmAll = 0;

    //constructor walking + running:
    public Activity(Integer id, String activity, Integer steps) {
        this.id = id;
        this.activityName = activity;
        this.steps = steps;
    }

    public Activity(Integer id, String activity, double distanceInKm) {
        this.id = id;
        this.activityName = activity;
        this.distanceInKm = distanceInKm;
    }

    //todo Constructor

    //todo activityName ENUMS: WALKING, RUNNING, BIKING, SWIMMING, ...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public Integer getStepsAll() {
        return stepsAll;
    }

    public void setStepsAll(Integer stepsAll) {
        this.stepsAll = stepsAll;
    }

    public Timestamp getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(Timestamp loggedAt) {
        this.loggedAt = loggedAt;
    }

    public double getDistanceInKmAll() {
        return distanceInKmAll;
    }

    public void setDistanceInKmAll(double distanceInKmAll) {
        this.distanceInKmAll = distanceInKmAll;
    }


    //todo add steps to stepsAll --> in DB ?!?!

    //todo add distanceInKm to distanceInKmAll --> in DB ?!?!



}
