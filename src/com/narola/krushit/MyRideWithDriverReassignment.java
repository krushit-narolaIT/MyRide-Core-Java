package com.narola.krushit;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MyRideWithDriverReassignment {

    private List<Customer> customerList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();
    private List<RideRequest> rideRequestsList = new ArrayList<>();
    private List<Ride> rideList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();

    public MyRideWithDriverReassignment() {
        initData();
    }

    public void registerDriver(Driver driver) {
        driverList.add(driver);
        System.out.println("Driver Registered Successfully...!!!");
    }

    public void registerCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("Customer Registered Successfully...!!!");
    }

    public void addRoute(Route route) {
        routeList.add(route);
        System.out.println("Route Added Successfully...!!!");
    }

    private double[] isRouteSupported(RideRequest request) {
        double[] fareDetails = new double[2];
        double distance = 0.0;
        boolean isSupported = false;

        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                distance = route.getDistance();
                isSupported = true;
                break;
            }
        }

        if (!isSupported) {
            System.out.println("Service not available for this route.");
            return null;
        }

        double rate = 0.0;
        String type = request.getVehicleType();
        switch (type.toLowerCase()) {
            case "bike":
                rate = new Bike().calculateFare(distance);
                break;
            case "sedan":
                rate = new SedanCar().calculateFare(distance);
                break;
            case "suv":
                rate = new SuvCar().calculateFare(distance);
                break;
            case "autorickshaw":
                rate = new AutoRickshaw().calculateFare(distance);
                break;
            default:
                System.out.println("Invalid vehicle type: " + type);
                return null;
        }

        fareDetails[0] = rate;
        fareDetails[1] = distance;
        return fareDetails;
    }

    private Ride assignDriver(RideRequest request, double[] fareDetails) {
        List<Driver> availableDrivers = getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            System.out.println("No drivers are currently available.");
            return null;
        }

        while (!availableDrivers.isEmpty()) {
            Driver assignedDriver = assignRandomDriver(availableDrivers);
            System.out.println("Attempting with Driver: " + assignedDriver.getFirstName() + " " + assignedDriver.getLastName());

            if (assignedDriver.requestConfirmation(request) && assignedDriver.isAvailable()) {
                System.out.println("Driver accepted the request.");
                Ride ride = new Ride(
                        rideList.size() + 1,
                        "Accepted",
                        request.getPickUpLocation(),
                        request.getDropOffLocation(),
                        request.getCustomer(),
                        assignedDriver,
                        request.getRideRequestDate(),
                        request.getPickUpTime(),
                        request.getDropOffTime(),
                        fareDetails[1],
                        fareDetails[0]
                );
                printTicket(ride);
                return ride;
            } else {
                System.out.println("Driver rejected the request. Searching for another driver.");
                availableDrivers.remove(assignedDriver);
            }
        }

        System.out.println("All drivers rejected the request. Ride request failed.");
        return null;
    }

    private List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverList) {
            if (!driver.isLastTimeRejected()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    private Driver assignRandomDriver(List<Driver> availableDrivers) {
        int randomIndex = (int) (Math.random() * availableDrivers.size());
        return availableDrivers.get(randomIndex);
    }

    public Ride requestForRide(RideRequest rideRequest) {
        double[] fareDetails = isRouteSupported(rideRequest);
        if (fareDetails == null) {
            System.out.println("Sorry, we are unable to proceed with your request.");
            return null;
        }

        return assignDriver(rideRequest, fareDetails);
    }

    public static void printTicket(Ride ride) {
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

    private void initData() {
        // Initialize with sample data if needed
    }

    public static void main(String[] args) {
        MyRideWithDriverReassignment myRide = new MyRideWithDriverReassignment();

        Route route1 = new Route("Surat", "Ahmedabad", 150);
        myRide.addRoute(route1);

        Driver driver1 = new Driver(1, "Raj", "Raja", new BigInteger("9874563214"), "rajraja@gmail.com", "DL14-0123654787");
        myRide.registerDriver(driver1);
        driver1.setAvailable(false);

        Driver driver2 = new Driver(2, "Shri", "Vastava", new BigInteger("5641239874"), "shri@gmail.com", "GJ05-0123654787");
        myRide.registerDriver(driver2);

        Customer customer1 = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");
        myRide.registerCustomer(customer1);

        LocalDate rideDate = LocalDate.parse("2025-01-16");
        RideRequest rideRequest = new RideRequest(
                "Surat",
                "Ahmedabad",
                customer1,
                rideDate,
                LocalTime.now(),
                LocalTime.of(2, 15, 0),
                "suv",
                1
        );

        Ride ride = myRide.requestForRide(rideRequest);
        if (ride != null) {
            System.out.println("Ride successfully booked!");
        } else {
            System.out.println("Ride request failed. Please try again.");
        }
    }
}