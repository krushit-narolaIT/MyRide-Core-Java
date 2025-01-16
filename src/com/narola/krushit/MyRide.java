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

    public Driver requestForRide(RideRequest rideRequest){
        List<Driver> drivers = this.driverList;
        List<Driver> availableDrivers =  new ArrayList<>();

        for(Driver driver: drivers){
            if(driver.isAvailable()){
                if(!driver.isLastTimeRejected()){
                    availableDrivers.add(driver);
                }
            }
        }

        System.out.println("== Available drivers for location ==");
        for(Driver d : availableDrivers){
            System.out.println("Driver :" + d.getFirstName() + " " + d.getLastName());
        }
        System.out.println("=== Assigned Driver ===");
        System.out.println("-> " + availableDrivers.get(0).getFirstName());

        return availableDrivers.isEmpty() ? null : availableDrivers.get(0);
    }

    private double[] isRootSupported(RideRequest request) {
        double[] arr = new double[2];
        double distance = 0.0;
        boolean flag = false;
        for(Route route : routeList){
            if(route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())){
                distance = route.getDistance();
                flag = true;
            }
        }

        if(!flag){
            System.out.println("Sorry for inconvenience, we are unable to proceed your request");
            return null;
        }

        double rate = 0.0;
        String type = request.getVehicleType();
        if(type.equalsIgnoreCase("bike")){
            distance = new Bike().calculateFare(distance);
        } else if(type.equalsIgnoreCase("sedan")){
            distance = new SedanCar().calculateFare(distance);
        } else if(type.equalsIgnoreCase("suv")){
            distance = new SuvCar().calculateFare(distance);
        } else {
            distance = new AutoRickshaw().calculateFare(distance);
        }

        arr[0] = rate;
        arr[1] = distance;

        System.out.println("Your Ride Total Charge Will Be :: " + rate);

        return arr;
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

        /*
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
        */


        String dateString = "2025-01-16";
        LocalDate rideDate = LocalDate.parse(dateString);

        RideRequest rideRequest = new RideRequest();

        // Create the RideRequest object
        RideRequest request = new RideRequest(
                "Jaipur",
                "Delhi",
                customer1,
                rideDate,
                LocalTime.now(),
                LocalTime.of(2, 15, 0),
                "Sedan",
                4
        );

        double[] arr = myRide.isRootSupported(rideRequest);

        myRide.rideRequestsList.add(rideRequest);

        Driver driver = myRide.requestForRide(rideRequest);
        System.out.println("Driver :: " + driver.getFirstName() + " " + driver.getLastName());
        myRide.requestToDriver(driver, rideRequest, arr);

        //Prequiste : System will support specific route only, route will have from loc, to loca, distance.

        //1. System checks provide route supported or not
        //2. If supported, display total price and checks for available driver
        //3. If route not supported then display message "Service not avaiable for this route"
        //4. Assigned random availa driver
        //5. Assigned driver accept or reject
        //6. If Driver accept, confirm booking and print ticket

        //1. System checks available driver
        //2. Assigned random availa driver
        //3. Assigned driver reject
        //4. If Driver reject, assign to another available driver
        //5. If driver accept, confirm booking and print ticket

        //1. System checks available driver
        //2. Assigned random availa driver
        //3. Assigned driver reject
        //4. If Driver reject, assign to another available driver
        //5. If driver reject, Print "No vehical available"

        /*
        System.out.println("========================================");
        Driver assignedDriver = customer.requestForRide(request);
        System.out.println("Driver :: " + assignedDriver.getFirstName() + " " + assignedDriver.getLastName());

        System.out.print("\nWaiting for confirmation");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        if (assignedDriver.requestConfirmation()) {
            System.out.println("\nConfirmation received.");
            Ride ride = new Ride(1, "Accepted", request.getPickUpLocation(), request.getDropOffLocation(), customer, assignedDriver, request.getRideRequestDate(), request.getPickUpTime(), request.getDropOffTime(), request.getDistance(), assignedDriver.totalCharge(request.getDistance()));
            MyRideController.rideList.add(ride);
            MyRideController.printTicket(ride);
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
                        MyRideController.rideList.add(ride);
                        MyRideController.printTicket(ride);
                    } else {
                        System.out.println("Sorry for inconvenience.!!, no driver available for your ride");
                    }
                }
            }
        }

        System.out.println();
        System.out.println();

        System.out.println("===================================================");
        System.out.println("==============     Previous Rides     ==============");
        List<Ride> userRide = Ride.findRideByUserID(3);

        for (Ride ride : userRide) {
            System.out.println("Ride :: " + ride.getRideID() + " " + ride.getRideDate() + " " + ride.getPickUpLocation() + " " + ride.getDropOffLocation() + " " + ride.getDriver().getFirstName());
        }
        */

    }
}
