package com.narola.krushit;

abstract class Vehicle {
    private int vehicleID;
    private String vehicleType;
    private String fuelType;

    public Vehicle() {
    }

    public Vehicle(int vehicleID, String vehicleType, String fuelType) {
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
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


    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", vehicleType='" + vehicleType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }

    public abstract double calculateFare(double distance);
}
