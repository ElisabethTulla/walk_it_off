package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.repository.ChallengeRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ChallengeService {

    public ChallengeService (){}

    ChallengeRepository challengeRepo = new ChallengeRepository();

    //todo enterChallenge(User user, Challenge challenge)

    //todo getActiveChallenges(User user)

    //todo checkProgress(User user, Challenge challenge)

    //todo show all Challenges + attached Achievements


    public void createChallenge(String name, Integer reqSteps, double reqKm, Achievement reqAchievement,
                                Integer minParticipants, Integer maxParticipants, Integer goalSteps, double goalKm,
                                Integer startYear, Integer startMonth, Integer startDay, Integer lastsForDays,
                                Achievement rewardAchievement) {

        LocalDateTime startDate = LocalDate.of(startYear, startMonth, startDay).atStartOfDay();
        LocalDateTime endDate;
        Date date = new Date();

        if (lastsForDays <= 1){
            endDate = LocalDate.of(startYear, startMonth, startDay).atTime(23, 59);
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, startDay);
            date = c.getTime();

            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            endDate = localDate.atTime(23, 59);
        }

        //create Challenge - Object:
        Challenge newChallenge = new Challenge(name, reqSteps, reqKm, reqAchievement, minParticipants, maxParticipants,
                goalSteps, goalKm, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), rewardAchievement);

        //create Challenge in DB:
        challengeRepo.createChallenge(newChallenge);
        System.out.println("Challenge created");

        //----->> todo WRITE EXTERNAL METHOD for DATE FORMATING .... (also for use in ComparingService)????????
    }


    //todo deleteChallenge(Challenge challenge)



}
