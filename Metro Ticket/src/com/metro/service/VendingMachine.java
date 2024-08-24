package com.metro.service;

import com.metro.model.MetroStation;
import com.metro.model.Route;
import com.metro.model.Ticket;

import java.sql.SQLOutput;
import java.util.*;

public class VendingMachine {
    private Map<String, Route> routes;
    private List<Ticket> purchasedTicket;
    private Scanner scanner;

    public VendingMachine() {
        routes= new HashMap<>();
        purchasedTicket = new ArrayList<>();
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

            switch (choice){
                case "1":
                    viewRoutes();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid choice.Please try again!");


            }

        }
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
