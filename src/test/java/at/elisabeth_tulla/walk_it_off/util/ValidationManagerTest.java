package at.elisabeth_tulla.walk_it_off.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationManagerTest {

    @Test
    void validatePassword() {

        ValidationManager manager = new ValidationManager();

        assertFalse(manager.validatePassword("password"));
        assertFalse(manager.validatePassword("ThisPasswordIsWayTooLong1!"));
        assertFalse(manager.validatePassword("NOLOWERCASE1!"));
        assertFalse(manager.validatePassword("123456789"));

        assertTrue(manager.validatePassword("Password123!"));
        assertTrue(manager.validatePassword("Postgres2?"));
        assertTrue(manager.validatePassword("StrongPassword*8"));

    }
}