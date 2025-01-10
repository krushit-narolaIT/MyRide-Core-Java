package com.narola.krushit;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

import static com.narola.krushit.Driver.driversList;

public class MyRide {
    public static void main(String[] args) {
        // Create Customer
        Customer customer = new Customer(3, "Rushit" , "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");

        //Register customer
        customer.addCustomer(customer);

        //Get customer list
        List<Customer> list = customer.getCustomerList();
        System.out.println("========================   Customer List  =====================");
        for(Customer cust : list){
            System.out.println(cust);
        }
        System.out.println("==============================================================");
        System.out.println();

        //get customer by id
        System.out.println("=====================   Get Customer By ID  =====================");
        System.out.println(customer.getCustomerByID(3));

        //register driver
        List<Driver> driverList = Driver.getDriversList();
        driversList.add(new Driver(2, "Ravi", "Kumar", new BigInteger("98912412340"), "ravi@gmail.com", "DL14-20224556781", new SuvCar(2,  VehicleType.FOURWHEELER.name(), "Diesel", 10), "Surat", "Vadodara", 180.0));
        driversList.add(new Driver(3, "Sonu", "Jawan", new BigInteger("85912412340"), "sonu@gmail.com", "DL14-20114556781", new SuvCar(2,  VehicleType.FOURWHEELER.name(), "Diesel", 12), "Mumbai", "Hyderabad", 220.0));

        //get all driver list
        System.out.println();
        System.out.println("====================       Driver List       ==================");
        System.out.println(driversList);

        //book ride by customer from surat to vadodara
        System.out.println();
        System.out.println();
        List<Driver> availableDrivers = customer.showRiders("surat",100.0,"vadodara");
        System.out.println("||=========================================================||");
        System.out.println("||========    List of available Drivers    ================||");
        System.out.println("||==========    [Surat --> Vadodara]   ====================||");
        System.out.println("||=========================================================||");

        for(Driver d : availableDrivers) {
            System.out.println("||\t\t\tDrivers :: " + d.getFirstName() + " " + d.getLastName() + " " + d.totalCharge(200) + "₹\t\t\t\t   ||");
        }
        System.out.println("||=========================================================||");

        //book ride by customer from mumbai to hydrabad
        System.out.println();
        System.out.println();
        List<Driver> availableDrivers2 = customer.showRiders("mumbai",2500.25,"Hyderabad");
        System.out.println("||=========================================================||");
        System.out.println("||========    List of available Drivers    ================||");
        System.out.println("||==========    [Mumbai --> Hyderabad]   ==================||");
        System.out.println("||=========================================================||");
        for(Driver d : availableDrivers2) {
            System.out.println("||\t\t\tDrivers :: " + d.getFirstName() + " " + d.getLastName() + " " + d.totalCharge(2500.25) + "₹\t\t\t\t   ||");
        }
        System.out.println("||=========================================================||");

        System.out.println();
        System.out.println();





    }
}
