package com.narola.krushit;

public abstract class Vehicle {
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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
