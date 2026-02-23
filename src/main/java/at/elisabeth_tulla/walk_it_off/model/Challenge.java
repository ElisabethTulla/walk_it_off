package at.elisabeth_tulla.walk_it_off.model;

import java.sql.Timestamp;

public class Challenge {

    private Integer id;
    private String name;
    private Integer requiredSteps = 0;
    private double requiredKm = 0.0;
    private Achievement requiredAchievement = null;
    private Integer minNumberParticipants = 1;
    private Integer maxNumberParticipants = 9999999;
    private Integer goalSteps = 0;
    private double goalDistanceKm = 0.0;
    private Timestamp startedAt;
    private Timestamp endsAt;
    private Achievement rewardAchievement = null;

    public Challenge(String name, Integer reqSteps, double reqKm, Achievement reqAchievement, Integer minParticipants,
                     Integer maxParticipants, Integer goalSteps, double goalKm, Timestamp startDate,
                     Timestamp endDate, Achievement rewardAchievement) {
        this.name = name;
        this.requiredSteps = reqSteps;
        this.requiredKm = reqKm;
        this.requiredAchievement = reqAchievement;
        this.minNumberParticipants = minParticipants;
        this.maxNumberParticipants = maxParticipants;
        this.goalSteps = goalSteps;
        this.goalDistanceKm = goalKm;
        this.startedAt = startDate;
        this.endsAt = endDate;
        this.rewardAchievement = rewardAchievement;
    }

    //todo constructor

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

    public Achievement getRequiredAchievement() {
        return requiredAchievement;
    }

    public void setRequiredAchievement(Achievement requiredAchievement) {
        this.requiredAchievement = requiredAchievement;
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

    public Achievement getRewardAchievement() {
        return rewardAchievement;
    }

    public void setRewardAchievement(Achievement rewardAchievement) {
        this.rewardAchievement = rewardAchievement;
    }
}
