package com.narola.krushit;

import java.time.LocalTime;
import java.util.Date;

public class RideRequest {
    private int rideRequestID;
    private String rideRequestStatus = "Pending";
    private String pickUpLocation;
    private String dropOffLocation;
    private Customer customer;
    private Date rideRequestDate;
    private LocalTime pickUpTime;
    private LocalTime dropOffTime;
    private int distance;

    public RideRequest() {
    }

    public RideRequest(int rideRequestID, String pickUpLocation, String dropOffLocation,
            Customer customer,Date rideRequestDate, LocalTime pickUpTime, LocalTime dropOffTime,
            int distance) {
        this.rideRequestID = rideRequestID;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.customer = customer;
        this.rideRequestDate = rideRequestDate;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.distance = distance;
    }

    public int getRideRequestID() {
        return rideRequestID;
    }

    public void setRideRequestID(int rideRequestID) {
        this.rideRequestID = rideRequestID;
    }

    public String getRideRequestStatus() {
        return rideRequestStatus;
    }

    public void setRideRequestStatus(String rideRequestStatus) {
        this.rideRequestStatus = rideRequestStatus;
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

    public Date getRideRequestDate() {
        return rideRequestDate;
    }

    public void setRideRequestDate(Date rideRequestDate) {
        this.rideRequestDate = rideRequestDate;
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

    @Override
    public String toString() {
        return "RideRequest [rideRequestID=" + rideRequestID + ", rideRequestStatus=" + rideRequestStatus
                + ", pickUpLocation=" + pickUpLocation + ", dropOffLocation=" + dropOffLocation + ", customer="
                + customer + ", rideRequestDate=" + rideRequestDate + ", pickUpTime="
                + pickUpTime + ", dropOffTime=" + dropOffTime + ", distance=" + distance + "]";
    }
}
