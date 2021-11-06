package airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        public void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When we have a regular passenger")
        class RegularPassenger {

            @Test
            @DisplayName("then we can add and remove him from economy flight")
            public void testEconomyFlightRegularPassenger() {

                assertAll("Verify all conditions for regular passenger and economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Mike", economyFlight.getPassengers().get(0).getName()),
                        () -> assertTrue(economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengers().size())
                );
            }
        }

        @DisplayName("When we have a vip passenger")
        @Nested
        class VipPassenger {

            @Test
            @DisplayName("then we can add but can`t remove in economy flight")
            public void testEconomyFlightVipPassenger() {

                assertAll("verify all conditions vip passenger and economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("James", economyFlight.getPassengers().get(0).getName()),
                        () -> assertFalse(economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengers().size())
                );
            }
        }
    }

    @DisplayName("Given there is an business flight")
    @Nested
    public class BusinessFlightTest {
        private Flight businessFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("when we have a regular passenger")
        class RegularPassenger {

            @Test
            @DisplayName("then we cant add or remove him from a business flight")
            public void testBusinessFlightRegularPassenger() {

                assertAll("verify all conditions for regular passenger and vip flight",
                        () -> assertFalse(businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengers().size()),
                        () -> assertFalse(businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("when we have a vip passenger")
        class VipPassenger {

            @Test
            @DisplayName("then we can add him but can`t remove from a flight")
            public void testBusinessFlightVipPassenger() {

                assertAll("verify all conditions for vip passenger and business flight",
                        () -> assertTrue(businessFlight.addPassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengers().size()),
                        () -> assertFalse(businessFlight.removePassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengers().size())
                );
            }
        }
    }
}
