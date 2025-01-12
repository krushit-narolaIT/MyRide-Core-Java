package com.narola.krushit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Driver extends User {
    private String licenseNumber;
    private boolean available;
    private Vehicle vehicle;
    private String pickUpLocation;
    private String dropOffLocation;
    private double charge;
    private boolean isAvailableForAnyRide;
    private boolean isLastTimeRejected;

    public boolean isAvailableForAnyRide() {
        return isAvailableForAnyRide;
    }

    public void setAvailableForAnyRide(boolean availableForAnyRide) {
        isAvailableForAnyRide = availableForAnyRide;
    }

    public boolean isLastTimeRejected() {
        return isLastTimeRejected;
    }

    public void setLastTimeRejected(boolean lastTimeRejected) {
        isLastTimeRejected = lastTimeRejected;
    }

    public Driver(int userID, String firstName, String lastLame, BigInteger phoneNo, String emailID, String licenseNumber, Vehicle vehicle, String pickUpLocation, String dropOffLocation, Double charge, boolean isAvailableForAnyRide) {
        super(userID, firstName, lastLame, phoneNo, emailID);
        this.vehicle = vehicle;
        this.available = true;
        this.licenseNumber = licenseNumber;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.charge = charge;
        this.isAvailableForAnyRide = isAvailableForAnyRide;
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

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double totalCharge(double distance) {
        return vehicle.getFareRate() * distance + this.charge;
    }

    public void acceptRide() {
        System.out.println(getFirstName() + " accepted the ride.");
        this.available = false;
    }

    public void completeRide() {
        System.out.println(getFirstName() + " completed the ride.");
        this.available = true;
    }

    public void registerDriver(Driver driver) {
        MyRideController.driverList.add(driver);
        System.out.println("Driver registered successfully..!!");
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
                ", \n[ Route :: Pickup location = " + pickUpLocation + " ---> Dropoff Location =" + dropOffLocation +
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

    public boolean requestConfirmation(){
        boolean[] acceptance = {false, true, true, true, true};
        int index = (int) (Math.random() * acceptance.length);

        boolean ans = acceptance[index];
        if(!ans){
            this.setLastTimeRejected(true);
        }
        return ans;
    }


}