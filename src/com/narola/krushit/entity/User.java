package com.narola.krushit.entity;

import java.math.BigInteger;
import java.util.Objects;

public abstract class User {
    private int userID;
    private String firstName;
    private String lastName;
    private BigInteger phoneNo;
    private String emailID;

    public User(String firstName, String lastName, BigInteger phoneNo, String emailID) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(phoneNo, user.phoneNo) && Objects.equals(emailID, user.emailID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNo, emailID);
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

    public abstract void displayUserRole();
    public abstract String getUserDetails();
}
