package com.narola.krushit;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Driver extends User {
    private String licenseNumber;
    private boolean available;
    private Vehicle vehicle;
    private boolean isLastTimeRejected;

    public boolean isLastTimeRejected() {
        return isLastTimeRejected;
    }

    public void setLastTimeRejected(boolean lastTimeRejected) {
        isLastTimeRejected = lastTimeRejected;
    }

    public Driver(int userID, String firstName, String lastLame, BigInteger phoneNo, String emailID, String licenseNumber) {
        super(userID, firstName, lastLame, phoneNo, emailID);
        this.available = true;
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

//    public double totalCharge(double distance) {
//        return vehicle.getFareRate() * distance;
//    }

    public void acceptRide() {
        System.out.println(getFirstName() + " accepted the ride.");
        this.available = false;
    }

    public void completeRide() {
        System.out.println(getFirstName() + " completed the ride.");
        this.available = true;
    }

    public void addVehical(Vehicle vehicle){
        this.setVehicle(vehicle);
        System.out.println("Vehicle added successfully...!!");
    }

    @Override
    public String toString() {
        return "Driver [" +
                " Driver ID='" + userID + '\'' +
                ", Driver First Name='" + getFirstName() + '\'' +
                ", Driver Last Name='" + getLastName() + '\'' +
                ", License Number='" + licenseNumber + '\'' +
                ", \n\tAvailability =" + available +
                ", Vehicle=" + vehicle +
                ", Phone No=" + phoneNo +
                ", Email id='" + emailID + '\'' +
                " ]";
    }

    @Override
    public void displayUserRole() {
        System.out.println("Role: Driver");
    }

    @Override
    public String getUserDetails() {
        return "Driver Details: " +
                "Name: " + firstName + " " + lastName +
                ", Phone: " + phoneNo +
                ", License: " + licenseNumber +
                ", Vehicle: " + vehicle.getVehicleType();
    }

    public boolean requestConfirmation(RideRequest request){
        boolean[] acceptance = {false, true, true, true, true};
        int index = (int) (Math.random() * acceptance.length);

        boolean ans = acceptance[index];
        if(!ans){
            this.setLastTimeRejected(true);
            return false;
        }
        return true;
    }

    public static void printTicket(Ride ride){
        System.out.println("\n=========== Ride Details ===============");
        System.out.println("Ride ID            : " + ride.getRideID());
        System.out.println("Pick-Up Location   : " + ride.getPickUpLocation());
        System.out.println("Drop-Off Location  : " + ride.getDropOffLocation());
        System.out.println("Customer           : " + ride.getCustomer().getFirstName() + " " + ride.getCustomer().getLastName());
        System.out.println("Driver             : " + ride.getDriver().getFirstName() + " " + ride.getDriver().getLastName());
        System.out.println("Ride Date          : " + ride.getRideDate());
        System.out.println("Pick-Up Time       : " + ride.getPickUpTime());
        System.out.println("Drop-Off Time      : " + ride.getDropOffTime());
        System.out.println("Distance           : " + ride.getDistance() + " km");
        System.out.println("Total Cost         : $" + String.format("%.2f", ride.getTotalCost()));
        System.out.println("========================================");
    }

}