package at.elisabeth_tulla.walk_it_off;

import at.elisabeth_tulla.walk_it_off.service.*;

import java.time.LocalDate;


public class testUsers {

    static UserService userService = new UserService();

    public static void main(String[] args) {
        //createTestUsers();
    }

    /***
     * REGISTER TEST-USERS:
     */
    public static void createTestUsers() {

        userService.registerUser("Elvis", "Tulla", "elvis@tulla.at",
                "Postgres1!", LocalDate.of(2018, 1, 18), "male");
        userService.registerUser("Felix", "Tulla", "felix@tulla.at",
                "Postgres1!", LocalDate.of(2015, 1, 6), "male");
        userService.registerUser("Elisabeth", "Tulla", "tulla.elisabeth@gmx.at",
                "postgres", LocalDate.of(1992, 1, 16), "female");
        userService.registerUser("Oliver", "Tulla", "oliver@tulla.at",
                "Postgres1!", LocalDate.of(1992, 7, 21), "male");
        userService.registerUser("Nachi", "Tulla", "nachi@tulla.at",
                "Postgres1!", LocalDate.of(2016, 6, 3), "male");
    }

}
