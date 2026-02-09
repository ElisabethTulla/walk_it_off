public class Main {

    public static void main(String[] args) {

    UserService userService = new UserService();
    AccountService accountService = new AccountService();
    ActivityService activityService = new ActivityService();

    //userService.registerUser("Felix", "Tulla", "felix@tulla.at",
     //       "Postgres1!", 2015, 1, 6, "male");

    //userService.login("ofaderbauer@gmail.com", "ofaderbauer@gmail.com");

    //accountService.deleteUser("ofaderbauer@gmail.com");


        User currentUser = userService.login("felix@tulla.at", "Postgres1!");
      //  accountService.changePassword(currentUser, "Postgres1!");
       // accountService.changeLastName(currentUser, "Tulla");
        //accountService.changeFirstName(currentUser, "Nachi");
        //accountService.changeEmail(currentUser, "nachi@tulla.at");
        System.out.println(currentUser.toString());
        //System.out.println(currentUser.getAge());

        System.out.println(activityService.checkActivity("walking"));
        //activityService.loggWalking(currentUser, "walking", 10000);
        //activityService.loggRunning(currentUser, "running", 5);

        /***
         * todo type [ENUMS: WALKING,...]
         *
         * todo FEAT show and compare stats #21
         * create ComparingService
         * (Methods: showData, compareToDate, compareToUser, compareToGroup)
         *
         * create ComparingRepository
         * (Methods: readDataFromDB)
         *
         * todo Create Challenges and Achievements
         */


    }


}
