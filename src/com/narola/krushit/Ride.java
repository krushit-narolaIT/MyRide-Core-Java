package com.narola.krushit;

import java.time.LocalTime;
import java.util.Date;

public class Ride {
    private int rideID;
    private String rideStatus;
    private String pickUpLocation;
    private String dropOffLocation;
    private Customer customer;
    private Driver driver;
    private Date rideDate;
    private LocalTime pickUpTime;
    private LocalTime dropOffTime;
    private int distance;
    private double totalCost;

    public Ride() {
    }

    public Ride(int rideID, String rideStatus, String pickUpLocation, String dropOffLocation, Customer customer, Driver driver, Date rideDate, LocalTime pickUpTime, LocalTime dropOffTime, int distance, double totalCost) {
        this.rideID = rideID;
        this.rideStatus = rideStatus;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.customer = customer;
        this.driver = driver;
        this.rideDate = rideDate;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.distance = distance;
        this.totalCost = totalCost;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Date getRideDate() {
        return rideDate;
    }

    public void setRideDate(Date rideDate) {
        this.rideDate = rideDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideID=" + rideID +
                ", rideStatus='" + rideStatus + '\'' +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", customer=" + customer +
                ", driver=" + driver +
                ", rideDate=" + rideDate +
                ", pickUpTime=" + pickUpTime +
                ", dropOffTime=" + dropOffTime +
                ", distance=" + distance +
                ", totalCost=" + totalCost +
                '}';
    }
}
