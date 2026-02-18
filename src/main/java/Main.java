import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.AccountService;
import at.elisabeth_tulla.walk_it_off.service.ActivityService;
import at.elisabeth_tulla.walk_it_off.service.ComparingService;
import at.elisabeth_tulla.walk_it_off.service.UserService;

public class Main {

    public static void main(String[] args) {

    UserService userService = new UserService();
    AccountService accountService = new AccountService();
    ActivityService activityService = new ActivityService();
    ComparingService comparingService = new ComparingService();

        /***
         * REGISTER USER
         */
   // userService.registerUser("Elvis", "Tulla", "elvis@tulla.at", "Postgres1!", 2018, 1, 18, "male");

        /***
         * LOGIN
         */
        //userService.login("ofaderbauer@gmail.com", "ofaderbauer@gmail.com");
        //User currentUser = userService.login("tulla.elisabeth@gmx.at", "postgres");
        User currentUser = userService.login("nachi@tulla.at", "Postgres1!");

        System.out.println(currentUser.toString());

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
        //activityService.loggWalking(currentUser, "walking", 4000);
        //activityService.loggRunning(currentUser, "running", 0.5);

        /***
         * SHOW STEPS
         */
        //todo:GUI input als jahr, monat, tag (ev mit Kalender zum auswählen...)
        //comparingService.sumUpStepsTimeframe(currentUser, 2026, 1, 1,
         //       2026, 2, 17);

        //comparingService.mapStepsTimeframe(currentUser, 2026, 1, 1, 2026, 2, 18);

        //comparingService.sumUpAllSteps(currentUser);

        comparingService.compareStepsSumTimeframes(currentUser, 2026, 1, 1,
                2026, 1, 31, 2026, 2, 1,
                2026, 2, 18);

        /***
         * SHOW KM (RUNS)
         */
        //sum up runs in Timeframe:
        comparingService.sumUpKmTimeframe(currentUser, 2026, 1, 1,
                2026, 2, 18);

        //sum up runs Overall:
        comparingService.sumUpAllKm(currentUser);



        /***
         * todo type [ENUMS: WALKING,...]  // könnte auch gender als ENUMS anlegen (ev stattdessen)
         *
         * todo FEAT show and compare stats #21
         * create at.elisabeth_tulla.walk_it_off.service.ComparingService
         * (Methods: showData, compareToDate, compareToUser, compareToGroup)
         *
         * create at.elisabeth_tulla.walk_it_off.repository.ComparingRepository
         * (Methods: readDataFromDB)
         *
         * todo Create Challenges and Achievements
         */


    }

    /***
     *
     * FXML GUI
     *
     * Liste mit Detail Ansicht
     * (Tabelle)
     *
     * ev. Grafiken dazu (eigene Library)
     *
     *
     *
     * eigenes JSOn file importieren für steps input
     * + ev noch andere Formate (XML,...)
     *
     * + ev GAMIN Device XML : (BSP)
     *
     * <?xml version="1.0" encoding="UTF-8"?>
     * <TrainingCenterDatabase xmlns="http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2">
     * <Activities>
     * <at.elisabeth_tulla.walk_it_off.model.Activity Sport="Running">
     * <Id>2023-10-25T10:00:00Z</Id>
     * <Lap StartTime="2023-10-25T10:00:00Z">
     * <TotalTimeSeconds>1800</TotalTimeSeconds>
     * <DistanceMeters>5000</DistanceMeters>
     * <Calories>400</Calories>
     * <Intensity>Active</Intensity>
     * <Track>
     * <Trackpoint>
     * <Time>2023-10-25T10:00:00Z</Time>
     * <Position>
     * <LatitudeDegrees>50.123</LatitudeDegrees>
     * <LongitudeDegrees>8.456</LongitudeDegrees>
     * </Position>
     * <AltitudeMeters>120</AltitudeMeters>
     * <DistanceMeters>0</DistanceMeters>
     * <HeartRateBpm><Value>120</Value></HeartRateBpm>
     * </Trackpoint>
     * <!-- Weitere Trackpoints -->
     * </Track>
     * </Lap>
     * </at.elisabeth_tulla.walk_it_off.model.Activity>
     * </Activities>
     * </TrainingCenterDatabase>
     *
     * -> ev auf JSON file umwandeln und so importieren (ev mit ChatGPT)
     *
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
     */

}
