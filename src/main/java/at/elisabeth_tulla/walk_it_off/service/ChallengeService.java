package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;
import at.elisabeth_tulla.walk_it_off.repository.ComparingRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class ChallengeService {

    public ChallengeService() {
    }

    ChallengeRepository challengeRepo = new ChallengeRepository();
    AchievementRepository achievementRepo = new AchievementRepository();
    ComparingRepository comparingRepo = new ComparingRepository();

    public Challenge getChallenge(Integer challengeId) {
        return challengeRepo.getChallenge(challengeId);
    }

    public String checkReqAchievements(User user, Integer challengeId) {

        Challenge currentChallenge = challengeRepo.getChallenge(challengeId);

        //check, if user fulfills challenge requirements:
        List<Achievement> userAchievements = achievementRepo.getUserAchievements(user);

        for (Achievement achievement : userAchievements) {
            if (achievement.getId().equals(currentChallenge.getRequiredAchievementID())) {
                return null;
            }
        }

        Achievement requiredAchievement = achievementRepo.getAchievement(currentChallenge.getRequiredAchievementID());

            return requiredAchievement.getName();
    }

    public boolean enterChallenge(User user, Integer challengeID) {

       Challenge currentChallenge = challengeRepo.getChallenge(challengeID);

        return challengeRepo.enterChallenge(user, currentChallenge);
    }

    public boolean checkParticipantsOutmaxed(Integer challengeId) {

        Challenge currentChallenge = challengeRepo.getChallenge(challengeId);

        Integer numberParticipants = challengeRepo.getParticipantsCount(currentChallenge);

        if (numberParticipants == currentChallenge.getMaxNumberParticipants()) {
            //Objects.equals(numberParticipants, currentChallenge.getMaxNumberParticipants())//todo FRAGE: replace with equals?
            return false;
        }
        return true;
    }

    //check Progress:
    private void checkChallengeProgress(User user1, Challenge challenge) {

        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(challenge.getStartedAt().getTime()),
                TimeZone.getDefault().toZoneId());
        LocalDateTime nowTime = LocalDateTime.ofInstant(Instant.now(), TimeZone.getDefault().toZoneId());

        //check if challenge goal is STEPS:
        if (challenge.getGoalSteps() >= 1) {

            Integer sumSteps = comparingRepo.getStepsSumDateToDate(user1, startTime, nowTime);
            Integer diffSteps = challenge.getGoalSteps() - sumSteps;

            System.out.println("You have already walked " + sumSteps + " steps. " + diffSteps +
                    " steps left to walk until " + challenge.getEndsAt());

        } else if (challenge.getGoalDistanceKm() >= 1) {

            double sumKM = comparingRepo.getKmSumDateToDate(user1, startTime, nowTime);
            double diffKM = challenge.getGoalDistanceKm() - sumKM;

            System.out.println("You already ran " + sumKM + " km. " +
                    diffKM + " km left to run until " + challenge.getEndsAt());
        }
    }

    public Achievement checkChallengeSuccess(User user1, Challenge challenge) {

        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(challenge.getStartedAt().getTime()),
                TimeZone.getDefault().toZoneId());
        LocalDateTime endTime = LocalDateTime.now();

        //check if challenge goal is STEPS:
        if (challenge.getGoalSteps() >= 1) {

            Integer sumSteps = comparingRepo.getStepsSumDateToDate(user1, startTime, endTime);

            if (sumSteps >= challenge.getGoalSteps()) {

                Achievement achievement = achievementRepo.getAchievement(challenge.getRewardAchievementID());
                achievementRepo.unlockAchievement(user1, achievement.getId());

                challengeRepo.deactivateChallenge(user1, challenge);

                return achievement;

            } else {
                return null;
            }

        } else if (challenge.getGoalDistanceKm() >= 1) {

            double sumKM = comparingRepo.getKmSumDateToDate(user1, startTime, endTime);

            if (sumKM < challenge.getGoalDistanceKm()) {
                return null;

            } else {

                Achievement achievement = achievementRepo.getAchievement(challenge.getRewardAchievementID());

                achievementRepo.unlockAchievement(user1, achievement.getId());

                challengeRepo.deactivateChallenge(user1, challenge);

                return achievement;
            }
        }
        return null;
    }

    public void updateActiveChallenges(User user1) {

        List<Challenge> activeChallenges = getActiveChallenges(user1);

        for (Challenge challenge : activeChallenges) {
            boolean ended = checkChallengeEnded(challenge);
            if (ended) {
                challengeRepo.deactivateChallenge(user1, challenge);
            }
        }
    }

    public boolean checkChallengeEnded(Challenge challenge) {
        return challenge.getEndsAt().before(Timestamp.from(Instant.now()));
    }

    public List<Challenge> showAllChallenges() {
        return challengeRepo.getAllChallenges();
    }

    public void createChallenge(String name, Integer reqSteps, double reqKm, Integer requiredAchievementID,
                                Integer minParticipants, Integer maxParticipants, Integer goalSteps, double goalKm,
                                LocalDate startdate, Integer lastsForDays,
                                Integer rewardAchievementID) {

        LocalDateTime startDate = startdate.atStartOfDay();

        //convert LocalDateTime to date:
        Instant instant = startDate.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        //LocalDateTime endDate; todo : (check if it works)

        LocalDateTime endDate = startdate
                .plusDays(Math.max(lastsForDays - 1, 0)).atTime(23, 59);

        if (lastsForDays <= 1) {
            endDate = startdate.atTime(23, 59);
        } else {
            Calendar c = Calendar.getInstance();

            c.setTime(date);
            c.add(Calendar.DATE, lastsForDays - 1);
            date = c.getTime();

            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            endDate = localDate.atTime(23, 59);

            //LocalDate localDate = LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
        }

        Integer trueRequiredAchievementID;

        if (requiredAchievementID == 0) {
            trueRequiredAchievementID = 13;
        } else
            trueRequiredAchievementID = requiredAchievementID;

        Challenge newChallenge = new Challenge(name, reqSteps, reqKm, trueRequiredAchievementID, minParticipants, maxParticipants,
                goalSteps, goalKm, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), rewardAchievementID);

        challengeRepo.createChallenge(newChallenge);
    }

    public List<Challenge> getActiveChallenges(User user) {
        return challengeRepo.getActiveChallenges(user);
    }
}
