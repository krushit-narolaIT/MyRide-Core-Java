package com.narola.krushit;

public class SedanCar extends Car {
    private static final int capacity = 4;
    private double fareRate;

    public SedanCar(int vehicleID, String vehicleType, String fuelType, double fareRate) {
        super(vehicleID, vehicleType, fuelType);
        this.fareRate = fareRate;
    }

    public double getFareRate() {
        return fareRate;
    }

    public void setFareRate(double fareRate) {
        this.fareRate = fareRate;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getFareRate();
    }

    @Override
    public String toString() {
        return super.toString() + "SedanCar{" +
                "fareRate=" + fareRate +
                '}';
    }
}