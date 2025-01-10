package com.narola.krushit;

class Bike extends Vehicle {
    private static String fuelType;
    private double fareRate;
    private static final int capacity = 1;

    public Bike(int vehicleID, String vehicleType, double fairRate) {
        super(vehicleID, vehicleType);
        this.fareRate = fairRate;
    }

    public double getFareRate() {
        return fareRate;
    }

    public void setFareRate(double fareRate) {
        this.fareRate = fareRate;
    }

    @Override
    public String toString() {
        String str = "Bike ::[" + super.toString() + "fareRate=" + fareRate + " ]";
        return str;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getFareRate();
    }
}