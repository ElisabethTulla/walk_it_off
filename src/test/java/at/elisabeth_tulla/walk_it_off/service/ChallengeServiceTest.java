package at.elisabeth_tulla.walk_it_off.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeServiceTest {

    @Test
    void calculateEndDate() {

        ChallengeService service = new ChallengeService();

        LocalDateTime endDate = LocalDateTime.of(2026, 3, 1, 23, 59);

        assertEquals(endDate, service.calculateEndDate(LocalDate.of(2026, 3, 1), 1));

    }
}