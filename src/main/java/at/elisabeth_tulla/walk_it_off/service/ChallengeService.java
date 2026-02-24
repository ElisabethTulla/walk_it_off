package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AchievementRepository;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChallengeService {

    public ChallengeService() {
    }

    ChallengeRepository challengeRepo = new ChallengeRepository();
    AchievementRepository achievementRepo = new AchievementRepository();

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


    //todo getActiveChallenges(User user)

    //todo checkProgress(User user, Challenge challenge)

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

        //todo check, if Challenge with that name already exists

        //todo FRAGE: ist unix time besser? bei meiner Lösung hier wird viel herum formatiert...
        //todo FRAGE: WRITE EXTERNAL METHOD for DATE FORMATING .... (also for use in ComparingService)????????


        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();

        //convert LocalDateTime to date:
        Instant instant = startDate.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        LocalDateTime endDate;

        if (lastsForDays <= 1) {
            endDate = LocalDate.of(startYear, startMonth, startDay).atTime(23, 59);
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, lastsForDays - 1);
            date = c.getTime();

            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            endDate = localDate.atTime(23, 59);
        }

        //create Challenge - Object:
        Challenge newChallenge = new Challenge(name, reqSteps, reqKm, requiredAchievementID, minParticipants, maxParticipants,
                goalSteps, goalKm, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), rewardAchievementID);

        //create Challenge in DB:
        challengeRepo.createChallenge(newChallenge);
        System.out.println("Challenge created\n");
        System.out.println(newChallenge);

    }


    /*
    //todo deleteChallenge(Challenge challenge)
    public void deleteChallenge(Integer challengeID){
        challengeRepo.deleteChallenge(challengeID);
        System.out.println("Challenge deleted\n");
    }
     */


}
