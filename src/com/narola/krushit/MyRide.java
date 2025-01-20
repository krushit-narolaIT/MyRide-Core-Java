package com.narola.krushit;

import com.narola.krushit.commons.StatusConstant;
import com.narola.krushit.entity.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MyRide {
    private List<Customer> customerList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();
    private List<RideRequest> rideRequestList = new ArrayList<>();
    private List<Ride> rideList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();
    private List<String> vehicleList = new ArrayList<>();

    public static void main(String[] args) {
        MyRide myRide = new MyRide();

        myRide.addRoute(new Route("Surat", "Ahmedabad", 150));
        myRide.addRoute(new Route("Mumbai", "Kolkata", 1700));

        // Registering Drivers
        Driver driver1 = new Driver("Raj", "Raja", new BigInteger("9874563214"), "rajraja@gmail.com", "DL14-0123654787");
        myRide.registerDriver(driver1);

        Vehicle vehicle1 = new SedanCar("3W", "Petrol", "High", "Moderate", 4);
        driver1.addVehical(vehicle1);
        myRide.addVehicle(vehicle1);

        Driver driver2 = new Driver("Shri", "Vastava", new BigInteger("5641239874"), "shri@gmail.com", "GJ05-0123654787");
        myRide.registerDriver(driver2);

        Vehicle vehicle2 = new SuvCar("3W", "Petrol", 'S', 100, "Low");
        driver2.addVehical(vehicle2);
        myRide.addVehicle(vehicle2);

        Driver driver3 = new Driver("Arjun", "Dada", new BigInteger("8523697412"), "arjuna@gmail.com", "GJ05-4521361236");
        myRide.registerDriver(driver3);

        Vehicle vehicle3 = new SuvCar("3W", "Diesel", 'S', 75, "Moderate");
        driver3.addVehical(vehicle3);
        myRide.addVehicle(vehicle3);

        System.out.println("Vehicles :: " + myRide.vehicleList);

        // Registering Customer
        Customer customer1 = new Customer("Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");
        myRide.registerCustomer(customer1);

        // Creating Ride Request
        RideRequest rideRequest = new RideRequest.Builder()
                .setPickUpLocation("Mumbai")
                .setDropOffLocation("Kolkata")
                .setCustomer(customer1)
                .setRideRequestDate(LocalDate.now())
                .setPickUpTime(LocalTime.of(10, 30))
                .setDropOffTime(LocalTime.of(11, 30))
                .setVehicleType("suv")
                .setCapacity(4)
                .build();

        System.out.println(rideRequest);
        myRide.rideRequestList.add(rideRequest);

        try {
            Ride ride = myRide.processRideRequest(rideRequest);
            if (ride != null) {
                myRide.rideList.add(ride);
            }
        } catch (Exception e) {
            System.out.println("\u26A0 \u26A0 " + e.getMessage() + " \u26A0 \u26A0");
        }

        //get previous user rides for customer1
        System.out.println("\n================================");
        System.out.println("=== Customer1 Previous Rides ===");
        myRide.findUserRidesByUserID(customer1.getUserID());
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle instanceof SedanCar) {
            vehicleList.add("sedan");
        } else if (vehicle instanceof SuvCar) {
            vehicleList.add("suv");
        } else if (vehicle instanceof AutoRickshow) {
            vehicleList.add("auto");
        } else if (vehicle instanceof Bike) {
            vehicleList.add("bike");
        }
    }

    public void registerDriver(Driver driver) {
        if(driverList.isEmpty()){
            driver.setUserID(1);
        } else {
            driver.setUserID(driverList.get(driverList.size() - 1).getUserID() + 1);
        }
        driverList.add(driver);
        System.out.println("Driver Registered Successfully...!!!");
    }

    public void registerCustomer(Customer customer) {
        if(customerList.isEmpty()){
            customer.setUserID(1);
        } else {
            customer.setUserID(customerList.get(driverList.size() - 1).getUserID() + 1);
        }
        customerList.add(customer);
        System.out.println("Customer Registered Successfully...!!!");
    }

    public void addRoute(Route route) {
        routeList.add(route);
        System.out.println("Route Added Successfully...!!!");
    }

    private boolean isRouteAvailable(RideRequest request) {
        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                return true;
            }
        }
        return false;
    }

    private FareDetails calculateFare(RideRequest request) throws Exception {
        Route matchedRoute = null;
        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                matchedRoute = route;
                break;
            }
        }

        if (matchedRoute == null) {
            throw new Exception("Service not available for this route.");
        }

        double distance = matchedRoute.getDistance();
        double rate;

        if (request.getVehicleType().equalsIgnoreCase("bike")) {
            rate = new Bike().calculateFare(distance);
        } else if (request.getVehicleType().equalsIgnoreCase("sedan")) {
            rate = new SedanCar().calculateFare(distance);
        } else if (request.getVehicleType().equalsIgnoreCase("suv")) {
            rate = new SuvCar().calculateFare(distance);
        } else if (request.getVehicleType().equalsIgnoreCase("auto")) {
            rate = new AutoRickshow().calculateFare(distance);
        } else {
            throw new Exception("Invalid vehicle type: " + request.getVehicleType());
        }

        return new FareDetails(rate, distance);
    }

    private Ride assignDriverToRide(RideRequest request, FareDetails fareDetails) {
        List<Driver> availableDrivers = getAvailableDrivers();

        for (Driver driver : availableDrivers) {
            System.out.println("Attempting with Driver: " + driver.getFirstName() + " " + driver.getLastName());
            if (!driver.isLastTimeRejected() && driver.isAvailable() &&
                    request.getVehicleType().equalsIgnoreCase(driver.getVehicleType(driver.getVehicle())) &&
                        driver.requestConfirmation(request)) {
                driver.setAvailable(false);
                System.out.println("Your rider is " + driver.getFirstName() + " " + driver.getLastName());
                return new Ride.Builder()
                        .setRideID(rideList.size() + 1)
                        .setRideStatus(StatusConstant.RIDE_SCHEDULED)
                        .setPickUpLocation(request.getPickUpLocation())
                        .setDropOffLocation(request.getDropOffLocation())
                        .setCustomer(request.getCustomer())
                        .setDriver(driver)
                        .setRideDate(request.getRideRequestDate())
                        .setPickUpTime(request.getPickUpTime())
                        .setDropOffTime(request.getDropOffTime())
                        .setDistance(fareDetails.getDistance())
                        .setTotalCost(fareDetails.getRate())
                        .build();
            }
        }

        request.setRideRequestStatus(StatusConstant.RIDE_REQUEST_STATUS_REJECTED);
        System.out.println("No available drivers at the moment.");
        return null;
    }

    private List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverList) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public Ride processRideRequest(RideRequest rideRequest) throws Exception {
        if (!isRouteAvailable(rideRequest)) {
            throw new Exception("Ride not supported for the requested route.");
        }

        if (!isVehicleAvailable(rideRequest)) {
            throw new Exception("Sorry your requested vehicle is not available");
        }

        FareDetails fareDetails = calculateFare(rideRequest);
        Ride ride = assignDriverToRide(rideRequest, fareDetails);
        if (ride != null) {
            processRide(ride);
            rideRequest.setRideRequestStatus(StatusConstant.RIDE_REQUEST_STATUS_ACCEPTED);
        }
        return ride;
    }

    private boolean isVehicleAvailable(RideRequest rideRequest) {
        for (String vehicle : vehicleList) {
            if (rideRequest.getVehicleType().equalsIgnoreCase(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void processRide(Ride ride) {
        printTicket(ride);
        if (ride != null) {
            System.out.print("Ride is Ongoing");
            simulateRideProgress();
            ride.setRideStatus(StatusConstant.RIDE_COMPLETED);
            System.out.println("\nRide Completed...!!!");
        }
    }

    private void simulateRideProgress() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.print(".");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Unexpected interruption during ride.");
            }
        }
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

    private void findUserRidesByUserID(int userId){
        boolean flag = false;
        for(Ride ride : rideList){
            if(ride.getCustomer().getUserID() == userId){
                ride.printRide();
                flag = true;
            }
        }
        if(!flag){
            System.out.println("No Previous Rides available");
        }
    }
}
