package com.narola.krushit;

import java.math.BigInteger;
import java.util.List;

abstract class User {
    int userID;
    String firstName;
    String lastLame;
    BigInteger phoneNo;
    String emailID;

    public User(int userID, String firstName, String lastLame, BigInteger phoneNo, String emailID) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastLame = lastLame;
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

    public String getLastLame() {
        return lastLame;
    }

    public void setLastLame(String lastLame) {
        this.lastLame = lastLame;
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
                ", lastLame='" + lastLame + '\'' +
                ", phoneNo=" + phoneNo +
                ", emailID='" + emailID + '\'' +
                '}';
    }


}
