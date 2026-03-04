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

    public ChallengeService() {}

    ChallengeRepository challengeRepo = new ChallengeRepository();
    AchievementRepository achievementRepo = new AchievementRepository();
    ComparingRepository comparingRepo = new ComparingRepository();

    //enter Challenge:
    public void enterChallenge(User user, Integer challengeID) {

        Challenge currentChallenge = challengeRepo.getChallenge(challengeID);

        //check, if user fulfills challenge requirements:
        List<Achievement> userAchievements = achievementRepo.getUserAchievements(user);
        boolean userHasAchievement = false;

        for (Achievement achievement : userAchievements) {
            if (achievement.getId().equals(currentChallenge.getRequiredAchievementID())) {
                userHasAchievement = true;
            }
        }

        if (!userHasAchievement) {
            Achievement requiredAchievement = achievementRepo.getAchievement(currentChallenge.getRequiredAchievementID());

            System.out.println("If you want to participate in this challenge, " +
                    "you first have to earn the following achievement: " + requiredAchievement.getName());
            return;
        }
        //check, if Challenge would exceed its maxParticipants:
        Integer numberParticipants = challengeRepo.getParticipantsCount(currentChallenge);

        if (numberParticipants == currentChallenge.getMaxNumberParticipants()) {
            System.out.println("This Challenge has already reached the maximum amount of participants.");
            return;
        }

        challengeRepo.enterChallenge(user, currentChallenge);
        System.out.println("Challenge accepted! You are now participating in the "
                + currentChallenge.getName() + " challenge.");
    }

    //get List of all active Challenges:
    public List<Challenge> getActiveChallenges(User user1) {

        //get HashMap of active ChallengeIDs:
        HashMap<LocalDateTime, Integer> mapActiveChallenges = challengeRepo.getActiveChallenges(user1);
        List<Challenge> activeChallenges = new ArrayList<>();

        //get active Challenges:
        for (Map.Entry<LocalDateTime, Integer> entry : mapActiveChallenges.entrySet()) {
            activeChallenges.add(challengeRepo.getChallenge(entry.getValue()));
        }
        if (activeChallenges.isEmpty()) {
            System.out.println("There are no active challenges.");
        } else {
            System.out.println("Your active Challenges: \n");
            for (Challenge challenge : activeChallenges) {
                System.out.println(challenge);
            }
        }
        return activeChallenges;
    }

    //check all active Challenges:
    public void checkAllActiveChallenges(User user1, List<Challenge> activeChallenges) {

        for (Challenge challenge : activeChallenges) {

            boolean challengeEnded = checkChallengeEnded(user1, challenge);

            if (!challengeEnded) {
                checkChallengeProgress(user1, challenge);
            }
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

            System.out.println("You have already walked " + sumSteps + " steps. " +  diffSteps +
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

    private boolean checkChallengeEnded(User user1, Challenge challenge) {

        if (challenge.getEndsAt().before(Timestamp.from(Instant.now()))) {
            System.out.println("The challenge " + challenge.getName() + " has ended at " + challenge.getEndsAt());

            LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(challenge.getStartedAt().getTime()),
                    TimeZone.getDefault().toZoneId());
            LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(challenge.getEndsAt().getTime()),
                    TimeZone.getDefault().toZoneId());

            //check if challenge goal is STEPS:
            if (challenge.getGoalSteps() >= 1) {

                //count sum steps in Timeframe (from DB):
                Integer sumSteps = comparingRepo.getStepsSumDateToDate(user1, startTime, endTime);

                //check, if goal was reached:
                if (sumSteps < challenge.getGoalSteps()) {
                    System.out.println("You didn't quite get to the finish line this time.");
                } else {

                    //get achievement-object:
                    Achievement achievement = achievementRepo.getAchievement(challenge.getRewardAchievementID());

                    //unlock achievement:  todo als eigene Methode auslagern (+ km unten)
                    achievementRepo.unlockAchievement(user1, achievement.getId());
                    System.out.println("Congratulations! You unlocked " + achievement.getName() + "!");
                }
            } else if (challenge.getGoalDistanceKm() >= 1) {

                double sumKM = comparingRepo.getKmSumDateToDate(user1, startTime, endTime);

                if (sumKM < challenge.getGoalDistanceKm()) {
                    System.out.println("You didn't quite get to the finish line this time.");
                } else {

                    Achievement achievement = achievementRepo.getAchievement(challenge.getRewardAchievementID());

                    achievementRepo.unlockAchievement(user1, achievement.getId());
                    System.out.println("Congratulations! You unlocked " + achievement.getName() + "!");
                }
            }

            //set active=FALSE in user_challenge: todo eventuell ist "active" eine unnötige Spalte in "user_challenge",
            // todo          weil die Abfrage der aktiven Challenges jetzt JOINed mit challenge Tabelle abläuft
            challengeRepo.deactivateChallenge(user1, challenge);
            return true;
        }
        return false;
    }

    //show all Challenges
    public void showAllChallenges() {
        List<Challenge> allChallenges = challengeRepo.getAllChallenges();
        for (Challenge challenge : allChallenges) {
            System.out.println(challenge);
        }
    }

    public void createChallenge(String name, Integer reqSteps, double reqKm, Integer requiredAchievementID,
                                Integer minParticipants, Integer maxParticipants, Integer goalSteps, double goalKm,
                                Integer startYear, Integer startMonth, Integer startDay, Integer lastsForDays,
                                Integer rewardAchievementID) {

        //todo check, if Challenge with that name + same startDate already exists

        //todo FRAGE: ist unix time besser? bei meiner Lösung hier wird viel herum formatiert... (extra formatier-Methode?)

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

        //create Challenge - Object:
        Challenge newChallenge = new Challenge(name, reqSteps, reqKm, requiredAchievementID, minParticipants, maxParticipants,
                goalSteps, goalKm, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), rewardAchievementID);

        //create Challenge in DB:
        challengeRepo.createChallenge(newChallenge);
        System.out.println("Challenge created\n");
        System.out.println(newChallenge);
    }
}
