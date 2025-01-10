package com.narola.krushit;

abstract class Vehicle {
    private int vehicleID;
    private String vehicleType;


    public Vehicle(int vehicleID, String vehicleType) {
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID='" + vehicleID + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }

    public abstract double getFareRate();
    public abstract double calculateFare(double distance);
}