package airport;

public class Airport {
    public static void main(String[] args) {
        Flight businessFlight = new Flight("1", "Business");
        Flight economyFlight = new Flight("2", "Economy");

        Passenger james = new Passenger("James", true);
        Passenger mike = new Passenger("Mike", false);

        businessFlight.addPassenger(james);
        businessFlight.removePassenger(james);
        businessFlight.addPassenger(mike);
        economyFlight.addPassenger(mike);

        System.out.println("List of business passengers: ");
        for (Passenger p : businessFlight.getPassengers()) {
            System.out.println(p.getName());
        }

        System.out.println("List of economy passengers: ");
        for (Passenger p : economyFlight.getPassengers()) {
            System.out.println(p.getName());
        }
    }
}
