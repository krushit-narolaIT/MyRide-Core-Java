package com.narola.krushit;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MyRide {
    public static void main(String[] args) throws ParseException {
        // Create Customer
        Customer customer = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");

        //Register customer
        customer.addCustomer(customer);

        //Get customer list
        List<Customer> list = customer.getCustomerList();
        System.out.println("========================   Customer List  =====================");
        for (Customer cust : list) {
            System.out.println(cust);
        }
        System.out.println("==============================================================");
        System.out.println();

        //get customer by id
        System.out.println("=====================   Get Customer By ID  =====================");
        System.out.println(customer.getCustomerByID(3));

        //register driver
        List<Driver> driverList = MyRideController.driverList;
        driverList.add(new Driver(2, "Ravi", "Kumar", new BigInteger("98912412340"), "ravi@gmail.com", "DL14-20224556781", new SuvCar(2, VehicleType.FOURWHEELER.name(), "Diesel", 10), "Surat", "Vadodara", 180.0, false));
        driverList.add(new Driver(3, "Sonu", "Jawan", new BigInteger("85912412340"), "sonu@gmail.com", "DL14-20114556781", new SuvCar(2, VehicleType.FOURWHEELER.name(), "Diesel", 12), "Mumbai", "Hyderabad", 220.0, true));

        //get all driver list
        System.out.println();
        System.out.println("====================       Driver List       ==================");
        System.out.println(driverList);

        //book ride by customer from surat to vadodara
        System.out.println();
        System.out.println();
        List<Driver> availableDrivers = customer.showRiders("surat", 100.0, "vadodara");
        System.out.println("||=========================================================||");
        System.out.println("||========    List of available Drivers    ================||");
        System.out.println("||==========    [Surat --> Vadodara]   ====================||");
        System.out.println("||=========================================================||");

        for (Driver d : availableDrivers) {
            System.out.println("||\t\t\tDrivers :: " + d.getFirstName() + " " + d.getLastName() + " " + d.totalCharge(200) + "₹\t\t\t\t   ||");
        }
        System.out.println("||=========================================================||");

        //book ride by customer from mumbai to hydrabad
        System.out.println();
        System.out.println();
        List<Driver> availableDrivers2 = customer.showRiders("mumbai", 2500.25, "Hyderabad");
        System.out.println("||=========================================================||");
        System.out.println("||========    List of available Drivers    ================||");
        System.out.println("||==========    [Mumbai --> Hyderabad]   ==================||");
        System.out.println("||=========================================================||");
        for (Driver d : availableDrivers2) {
            System.out.println("||\t\t\tDrivers :: " + d.getFirstName() + " " + d.getLastName() + " " + d.totalCharge(2500.25) + "₹\t\t\t\t   ||");
        }
        System.out.println("||=========================================================||");

        System.out.println();
        System.out.println();

        // Create the RideRequest object

        RideRequest request = new RideRequest(
                1,
                "Jaipur",
                "Delhi",
                customer,
                new Date(2010, 1, 3),
                LocalTime.now(),
                LocalTime.of(2, 15, 0),
                100
        );

        System.out.println("========================================");
        Driver assignedDriver = customer.requestForRide(request);
        System.out.println("Driver :: " + assignedDriver.getFirstName() + " " + assignedDriver.getLastName());

        if (assignedDriver == null) {
            System.out.println("Sorry for inconvenience.!!, no driver available for your ride");
        } else {
            System.out.print("\nWaiting for confirmation");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.print(".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (assignedDriver.requestConfirmation()) {
            System.out.println("\nConfirmation received.");
            Ride ride = new Ride(1, "Accepted", request.getPickUpLocation(), request.getDropOffLocation(), customer, assignedDriver, request.getRideRequestDate(), request.getPickUpTime(), request.getDropOffTime(), request.getDistance(), assignedDriver.totalCharge(request.getDistance()));
            MyRideController.printTicket(ride);
            System.out.println("========================================");
        } else {
            System.out.println("\nDriver " + assignedDriver.getFirstName() + " not available for ride");
            System.out.print("\nAssigning new driver");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.print(".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            Driver newAssignedDriver = customer.requestForRide(request);

            if (newAssignedDriver == assignedDriver) {
                System.out.println("Sorry for inconvenience.!!, no driver available for your ride");
            } else {
                if (newAssignedDriver == null) {
                    System.out.println("Sorry for inconvenience.!!, no driver available for your ride");
                } else {
                    System.out.println("New Driver :: " + newAssignedDriver.getFirstName() + " " + newAssignedDriver.getLastName());

                    System.out.print("\nWaiting for confirmation");
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print(".");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (newAssignedDriver.requestConfirmation()) {
                        System.out.println("\nConfirmation received.");
                        Ride ride = new Ride(1, "Accepted", request.getPickUpLocation(), request.getDropOffLocation(), customer, newAssignedDriver, request.getRideRequestDate(), request.getPickUpTime(), request.getDropOffTime(), request.getDistance(), newAssignedDriver.totalCharge(request.getDistance()));

                        MyRideController.printTicket(ride);
                    } else {
                        System.out.println("Sorry for inconvenience.!!, no driver available for your ride");
                    }
                }
            }
        }
    }
}
