package com.narola.krushit;

import java.math.BigInteger;
import java.util.List;

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

        //get all driver list
        System.out.println();
        System.out.println("====================       Driver List       ==================");
        System.out.println(Driver.driversList);

    }
}
