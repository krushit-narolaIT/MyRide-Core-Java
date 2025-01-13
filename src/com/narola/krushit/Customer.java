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

    public void requestRide(String pickupLocation, String dropoffLocation, int passengers) {
        System.out.println(getFirstName() + " " + getLastName() + " requested a ride from " + pickupLocation + " to " + dropoffLocation + " for " + passengers + " passengers.");
    }

    public void addCustomer(Customer customer){
        MyRideController.customerList.add(customer);
        System.out.println("User Registered Successfully..!!");
    }

    public List<Customer> getCustomerList(){
        return MyRideController.customerList;
    }

    public Object getCustomerByID(Integer id){
         for(Customer cust : MyRideController.customerList){
             if(cust.getUserID() == id){
                 return cust;
             }
         }
         return null;
    }

    public Object deleteCustomerByID(Integer id){
        for(Customer cust : MyRideController.customerList){
            if(cust.getUserID() == id){
                MyRideController.customerList.remove(cust);
                System.out.println("User Removed Successfully...!!");
            }
        }
        return null;
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

    public List<Driver> showRiders(String pickUpLocation, double distance, String dropOffLocation){
        List<Driver> drivers =  MyRideController.driverList;
        List<Driver> availableDrivers =  new ArrayList<>();

        for(Driver driver: drivers){
            if(driver.getPickUpLocation().equalsIgnoreCase(pickUpLocation) && driver.getDropOffLocation().equalsIgnoreCase(dropOffLocation)){
                availableDrivers.add(driver);
            }
        }

        return availableDrivers;
    }

    public Driver requestForRide(RideRequest request){
        List<Driver> drivers =  MyRideController.driverList;
        List<Driver> availableDrivers =  new ArrayList<>();

        for(Driver driver: drivers){
            if(driver.isAvailable() && driver.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) && driver.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation()) || driver.isAvailableForAnyRide()){
                if(!driver.isLastTimeRejected()){
                    availableDrivers.add(driver);
                }
            }
        }

        availableDrivers.sort((a, b) -> Double.compare(a.getCharge(), b.getCharge()));
        System.out.println("== Available drivers for location ==");
        for(Driver d : availableDrivers){
            System.out.println("Driver :" + d.getFirstName() + " " + d.getLastName());
        }
        System.out.println("=== Assigned Driver ===");
        System.out.println("-> " + availableDrivers.get(0).getFirstName());

        return availableDrivers.isEmpty() ? null : availableDrivers.get(0);
    }

    public Ride bookRide(Driver driver, Date rideDate, Time pickUpTime, Time dropOffTime){
        Ride ride = new Ride();
        ride.setCustomer(this);
        ride.setDriver(driver);


        //ride.setRideID();
        return null;
    }
}
