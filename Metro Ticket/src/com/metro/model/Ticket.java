package com.metro.model;

import com.metro.enums.TicketType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {

    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");

    private int ticketId;
    private Route route;
    private TicketType ticketType;
    private double fare;
    private LocalDateTime purchaseTime;
    private boolean isPeakHour;

    public Ticket( Route route, TicketType ticketType, double fare,boolean isPeakHour) {
        this.ticketId = idCounter.getAndIncrement();
        this.route = route;
        this.ticketType = ticketType;
        this.fare = fare;
        this.purchaseTime = LocalDateTime.now();
        this.isPeakHour = isPeakHour;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public boolean isPeakHour() {
        return isPeakHour;
    }

    public void setPeakHour(boolean peakHour) {
        isPeakHour = peakHour;
    }

    @Override
    public String toString() {



        return "ticketId=" + ticketId +
                ", route=" + route.getRouteName() +
                ", ticketType=" + ticketType +
                ", fare=" + String.format("%.2f", fare) +
                ", purchaseTime=" + purchaseTime.format(formatter) +
                ", isPeakHour=" + isPeakHour;
    }

}
