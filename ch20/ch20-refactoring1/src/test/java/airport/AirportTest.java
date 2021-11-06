package airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Given there is an economy flight")
    @Nested
    public class EconomyFlightTest {
        private Flight economyFlight;

        @BeforeEach
        public void setUp() {
            economyFlight = new EconomyFlight("1");
        }

        @Test
        public void testEconomyFlightRegularPassenger() {
            Passenger mike = new Passenger("Mike", false);

            assertEquals("1", economyFlight.getId());
            assertTrue(economyFlight.addPassenger(mike));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("Mike", economyFlight.getPassengers().get(0).getName());
            assertTrue(economyFlight.removePassenger(mike));
            assertEquals(0, economyFlight.getPassengers().size());
        }

        @Test
        public void testEconomyFlightVipPassenger() {
            Passenger james = new Passenger("James", true);

            assertEquals("1", economyFlight.getId());
            assertTrue(economyFlight.addPassenger(james));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("James", economyFlight.getPassengers().get(0).getName());
            assertFalse(economyFlight.removePassenger(james));
            assertEquals(1, economyFlight.getPassengers().size());
        }
    }

    @DisplayName("Given there is an business flight")
    @Nested
    public class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
        }

        @Test
        public void testBusinessFlightRegularPassenger() {
            Passenger mike = new Passenger("Mike", false);

            assertFalse(businessFlight.addPassenger(mike));
            assertEquals(0, businessFlight.getPassengers().size());
            assertFalse(businessFlight.removePassenger(mike));
            assertEquals(0, businessFlight.getPassengers().size());
        }

        @Test
        public void testBusinessFlightVipPassenger() {
            Passenger james = new Passenger("James", true);

            assertTrue(businessFlight.addPassenger(james));
            assertEquals(1, businessFlight.getPassengers().size());
            assertFalse(businessFlight.removePassenger(james));
            assertEquals(1, businessFlight.getPassengers().size());
        }
    }
}
