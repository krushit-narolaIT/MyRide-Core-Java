package com.narola.krushit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

//MyRide application controller
public class MyRideController {

    static List<Customer> customerList;
    static List<Driver> driverList;
    static List<RideRequest> rideRequestsList;
    static List<Ride> rideList;

    static {
        customerList = new ArrayList<>();
        driverList = new ArrayList<>();
        rideRequestsList = new ArrayList<>();
        rideList = new ArrayList<>();

        customerList.add(new Customer(1, "Krushit", "Babariya", new BigInteger("9876543214"), "ksb@narola.email"));
        customerList.add(new Customer(2, "Aarav", "Sharma", new BigInteger("9876543215"), "aarav.sharma@email.com"));
        customerList.add(new Customer(3, "Riya", "Patel", new BigInteger("9876543216"), "riya.patel@email.com"));
        customerList.add(new Customer(4, "Vikram", "Singh", new BigInteger("9876543217"), "vikram.singh@email.com"));

        driverList.add(new Driver(1, "Raj", "Master", new BigInteger("98965412340"), "driver@gmail.com", "DL14-20214556781", new SedanCar(1, VehicleType.FOURWHEELER.name(), "Pertrol", 10), "Surat", "Vadodara", 200.0, true) {
        });
        driverList.add(new Driver(2, "Kunal", "Joshi", new BigInteger("98965412341"), "kunal.joshi@email.com", "DL14-20214556782", new SedanCar(2, VehicleType.FOURWHEELER.name(), "Diesel", 15), "Ahmedabad", "Mumbai", 250.0, true) {
        });
        driverList.add(new Driver(3, "Neha", "Verma", new BigInteger("98965412342"), "neha.verma@email.com", "DL14-20214556783", new SedanCar(3, VehicleType.FOURWHEELER.name(), "Electric", 8), "Pune", "Goa", 300.0, false) {
        });
        driverList.add(new Driver(4, "Ankit", "Mehta", new BigInteger("98965412343"), "ankit.mehta@email.com", "DL14-20214556784", new SedanCar(4, VehicleType.FOURWHEELER.name(), "Pertrol", 12), "Delhi", "Jaipur", 150.0, false) {
        });
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
