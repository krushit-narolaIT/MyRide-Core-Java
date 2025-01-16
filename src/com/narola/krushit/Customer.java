package com.narola.krushit;

import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Customer extends  User{
    public Customer(int userID, String firstName, String lastLame, BigInteger phoneNo, String emailID) {
        super(userID, firstName, lastLame, phoneNo, emailID);
    }

    @Override
    public String toString() {
        return "Customer [" +
                "Customer ID=" + userID +
                ", First Name='" + firstName + '\'' +
                ", Last Lame='" + lastName + '\'' +
                ", Phone No='" + phoneNo + '\'' +
                ", Email ID='" + emailID + '\'' +
                "]";
    }

    @Override
    public void displayUserRole() {
        System.out.println("Role: Customer");
    }

    @Override
    public String getUserDetails() {
        return "Driver Details: " +
                "Name: " + firstName + " " + lastName +
                ", Phone: " + phoneNo;
    }

//    public List<Driver> showRiders(String pickUpLocation, double distance, String dropOffLocation){
//        List<Driver> drivers =  MyRideController.driverList;
//        List<Driver> availableDrivers =  new ArrayList<>();
//
//        for(Driver driver: drivers){
//            if(driver.getPickUpLocation().equalsIgnoreCase(pickUpLocation) && driver.getDropOffLocation().equalsIgnoreCase(dropOffLocation)){
//                availableDrivers.add(driver);
//            }
//        }
//
//        return availableDrivers;
//    }

    public Ride bookRide(Driver driver, Date rideDate, Time pickUpTime, Time dropOffTime){
        Ride ride = new Ride();
        ride.setCustomer(this);
        ride.setDriver(driver);


        //ride.setRideID();
        return null;
    }
}
