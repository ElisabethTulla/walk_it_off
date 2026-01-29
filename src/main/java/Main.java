public class Main {

    public static void main(String[] args) {

    UserService userService = new UserService();
    AccountService accountService = new AccountService();

    //userService.registerUser("Nacho", "Tulla", "nacho@tulla.at",
     //       "Postgres1!", 2016, 6, 3, "male");

    //userService.login("ofaderbauer@gmail.com", "ofaderbauer@gmail.com");

    //accountService.deleteUser("ofaderbauer@gmail.com");


        User currentUser = userService.login("nachi@tulla.at", "Postgres1!");
      //  accountService.changePassword(currentUser, "Postgres1!");
       // accountService.changeLastName(currentUser, "Tulla");
        //accountService.changeFirstName(currentUser, "Nachi");
        //accountService.changeEmail(currentUser, "nachi@tulla.at");
        System.out.println(currentUser.toString());


    }


}
