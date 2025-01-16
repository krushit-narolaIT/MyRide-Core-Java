package com.narola.krushit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class RideRequest {
    private String rideRequestStatus = "Pending";
    private String pickUpLocation;
    private String dropOffLocation;
    private Customer customer;
    private LocalDate rideRequestDate;
    private LocalTime pickUpTime;
    private LocalTime dropOffTime;
    private String vehicleType;
    private int capacity;

    public RideRequest() {
    }

    public RideRequest(String pickUpLocation, String dropOffLocation,
            Customer customer,LocalDate rideRequestDate, LocalTime pickUpTime, LocalTime dropOffTime, String vehicleType, int capacity) {
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.customer = customer;
        this.rideRequestDate = rideRequestDate;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public LocalDate getRideRequestDate() {
        return rideRequestDate;
    }

    public void setRideRequestDate(LocalDate rideRequestDate) {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RideRequest [RideRequestStatus=" + rideRequestStatus
                + ", pickUpLocation=" + pickUpLocation + ", dropOffLocation=" + dropOffLocation + ", customer="
                + customer + ", rideRequestDate=" + rideRequestDate + ", pickUpTime="
                + pickUpTime + ", dropOffTime=" + dropOffTime + "]";
    }
}
