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

    //enter Challenge:
    public boolean enterChallenge(User user, Integer challengeID) {

       Challenge currentChallenge = challengeRepo.getChallenge(challengeID);

        return challengeRepo.enterChallenge(user, currentChallenge);
    }

    public boolean checkParticipantsOutmaxed(Integer challengeId) {

        Challenge currentChallenge = challengeRepo.getChallenge(challengeId);

        //check, if Challenge would exceed its maxParticipants:
        Integer numberParticipants = challengeRepo.getParticipantsCount(currentChallenge);

        if (numberParticipants == currentChallenge.getMaxNumberParticipants()) {
            //Objects.equals(numberParticipants, currentChallenge.getMaxNumberParticipants())//todo replace with equals?
            return false;
        }
        return true;
    }

    //get List of all ongoing Challenges:
    public List<Challenge> getOngoingChallenges(User user1) {

        //get HashMap of active ChallengeIDs:
        HashMap<LocalDateTime, Integer> mapOngoingChallenges = challengeRepo.getOngoingChallenges(user1);
        List<Challenge> ongoingChallenges = new ArrayList<>();

        //get active Challenges:
        for (Map.Entry<LocalDateTime, Integer> entry : mapOngoingChallenges.entrySet()) {
            ongoingChallenges.add(challengeRepo.getChallenge(entry.getValue()));
        }
        /*
        if (ongoingChallenges.isEmpty()) {
            System.out.println("There are no active challenges.");
        } else {
            System.out.println("Your active Challenges: \n");
            for (Challenge challenge : ongoingChallenges) {
                System.out.println(challenge);
            }
        }
        
         */
        return ongoingChallenges;
    }

    //check all active Challenges:
    public void checkAllOngoingChallenges(User user1, List<Challenge> ongoingChallenges) {


        for (Challenge challenge : ongoingChallenges) {

            // boolean challengeEnded = checkChallengeEnded(user1, challenge);

            //todo WRONG!!!!! there are no ended Challenges in ongoingChallenges List!!!!
            // It already filtered out the ended Challenges while fetching them from the DB!

            //todo  give update on weather or not it was completed in AccountController (from method checkChallengeEnded)!


            //if (!challengeEnded) {
            //    checkChallengeProgress(user1, challenge);
            //}
        }
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

            System.out.println("You already ran " + sumKM + " km. ");

            if (diffKM <= 0) {
                System.out.println("You already reached your goal!");
            } else {
                System.out.println(diffKM + " km left to run until " + challenge.getEndsAt());
            }
        }
    }

    public Achievement checkChallengeSuccess(User user1, Challenge challenge) {

        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(challenge.getStartedAt().getTime()),
                TimeZone.getDefault().toZoneId());
        LocalDateTime endTime = LocalDateTime.now();

        //check if challenge goal is STEPS:
        if (challenge.getGoalSteps() >= 1) {
            //count sum steps in Timeframe (from DB):
            Integer sumSteps = comparingRepo.getStepsSumDateToDate(user1, startTime, endTime);

            //check, if goal was reached:
            if (sumSteps >= challenge.getGoalSteps()) {

                //get achievement-object:
                Achievement achievement = achievementRepo.getAchievement(challenge.getRewardAchievementID());

                //unlock achievement:
                achievementRepo.unlockAchievement(user1, achievement.getId());

                //deactivate Challenge, because finished
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

                //deactivate Challenge, because finished
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

    //show all Challenges
    public List<Challenge> showAllChallenges() {
        return challengeRepo.getAllChallenges();
    }

    public void createChallenge(String name, Integer reqSteps, double reqKm, Integer requiredAchievementID,
                                Integer minParticipants, Integer maxParticipants, Integer goalSteps, double goalKm,
                                Integer startYear, Integer startMonth, Integer startDay, Integer lastsForDays,
                                Integer rewardAchievementID) {

        //todo check, if Challenge with that name + same startDate already exists

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();

        //convert LocalDateTime to date:
        Instant instant = startDate.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        //LocalDateTime endDate; todo : (check if it works)

        LocalDateTime endDate = LocalDate.of(startYear, startMonth, startDay)
                .plusDays(Math.max(lastsForDays - 1, 0)).atTime(23, 59);

        if (lastsForDays <= 1) {
            endDate = LocalDate.of(startYear, startMonth, startDay).atTime(23, 59);
        } else {
            Calendar c = Calendar.getInstance();

            c.setTime(date);
            c.add(Calendar.DATE, lastsForDays - 1);
            date = c.getTime();

            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            endDate = localDate.atTime(23, 59);


            //LocalDate localDate = LocalDate.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
            //LocalDate localDate = LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
        }

        Integer trueRequiredAchievementID;

        //set default AchievementID if not otherwise specified:
        if (requiredAchievementID == 0) {
            trueRequiredAchievementID = 13;
        } else
            trueRequiredAchievementID = requiredAchievementID;

        //create Challenge - Object:
        Challenge newChallenge = new Challenge(name, reqSteps, reqKm, trueRequiredAchievementID, minParticipants, maxParticipants,
                goalSteps, goalKm, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), rewardAchievementID);

        //create Challenge in DB:
        challengeRepo.createChallenge(newChallenge);
        System.out.println("Challenge created\n");
        System.out.println(newChallenge);
    }

    public List<Challenge> getActiveChallenges(User user) {

        return challengeRepo.getActiveChallenges(user);

    }
}
