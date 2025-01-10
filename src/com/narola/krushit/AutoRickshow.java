package com.narola.krushit;

class AutoRickshow extends Vehicle {
    private String fuelType;
    private double fareRate;
    private static final int capacity = 3;

    public AutoRickshow(int vehicleID, String vehicleType, String fuelType, double fairRate) {
        super(vehicleID, vehicleType);
        this.fuelType = fuelType;
        this.fareRate = fairRate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getFareRate() {
        return fareRate;
    }

    public void setFairRate(double fairRate) {
        this.fareRate = fairRate;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getFareRate();
    }
}