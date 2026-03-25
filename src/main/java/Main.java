import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

    UserService userService = new UserService();
    AccountService accountService = new AccountService();
    LoggingService loggingService = new LoggingService();
    ComparingService comparingService = new ComparingService();
    AchievementService achievementService = new AchievementService();
    ChallengeService challengeService = new ChallengeService();

        /***
         * REGISTER USER
         */
        // userService.registerUser("Elvis", "Tulla", "elvis@tulla.at", "Postgres1!", 2018, 1, 18, "male");

        /***
         * LOGIN
         */
        //User currentUser = userService.login("ofaderbauer@gmail.com", "ofaderbauer@gmail.com");
        //User currentUser = userService.login("tulla.elisabeth@gmx.at", "postgres");
        //User currentUser = userService.login("felix@tulla.at", "Postgres1!");
        //User currentUser = userService.login("nachi@tulla.at", "Postgres1!");
        //User currentUser = userService.login("elvis@tulla.at", "Postgres1!");
        //User currentUser = userService.login("test@test.at", "Postgres1!");

        //System.out.println(currentUser.toString());

        /***
         * ACCOUNT MANAGEMENT
         */
        //  accountService.changePassword(currentUser, "Postgres1!");
       // accountService.changeLastName(currentUser, "Tulla");
        //accountService.changeFirstName(currentUser, "Nachi");
        //accountService.changeEmail(currentUser, "nachi@tulla.at");
        //accountService.deleteUser("ofaderbauer@gmail.com");

        //System.out.println(currentUser.getAge());

        /***
         * LOG ACTIVITY
         */
        //System.out.println(activityService.checkActivity("walking"));
        //loggingService.loggWalking(currentUser, "walking", 10000);
        //loggingService.loggRunning(currentUser, "running", 2.0);

        /***
         * SHOW STEPS
         */
        //todo:GUI input als jahr, monat, tag (ev mit Kalender zum auswählen...) 'y' or 'n' for activityCounter
        //comparingService.sumUpStepsTimeframe(currentUser, 'y', 2026, 1, 1,
          //     2026, 2, 18);

        //comparingService.mapStepsTimeframe(currentUser, 2026, 1, 1, 2026, 2, 18);

        //comparingService.sumUpAllSteps(currentUser);

        //comparingService.compareStepsSumTimeframes(currentUser, 2026, 1, 1,
          //      2026, 1, 31, 2026, 2, 1,
           //     2026, 2, 18);

        //comparingService.compareSumUpStepsTimeframeUsers(currentUser, "felix@tulla.at", 'y',
         //       2026, 1, 1, 2026, 2, 25);
        //todo timeframe als eigenes Object (mit start und end datum -> liefert einen timestamp)

        /***
         * SHOW KM (RUNS)
         */
        //sum up runs in Timeframe:
        //comparingService.sumUpKmTimeframe(currentUser, 'y', 2026, 3, 12, 2026, 3, 12);

        //comparingService.mapRunsTimeframe(currentUser, 2026, 1, 1, 2026, 2, 18);

        //sum up runs Overall:
        //comparingService.sumUpAllKm(currentUser);

        //comparingService.compareRunsSumTimeframes(currentUser, 2026, 1, 1,
          //      2026, 1, 31, 2026, 2, 1,
            //    2026, 2, 18);

        //comparingService.compareSumUpKmTimeframeUsers(currentUser, "felix@tulla.at", 'y',
         //       2026, 1, 1, 2026, 2, 25);

        /***
         * ACHIEVEMENTS
         */

        //create Achievement:
        //achievementService.createAchievement
         //       ("Ran20kmIn1Week", 0, 20, 0, "challenge");

        //show user achievements:
        //achievementService.showUserAchievements(currentUser);

        //show all achievements:
        //achievementService.showAllAchievements();

        /***
         * CHALLENGES
         */

        //show all challenges:
         //challengeService.showAllChallenges();

        //enter Challenge:
        //challengeService.enterChallenge(currentUser, 26);

        //get active Challenges:
        //List<Challenge> activeChallenges = challengeService.getOngoingChallenges(currentUser);

        //check all Challenges:
        //challengeService.checkAllActiveChallenges(currentUser, activeChallenges);

        //create Challenge:
        //challengeService.createChallenge("Walk 10.000 Steps With Me", 0, 0.0, 12,
         //       1, 9999999, 100000, 0,
          //     2026, 3, 12, 1, 12);


        /***
         *
         * todo POP UP ALERT FÜR ACHIEVEMENTS (IN LOGGINGCONTROLLER) KAPUTT!!!!! -> Es sind keine Achievements in der Liste!
         * todo Nur das 10.000 Steps Achievement geht, das im LoggingService hardcodiert zur Liste zugefügt wird...
         *
         *
         *todo NICE TO HAVE:
         *   FEAT show and compare stats #21:
         * - todo compare achievements to other user
         * - todo compare stats to user-group (eg others in your age group, ...)
         *
         * ---------
         *
         * logger statt system.out (kann auch zur konsole ausgeben) error/warning/degub
         *
         * von gui die Integers year, month, day gleich als LocalDateTime umformatieren und so an die Methoden
         * weitergeben (nicht dann in der methode erst umformatiern)
         *
         * todo file io reader for steps input
         *
         * todo class diagramm für db (nice to have)
         *
         * todo junit tests für methoden, die nicht direkt auf die db zugreifen
         *
         * todo type [ENUMS: WALKING,...]  // könnte auch gender als ENUMS anlegen (ev stattdessen)
         *
         * todo log time with runs (also in db) and compare times per km improvements (add maxTime to createChallenge)
         */

    }

    /***
     *

     *
     * Package Struktur:
     *
     *  (mit at.elisabethTulla.walk_it_off. ... beginnen
     *
     * com.training.walkit
     * ├── walk/
     * │   ├── Walk.java            # Entity
     * │   ├── WalkRepository.java
     * │   ├── WalkService.java
     * │   └── WalkResource.java    # REST-Endpoint
     * ├── user/
     * │   ├── at.elisabeth_tulla.walk_it_off.model.User.java
     * │   ├── at.elisabeth_tulla.walk_it_off.repository.UserRepository.java
     * │   ├── at.elisabeth_tulla.walk_it_off.service.UserService.java
     * │   └── UserResource.java
     * ├── route/
     * │   ├── Route.java
     * │   ├── RouteRepository.java
     * │   ├── RouteService.java
     * │   └── RouteResource.java
     * └── shared/
     *     ├── config/
     *     ├── exception/
     *     └── util/
     *
     *
     *     andere Variante:
     *
     * (mit at.elisabethTulla.walk_it_off. ... beginnen
     * ----------------------
     * com.training.walkit
     * ├── model/          # Entities, DTOs
     * ├── repository/     # Datenzugriff (JPA, Panache)
     * ├── service/        # Business-Logik
     * ├── controller/     # REST-Endpoints (JAX-RS / Spring MVC)
     * ├── config/         # Konfigurationsklassen
     * ├── exception/      # Custom Exceptions + Handler
     * └── util/           # Hilfsfunktionen
     *
     *
     *
     *
     * vaadin gui framework für java
     * quakus + spring
     *
     */

}
