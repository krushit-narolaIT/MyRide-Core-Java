package com.narola.krushit;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyRide {

    private List<Customer> customerList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();
    private List<RideRequest> rideRequestsList = new ArrayList<>();
    private List<Ride> rideList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();

    public MyRide() {
        initData();
    }

    public void registerDriver(Driver driver){
        driverList.add(driver);
        System.out.println("Driver Registered Successfully...!!!");
    }

    public void registerCustomer(Customer customer){
        customerList.add(customer);
        System.out.println("Customer Registered Successfully...!!!");
    }

    public void addRoute(Route route){
        routeList.add(route);
        System.out.println("Route Added Successfully...!!!");
    }

    public void displayAllCustomer(){
        System.out.println("=======================   Customer List  =====================");
        for (Customer cust : customerList) {
            System.out.println(cust);
        }
        System.out.println("==============================================================");
        System.out.println();
    }

    private double[] isRouteSupported(RideRequest request) {
        System.out.println("In isRouteSupported");
        double[] fareDetails = new double[2];
        double distance = 0.0;
        boolean isSupported = false;

        for (Route route : routeList) {
            //System.out.println("Checking route: " + route.getPickUpLocation() + " -> " + route.getDropOffLocation());
            System.out.println("Pickup in request:: " + request.getPickUpLocation());
            System.out.println("Pickup in route:: " + route.getPickUpLocation());
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                distance = route.getDistance();
                isSupported = true;
                System.out.println("Found :: " + distance + " " + isSupported);
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

        System.out.println("Route supported. Total fare: $" + rate + ", Distance: " + distance + " km");
        return fareDetails;
    }


    private Ride requestToDriver(Driver driver, RideRequest request, double[] arr){
        if(driver.requestConfirmation(request)){
            Ride ride = new Ride(1, "Accepted", request.getPickUpLocation(), request.getDropOffLocation(), request.getCustomer(), driver, request.getRideRequestDate(), request.getPickUpTime(), request.getDropOffTime(), arr[1], arr[0]);
            printTicket(ride);
            return ride;
        } else {
            System.out.println("Sorry..!! Service Unavailable for this route");
            return null;
        }
    }

    public Ride requestForRide(RideRequest rideRequest) {
        double[] fareDetails = isRouteSupported(rideRequest);
        if (fareDetails == null) {
            System.out.println("Sorry, we are unable to proceed with your request.");
            return null;
        }

        List<Driver> availableDrivers = getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            System.out.println("No drivers are currently available.");
            return null;
        }

        Driver assignedDriver = assignRandomDriver(availableDrivers);
        System.out.println("Assigned Driver: " + assignedDriver.getFirstName() + " " + assignedDriver.getLastName());

        Ride ride = requestToDriver(assignedDriver, rideRequest, fareDetails);
        if (ride == null) {
            System.out.println("Ride request failed. The driver rejected the request.");
            return null;
        }

        System.out.println("Ride confirmed successfully!");
        return ride;
    }


    private List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverList) {
            if (driver.isAvailable() && !driver.isLastTimeRejected()) {
                availableDrivers.add(driver);
            }
        }
        if (availableDrivers.isEmpty()) {
            System.out.println("No drivers available for the selected route.");
        }
        return availableDrivers;
    }


    private Driver assignRandomDriver(List<Driver> availableDrivers) {
        int randomIndex = (int) (Math.random() * availableDrivers.size());
        return availableDrivers.get(randomIndex);
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


    private void initData(){

//        customerList.add(new Customer(1, "Krushit", "Babariya", new BigInteger("9876543214"), "ksb@narola.email"));
//        customerList.add(new Customer(2, "Aarav", "Sharma", new BigInteger("9876543215"), "aarav.sharma@email.com"));
//        customerList.add(new Customer(3, "Riya", "Patel", new BigInteger("9876543216"), "riya.patel@email.com"));
//        customerList.add(new Customer(4, "Vikram", "Singh", new BigInteger("9876543217"), "vikram.singh@email.com"));
//
//        driverList.add(new Driver(1, "Raj", "Master", new BigInteger("98965412340"), "driver@gmail.com", "DL14-20214556781",
//                new SedanCar(1, VehicleType.FOURWHEELER.name(), "Petrol", 10, "Long", "Long"), "Surat", "Vadodara", 200.0, true));
//
//        driverList.add(new Driver(2, "Kunal", "Joshi", new BigInteger("98965412341"), "kunal.joshi@email.com", "DL14-20214556782",
//                new SedanCar(2, VehicleType.FOURWHEELER.name(), "Diesel", 15, "Medium", "Long"), "Ahmedabad", "Mumbai", 250.0, true));
//
//        driverList.add(new Driver(3, "Neha", "Verma", new BigInteger("98965412342"), "neha.verma@email.com", "DL14-20214556783",
//                new SedanCar(3, VehicleType.FOURWHEELER.name(), "Electric", 8, "Short", "Long"), "Pune", "Goa", 300.0, false));
//
//        driverList.add(new Driver(4, "Ankit", "Mehta", new BigInteger("98965412343"), "ankit.mehta@email.com", "DL14-20214556784",
//                new SuvCar(4, VehicleType.FOURWHEELER.name(), "Petrol", 12, 'L', 1500, "Short"), "Delhi", "Jaipur", 150.0, false));
    }

    public static void main(String[] args) {

        MyRide myRide = new MyRide();

        //add route
        Route route1 = new Route("Surat", "Ahmedabad", 150);
        myRide.addRoute(route1);

        Route route2 = new Route("Mumbai", "Hydrabad", 150);
        myRide.addRoute(route2);

        System.out.println("route List :: " + myRide.routeList);

        //register driver
        Driver driver1 = new Driver(1, "Raj", "Raja", new BigInteger("9874563214"), "rajraja@gmail.com", "DL14-0123654787");
        myRide.registerDriver(driver1);

        Bike vehicle1 = new Bike(2, "2W", "Petrol");
        driver1.addVehical(vehicle1);

        Driver driver2 = new Driver(2, "Shri", "Vastava", new BigInteger("5641239874"), "shri@gmail.com", "GJ05-0123654787");
        myRide.registerDriver(driver2);

        Vehicle vehicle2 = new AutoRickshaw(3, "3W", "Petrol", "Manual");
        driver2.addVehical(vehicle2);

        // Create Customer
        Customer customer1 = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");

        //Register customer
        myRide.registerCustomer(customer1);

        // Create Customer
        Customer customer2 = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");

        //Register customer
        myRide.registerCustomer(customer2);

        String dateString = "2025-01-16";
        LocalDate rideDate = LocalDate.parse(dateString);

        // Create the RideRequest object
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

        double[] arr = myRide.isRouteSupported(rideRequest);

        myRide.rideRequestsList.add(rideRequest);

        Ride ride = myRide.requestForRide(rideRequest);
        if (ride != null) {
            System.out.println("Ride successfully booked!");
            System.out.println("Driver :: " + ride.getDriver().getFirstName() + " " + ride.getDriver().getLastName());
        } else {
            System.out.println("Ride request failed. Please try again.");
        }
    }
}
