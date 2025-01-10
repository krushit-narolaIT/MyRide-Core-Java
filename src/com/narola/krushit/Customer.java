package com.narola.krushit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Customer extends  User{
    static List<Customer> customerList;

    static {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Krushit" , "Babariya", new BigInteger("9876543214"), "ksb@narola.email"));
        customerList.add(new Customer(2, "Sujal" , "Babariya", new BigInteger("9856543214"), "ssb@gmail.email"));
    }

    public Customer(int userID, String firstName, String lastLame, BigInteger phoneNo, String emailID) {
        super(userID, firstName, lastLame, phoneNo, emailID);
    }

    public void requestRide(String pickupLocation, String dropoffLocation, int passengers) {
        System.out.println(getFirstName() + " " + getLastLame() + " requested a ride from " + pickupLocation + " to " + dropoffLocation + " for " + passengers + " passengers.");
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
        System.out.println("User Registered Successfully..!!");
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }

    public Object getCustomerByID(Integer id){
         for(Customer cust : customerList){
             if(cust.getUserID() == id){
                 return cust;
             }
         }
         return null;
    }

    public Object deleteCustomerByID(Integer id){
        for(Customer cust : customerList){
            if(cust.getUserID() == id){
                customerList.remove(cust);
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
                ", Last Lame='" + lastLame + '\'' +
                ", Phone No=" + phoneNo +
                ", Email ID='" + emailID + '\'' +
                "]";
    }

    public void bookRide(String pickUpLocation, double distance, String dropOffLocation){


    }
}
