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

/***
 * This service creates Challenges, enters Users in Challenges and checks on their progress, end and success.
 */

public class ChallengeService {

    public ChallengeService() {
    }

    ChallengeRepository challengeRepo = new ChallengeRepository();
    AchievementRepository achievementRepo = new AchievementRepository();
    ComparingRepository comparingRepo = new ComparingRepository();

    /***
     * This method hands over the challengeId to the ChallengeRepository
     * @param challengeId attribute of Challenge-Object as Integer
     * @return Challenge as Object
     */
    public Challenge getChallenge(Integer challengeId) {
        return challengeRepo.getChallenge(challengeId);
    }

    /***
     * This method receives a Challenge from the ChallengeRepository
     * and a List of all User Achievements from AchievementRepository.
     * It than compares the achievementId-attribute of the Achievements
     * to the requiredAchievementID-Attribute of the Challenge.
     * If there is a match, it fetches the required Achievement from the AchievementRepository
     * @param user User Object
     * @param challengeId Integer attribute from Challenge Object
     * @return requiredAchievement name as String or null if there is no match
     */
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

    /***
     * This method receives the Challenge from the ChallengeRepository
     * and hands the Challenge and the User over to the ChallengeRepository to enter the Challenge.
     * @param user User Object
     * @param challengeID Integer attribute of Challenge Object
     */
    public void enterChallenge(User user, Integer challengeID) {

       Challenge currentChallenge = challengeRepo.getChallenge(challengeID);

        challengeRepo.enterChallenge(user, currentChallenge);
    }

    /***
     * This method receives the Challenge from the ChallengeRepository
     * and hands it over to the ChallengeRepository again in order to receive the number of participants as Integer.
     * It than compares this Integer to the maxNumberParticipants Integer attribute of the Challenge
     * and returns true if these Integers don't match - false if they do match.
     * @param challengeId Integer attribute of Challenge Object
     * @return boolean true, if the number of participants
     * has not yet reached the maxNumberParticipants of this challenge - false if it has.
     */
    public boolean checkParticipantsOutmaxed(Integer challengeId) {

        Challenge currentChallenge = challengeRepo.getChallenge(challengeId);

        Integer numberParticipants = challengeRepo.getParticipantsCount(currentChallenge);

        if (numberParticipants == currentChallenge.getMaxNumberParticipants()) {
            return false;
        }
        return true;
    }

    //check Progress:  (- not yet connected to the GUI...)
    /***
     * This method calculates the LocalDateTime startTime of the startedAt timestamp attribute of the Challenge
     * and the current LocalDateTime and hands it over to the ComparingRepo to receive either the summarized STEPS
     * or the summarized KILOMETERS in that specific timeframe. It than calculates the difference as Integer or double.
     * @param user1 User Object
     * @param challenge Challenge Object
     */
    private void checkChallengeProgress(User user1, Challenge challenge) {

        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(challenge.getStartedAt().getTime()),
                TimeZone.getDefault().toZoneId());
        LocalDateTime nowTime = LocalDateTime.now();

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

    /***
     * This method calculates the LocalDateTime startTime of the startedAt timestamp attribute of the Challenge
     * and the current LocalDateTime and hands it over to the ComparingRepo to receive either the summarized STEPS
     * or the summarized KILOMETERS in that specific timeframe. If this sum exceeds or matches the goalSteps Integer or
     * goalKms double attribute of the Challenge Object, it fetches the reward Achievement of this Challenge
     * and unlocks this Achievement for this User via the AchievementRepository.
     * It than hands over the Challenge and User to the ChallengeRepository to deactivate the Challenge for this User.
     * @param user1 User Object
     * @param challenge Challenge Object
     * @return Achievement Object if the Challenge was completed - null if not.
     */
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

    /***
     * This method fetches a List of all active Challenges of the User
     * and checks via the checkChallengeEnded(challenge) method, if the Challenge has ended.
     * If that is the case, it deactivates the Challenge for the User.
     * @param user1 User Object
     */
    public void updateActiveChallenges(User user1) {

        List<Challenge> activeChallenges = getActiveChallenges(user1);

        for (Challenge challenge : activeChallenges) {
            boolean ended = checkChallengeEnded(challenge);
            if (ended) {
                challengeRepo.deactivateChallenge(user1, challenge);
            }
        }
    }

    /***
     * This method checks if the Challenge ended bevor now.
     * @param challenge Challenge Object
     * @return boolean true if the Challenge has ended - false if it is still ongoing.
     */
    public boolean checkChallengeEnded(Challenge challenge) {
        return challenge.getEndsAt().before(Timestamp.from(Instant.now()));
    }

    /***
     * This method receives a List of Challenges from the ChallengeRepository.
     * @return List of Challenges
     */
    public List<Challenge> showAllChallenges() {
        return challengeRepo.getAllChallenges();
    }

    /***
     * This method calculates a LocalDateTime endDate by adding days to the LocalDate startdate.
     * @param startdate LocalDate marks starting point
     * @param lastsForDays Integer value to add to startdate
     * @return LocalDateTime endDate
     */
    public LocalDateTime calculateEndDate(LocalDate startdate, Integer lastsForDays){

        return startdate.plusDays(Math.max(lastsForDays - 1, 0)).atTime(23, 59);
    }

    /***
     * This method creates a new Challenge Object and hands it to the ChallengeRepository.
     * @param name String attribute of Challenge Object
     * @param reqSteps Integer attribute of Challenge Object
     * @param reqKm double attribute of Challenge Object
     * @param requiredAchievementID Integer attribute of Challenge Object
     * @param minParticipants Integer attribute of Challenge Object
     * @param maxParticipants Integer attribute of Challenge Object
     * @param goalSteps Integer attribute of Challenge Object
     * @param goalKm double attribute of Challenge Object
     * @param startdate LocalDate from DatePicker
     * @param lastsForDays Integer value to add to LocalDate startdate
     * @param rewardAchievementID Integer attribute of Challenge Object
     */
    public void createChallenge(String name, Integer reqSteps, double reqKm, Integer requiredAchievementID,
                                Integer minParticipants, Integer maxParticipants, Integer goalSteps, double goalKm,
                                LocalDate startdate, Integer lastsForDays,
                                Integer rewardAchievementID) {

        LocalDateTime startDate = startdate.atStartOfDay();
        LocalDateTime endDate = calculateEndDate(startdate, lastsForDays);

        Integer trueRequiredAchievementID;

        if (requiredAchievementID == 0) {
            trueRequiredAchievementID = 13;
        } else
            trueRequiredAchievementID = requiredAchievementID;

        Challenge newChallenge = new Challenge(name, reqSteps, reqKm, trueRequiredAchievementID, minParticipants, maxParticipants,
                goalSteps, goalKm, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), rewardAchievementID);

        challengeRepo.createChallenge(newChallenge);
    }

    /***
     * This method fetches a List of active Challenges from ChallengeRepository.
     * @param user User Object
     * @return List of active Challenge Objects
     */
    public List<Challenge> getActiveChallenges(User user) {
        return challengeRepo.getActiveChallenges(user);
    }
}
