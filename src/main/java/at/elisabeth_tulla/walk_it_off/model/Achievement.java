package at.elisabeth_tulla.walk_it_off.model;

import java.sql.Timestamp;

public class Achievement {

    private Integer id;
    private String name;
    private Integer requiredSteps;
    private double requiredKm;
    private Integer requiredDaysActive;
    private String type; //todo challenge OR user      //todo ENUMS:  CHALLENGE, USER
    private Boolean unlocked = false;
    private Timestamp unlockedAt = null;

    public Achievement(String name, Integer requiredSteps, double requiredKm, Integer requiredDays, String type) {
        this.name = name;
        this.requiredSteps = requiredSteps;
        this.requiredKm = requiredKm;
        this.requiredDaysActive = requiredDays;
        this.type = type;
    }

    public Achievement(Integer id, String name, Integer requiredSteps, double requiredKm,
                       Integer requiredDays, String type, Boolean unlocked, Timestamp unlockedAt) {
        this.id = id;
        this.name = name;
        this.requiredSteps = requiredSteps;
        this.requiredKm = requiredKm;
        this.requiredDaysActive = requiredDays;
        this.type = type;
        this.unlocked = unlocked;
        this.unlockedAt = unlockedAt;
    }

    public Achievement(Integer id, String name, Integer requiredSteps, double requiredKm,
                       Integer requiredDaysActive, String type) {
        this.id = id;
        this.name = name;
        this.requiredSteps = requiredSteps;
        this.requiredKm = requiredKm;
        this.requiredDaysActive = requiredDaysActive;
        this.type = type;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getRequiredDaysActive() {
        return requiredDaysActive;
    }

    public void setRequiredDaysActive(Integer requiredDaysActive) {
        this.requiredDaysActive = requiredDaysActive;
    }

    public Timestamp getUnlockedAt() {
        return unlockedAt;
    }

    public void setUnlockedAt(Timestamp unlockedAt) {
        this.unlockedAt = unlockedAt;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requiredSteps=" + requiredSteps +
                ", requiredKm=" + requiredKm +
                ", requiredDaysActive=" + requiredDaysActive +
                ", type='" + type + '\'' + ", unlocked=" + unlocked + ", unlocked at=" + unlockedAt +
                '}';
    }
}
