package com.metro.service;

import com.metro.enums.TicketType;
import com.metro.model.MetroStation;
import com.metro.model.Route;
import com.metro.model.Ticket;

import java.util.*;

public class VendingMachine {
    private Map<String, Route> routes;
    private List<Ticket> purchasedTickets;
    private Scanner scanner;

    public VendingMachine() {
        routes= new HashMap<>();
        purchasedTickets = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeRoutes();
    }

    private void initializeRoutes() {
        Route route1 = new Route("Route 1", Arrays.asList(
             new MetroStation("Station A","A1"),
                new MetroStation("Station B","B1"),
                new MetroStation("Station C","C1")

                ));

        Route route2 = new Route("Route 2", Arrays.asList(
                new MetroStation("Station D","D1"),
                new MetroStation("Station E","E1"),
                new MetroStation("Station F","F1"),
                new MetroStation("Station G","G1")


        ));
        routes.put(route1.getRouteName().toLowerCase(),route1);
        routes.put(route2.getRouteName().toLowerCase(),route2);


    }

    public void start(){
        while(true){
            System.out.println("\nWelcome to the Metro Ticket Vending Machine!");
            System.out.println("Please select the options:");
            System.out.println("1. View Routes");
            System.out.println("2. Purchase ticket");
            System.out.println("3. View purchased tickets");
            System.out.println("4. Exit");

            System.out.println("Enter your choice:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> viewRoutes();
                case "2" -> purchaseTicket();
                case "3" -> viewPurchasedTickets();
                case "4" -> {
                    exitProgram();
                    return;
                }
                default -> System.out.println("Invalid choice.Please try again!");
            }

        }
    }

    private void exitProgram() {
        System.out.println("\nThank you for using the Metro Ticket Vending Machine! Goodbye.");
        scanner.close();


    }

    private void viewPurchasedTickets() {
        if(purchasedTickets.isEmpty()){
            System.out.println("\nNo tickets available!");
        }
        else{
            System.out.println("\nPurchased Tickets:");
            purchasedTickets.forEach(ticket -> {
                        System.out.println("----------------------------");
                        System.out.println(ticket);
                    }
                    );
            System.out.println("----------------------------");
        }
        System.out.println("Press Enter to return to the main menu.");
        scanner.nextLine();
    }

    private void purchaseTicket() {
        try{
            System.out.println("Enter route for journey:");
            String routeName = scanner.nextLine().trim().toLowerCase();

            if(!routes.containsKey(routeName)){
                System.out.println("Route not found . Try again!");
                return;
            }

            Route selectedRoute=routes.get(routeName);

            System.out.println("\nSelect ticket type:");
            System.out.println("1. Single Ride");
            System.out.println("2. Return");
            System.out.println("3. Weekly Pass");
            System.out.println("4. Monthly Pass");
            System.out.print("Enter your choice: ");
            String ticketTypeChoice = scanner.nextLine();

            TicketType ticketType = getTicketTypeFromChoice(ticketTypeChoice);

            System.out.print("\nIs it peak hours? (yes/no): ");
            String peakHourInput = scanner.nextLine().trim().toLowerCase();
            boolean isPeakHour = peakHourInput.equals("yes");

            double fare = calculateFare(selectedRoute, ticketType, isPeakHour);

            System.out.printf("\nThe fare for a %s ticket on %s during %s hours is Rs. %.2f.\n",
                    formatTicketType(ticketType),
                    selectedRoute.getRouteName(),
                    isPeakHour ? "peak" : "off-peak",
                    fare);

            System.out.print("Do you want to purchase this ticket? (yes/no): ");
            String confirmPurchase = scanner.nextLine().trim().toLowerCase();

            if (confirmPurchase.equals("yes")) {
                Ticket ticket = new Ticket(selectedRoute, ticketType, fare, isPeakHour);
                purchasedTickets.add(ticket);
                System.out.println("\nTicket purchased successfully!");
                System.out.println(ticket);
                System.out.println("Press Enter to return to the main menu.");
                scanner.nextLine();
            } else {
                System.out.println("Purchase cancelled. Returning to main menu.");
            }

        }
        catch (Exception e){
            System.out.println("Error occurred while purchasing ticket:"+e.getMessage());
        }

    }

    private String formatTicketType(TicketType ticketType) {
        return switch (ticketType){
            case SINGLE_RIDE -> "Single Ride";
            case RETURN -> "Return";
            case WEEKLY_PASS -> "Weekly Pass";
            case MONTHLY_PASS -> "Monthly Pass";

        };
    }

    private double calculateFare(Route selectedRoute, TicketType ticketType, boolean isPeakHour) {
        int stationCount = selectedRoute.getStations().size();
        double baseFare = stationCount*1.0;

        switch (ticketType){
            case SINGLE_RIDE -> baseFare*=1.0;
            case RETURN -> baseFare*=2.0;
            case WEEKLY_PASS -> baseFare*=5.0;
            case MONTHLY_PASS -> baseFare*=15.0;

        }

        baseFare *= isPeakHour ? 1.2 : 1;

        return  baseFare;



    }

    private TicketType getTicketTypeFromChoice(String ticketTypeChoice) {

        return switch (ticketTypeChoice) {
            case "1" -> TicketType.SINGLE_RIDE;
            case "2" -> TicketType.RETURN;
            case "3" -> TicketType.WEEKLY_PASS;
            case "4" -> TicketType.MONTHLY_PASS;
            default -> null;
        };

    }

    private void viewRoutes() {
        System.out.println("\nAvailable Routes:");
        routes.values().forEach(x->
                {
                    System.out.println(x.getRouteName());
                    x.getStations().forEach(y->
                                    System.out.println(y.getStationName()+" : "+y.getStationCode())
                            );
                }

        );
        System.out.println("Press Enter to return to the main menu.");
        scanner.nextLine();
    }
}
