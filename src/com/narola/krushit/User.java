package com.narola.krushit;

import java.math.BigInteger;
import java.util.List;

abstract class User {
    int userID;
    String firstName;
    String lastName;
    BigInteger phoneNo;
    String emailID;

    public User(int userID, String firstName, String lastName, BigInteger phoneNo, String emailID) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.emailID = emailID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastLame(String lastName) {
        this.lastName = lastName;
    }

    public BigInteger getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(BigInteger phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastLame='" + lastName + '\'' +
                ", phoneNo=" + phoneNo +
                ", emailID='" + emailID + '\'' +
                '}';
    }


}
