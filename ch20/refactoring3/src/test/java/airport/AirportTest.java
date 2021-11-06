package airport;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

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

            @RepeatedTest(5)
            @DisplayName("then we can add him only once to economy flight")
            public void testEconomyFlightRegularPassengerAddOnlyOnce(RepetitionInfo repetitionInfo) {

                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }
                assertAll("verify regular passenger can be added to a flight only once",
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                        () -> assertEquals("Mike",
                                new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName())
                );
            }

            @Test
            @DisplayName("then we can add and remove him from economy flight")
            public void testEconomyFlightRegularPassenger() {

                assertAll("Verify all conditions for regular passenger and economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertEquals("Mike",
                                new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName()),
                        () -> assertTrue(economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersSet().size())
                );
            }
        }

        @DisplayName("When we have a vip passenger")
        @Nested
        class VipPassenger {

            @RepeatedTest(5)
            @DisplayName("then we can add vip passenger to economy flight only once")
            public void testVipPassengerOnlyOnce(RepetitionInfo repetitionInfo) {

                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(james);
                }
                assertAll("verify vip passenger add only once to economy flight",
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(james)),
                        () -> assertEquals("James",
                                new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName())
                );
            }

            @Test
            @DisplayName("then we can add but can`t remove in economy flight")
            public void testEconomyFlightVipPassenger() {

                assertAll("verify all conditions vip passenger and economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertEquals("James",
                                new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName()),
                        () -> assertFalse(economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size())
                );
            }
        }
    }

    @DisplayName("Given there is an business flight")
    @Nested
    class BusinessFlightTest {
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
                        () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                        () -> assertFalse(businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size())
                );
            }
        }

        @Nested
        @DisplayName("when we have a vip passenger")
        class VipPassenger {

            @RepeatedTest(5)
            @DisplayName("we can add vip passenger to business flight only once")
            public void testVipPassengerAddOnlyOnce(RepetitionInfo repetitionInfo) {

                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    businessFlight.addPassenger(james);
                }
                assertAll("verify vip passenger can be added to business flight only once",
                        () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                        () -> assertTrue(businessFlight.getPassengersSet().contains(james)),
                        () -> assertEquals("James",
                                new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName())
                );
            }

            @Test
            @DisplayName("then we can add him but can`t remove from a flight")
            public void testBusinessFlightVipPassenger() {

                assertAll("verify all conditions for vip passenger and business flight",
                        () -> assertTrue(businessFlight.addPassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                        () -> assertFalse(businessFlight.removePassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size())
                );
            }
        }
    }

    @Nested
    @DisplayName("Given there is a premium flight")
    class PremiumFlightTest {

        private Flight premiumFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        public void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When we have a regular passenger")
        class RegularPassengerTest {

            @Test
            @DisplayName("then you cant add nor remove him from flight")
            public void testRegularPassenger() {

                assertAll("verify regular passenger and premium flight",
                        () -> assertFalse(premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                        () -> assertFalse(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }
        }

        @Nested
        @DisplayName("when we have a vip passenger")
        class VipPassenger {

            @RepeatedTest(5)
            @DisplayName("we can add vip passenger to premium flight only once")
            public void testVipPassengerAddOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    premiumFlight.addPassenger(james);
                }
                assertAll("verify vip passenger can add to premium flight only once",
                        () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                        () -> assertTrue(premiumFlight.getPassengersSet().contains(james)),
                        () -> assertEquals("James",
                                new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName())
                );
            }

            @Test
            @DisplayName("then you can add and remove from premium flight")
            public void testVipPassenger() {
                assertAll("verify vip passenger and premium flight",
                        () -> assertTrue(premiumFlight.addPassenger(james)),
                        () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                        () -> assertTrue(premiumFlight.removePassenger(james)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }
        }
    }
}
