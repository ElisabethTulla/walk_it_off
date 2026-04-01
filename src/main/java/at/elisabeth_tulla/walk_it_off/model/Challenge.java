package at.elisabeth_tulla.walk_it_off.model;

import java.sql.Timestamp;

/***
 * This class serves as a data- and fxml-model for challenges.
 */

public class Challenge {

    private Integer id;
    private String name;
    private Integer requiredSteps = 0;
    private double requiredKm = 0.0;
    private Integer requiredAchievementID = 0;
    private Integer minNumberParticipants = 1;
    private Integer maxNumberParticipants = 9999999;
    private Integer goalSteps = 0;
    private double goalDistanceKm = 0.0;
    private Timestamp startedAt;
    private Timestamp endsAt;
    private Integer rewardAchievementID = 0;

    public Challenge(String name, Integer reqSteps, double reqKm, Integer achievementID, Integer minParticipants,
                     Integer maxParticipants, Integer goalSteps, double goalKm, Timestamp startDate,
                     Timestamp endDate, Integer rewardAchievementID) {
        this.name = name;
        this.requiredSteps = reqSteps;
        this.requiredKm = reqKm;
        this.requiredAchievementID = achievementID;
        this.minNumberParticipants = minParticipants;
        this.maxNumberParticipants = maxParticipants;
        this.goalSteps = goalSteps;
        this.goalDistanceKm = goalKm;
        this.startedAt = startDate;
        this.endsAt = endDate;
        this.rewardAchievementID = rewardAchievementID;
    }

    public Challenge(Integer id, String name, Integer reqSteps, Integer requiredAchievementID, Integer minParticipants,
                     Integer maxParticipants, Integer goalSteps, Integer goalKm, Timestamp startedAt, Timestamp endsAt,
                     Integer rewardAchievementID, Integer reqKm) {
        this.id = id;
        this.name = name;
        this.requiredSteps = reqSteps;
        this.requiredKm = reqKm;
        this.requiredAchievementID = requiredAchievementID;
        this.minNumberParticipants = minParticipants;
        this.maxNumberParticipants = maxParticipants;
        this.goalSteps = goalSteps;
        this.goalDistanceKm = goalKm;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
        this.rewardAchievementID = rewardAchievementID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRequiredSteps() {
        return requiredSteps;
    }

    public void setRequiredSteps(Integer requiredSteps) {
        this.requiredSteps = requiredSteps;
    }

    public double getRequiredKm() {
        return requiredKm;
    }

    public void setRequiredKm(double requiredKm) {
        this.requiredKm = requiredKm;
    }

    public Integer getRequiredAchievementID() {
        return requiredAchievementID;
    }

    public void setRequiredAchievementID(Integer requiredAchievementID) {
        this.requiredAchievementID = requiredAchievementID;
    }

    public Integer getMinNumberParticipants() {
        return minNumberParticipants;
    }

    public void setMinNumberParticipants(Integer minNumberParticipants) {
        this.minNumberParticipants = minNumberParticipants;
    }

    public Integer getMaxNumberParticipants() {
        return maxNumberParticipants;
    }

    public void setMaxNumberParticipants(Integer maxNumberParticipants) {
        this.maxNumberParticipants = maxNumberParticipants;
    }

    public Integer getGoalSteps() {
        return goalSteps;
    }

    public void setGoalSteps(Integer goalSteps) {
        this.goalSteps = goalSteps;
    }

    public double getGoalDistanceKm() {
        return goalDistanceKm;
    }

    public void setGoalDistanceKm(Integer goalDistanceKm) {
        this.goalDistanceKm = goalDistanceKm;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Timestamp endsAt) {
        this.endsAt = endsAt;
    }

    public Integer getRewardAchievementID() {
        return rewardAchievementID;
    }

    public void setRewardAchievementID(Integer rewardAchievementID) {
        this.rewardAchievementID = rewardAchievementID;
    }


    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requiredSteps=" + requiredSteps +
                ", requiredKm=" + requiredKm +
                ", requiredAchievementID=" + requiredAchievementID +
                ", minNumberParticipants=" + minNumberParticipants +
                ", maxNumberParticipants=" + maxNumberParticipants +
                ", goalSteps=" + goalSteps +
                ", goalDistanceKm=" + goalDistanceKm +
                ", startedAt=" + startedAt +
                ", endsAt=" + endsAt +
                ", rewardAchievementID=" + rewardAchievementID +
                '}';
    }
}
