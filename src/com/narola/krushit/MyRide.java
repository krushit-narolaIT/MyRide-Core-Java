package com.narola.krushit;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MyRide {
    private List<Customer> customerList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();
    private List<RideRequest> rideRequestsList = new ArrayList<>();
    private List<Ride> rideList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();

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

        Vehicle vehicle1 = new SedanCar(2, "3W", "Petrol", "High", "Modrate", 4);
        driver1.addVehical(vehicle1);

        Driver driver2 = new Driver(2, "Shri", "Vastava", new BigInteger("5641239874"), "shri@gmail.com", "GJ05-0123654787");
        myRide.registerDriver(driver2);

        Vehicle vehicle2 = new SedanCar(3, "3W", "Petrol", "Low", "Modrate", 5);
        driver2.addVehical(vehicle2);

        // Create Customer
        Customer customer1 = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");
        //Register customer
        myRide.registerCustomer(customer1);

        Customer customer2 = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");
        myRide.registerCustomer(customer2);

        //customer request for a ride
        String dateString = "2025-01-16";
        LocalDate rideDate = LocalDate.parse(dateString);
        RideRequest rideRequest = new RideRequest.Builder()
                .setPickUpLocation("Surat")
                .setDropOffLocation("Ahmedabad")
                .setCustomer(customer1)
                .setRideRequestDate(LocalDate.now())
                .setPickUpTime(LocalTime.of(10, 30))
                .setDropOffTime(LocalTime.of(11, 30))
                .setVehicleType("Sedan")
                .setCapacity(4)
                .build();

        //System.out.println(rideRequest);
        double[] arr = myRide.isRouteSupported(rideRequest);

        //add ride request to list
        myRide.rideRequestsList.add(rideRequest);

        //get ride object
        Ride ride = myRide.requestForRide(rideRequest);
        myRide.rideList.add(ride);
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
                rate = new AutoRickshow().calculateFare(distance);
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
            Ride ride = new Ride.Builder()
                    .setRideID(1)
                    .setRideStatus("Completed")
                    .setPickUpLocation(request.getPickUpLocation())
                    .setDropOffLocation(request.getDropOffLocation())
                    .setCustomer(request.getCustomer())
                    .setDriver(driver)
                    .setRideDate(request.getRideRequestDate())
                    .setPickUpTime(request.getPickUpTime())
                    .setDropOffTime(request.getDropOffTime())
                    .setDistance(arr[1])
                    .setTotalCost(arr[0])
                    .build();
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

        Driver assignedDriver = availableDrivers.get(0);
        System.out.println("Assigned Driver: " + assignedDriver.getFirstName() + " " + assignedDriver.getLastName());

        Ride ride = requestToDriver(assignedDriver, rideRequest, fareDetails);
        if (ride == null) {
            System.out.println("Ride request failed. The driver rejected the request.");
            return null;
        }

        System.out.println("Ride confirmed successfully!");
        System.out.println("Driver :: " + ride.getDriver().getFirstName() + " " + ride.getDriver().getLastName());
        ride.getDriver().setAvailable(false);
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
