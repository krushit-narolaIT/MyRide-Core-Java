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

    static List<Driver> driversList;

    static {
        driversList = new ArrayList<>();
        driversList.add(new Driver(1, "Raj", "Master", new BigInteger("98965412340"), "driver@gmail.com", "DL14-20214556781", new SedanCar(1,  VehicleType.FOURWHEELER.name(), "Pertrol", 351.20), "Surat", "Vadodara", 561.2) {
        });
    }

    public Driver(int userID, String firstName, String lastLame, BigInteger phoneNo, String emailID, String licenseNumber, Vehicle vehicle, String pickUpLocation, String dropOffLocation, Double charge) {
        super(userID, firstName, lastLame, phoneNo, emailID);
        this.vehicle = vehicle;
        this.available = true;
        this.licenseNumber = licenseNumber;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
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
        driversList.add(driver);
        System.out.println("Driver registered successfully..!!");
    }

    @Override
    public String toString() {
        return "Driver [" +
                " Driver ID='" + userID + '\'' +
                ", Driver First Name='" + getFirstName() + '\'' +
                ", Driver Last Name='" + getLastLame() + '\'' +
                ", License Number='" + licenseNumber + '\'' +
                ", \n\tAvailability =" + available +
                ", Vehicle=" + vehicle +
                ", Phone No=" + phoneNo +
                ", Email id='" + emailID + '\'' +
                ", \n[ Route :: Pickup location = " + pickUpLocation + " ---> Dropoff Location =" + dropOffLocation +
                " ]";
    }
}