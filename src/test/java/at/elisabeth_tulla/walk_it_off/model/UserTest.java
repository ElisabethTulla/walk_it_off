package at.elisabeth_tulla.walk_it_off.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getAge() {

        User testUser = new User("test", "tester", "test@tester.at", "Postgres1!",
                LocalDate.of(1992, 1,1), "female");

        assertEquals(34, testUser.getAge());
    }
}