package com.narola.krushit.entity;

public class AutoRickshow extends Vehicle {
    public final int capacity = 3;
    private String transmission;

    public AutoRickshow() {
    }

    public AutoRickshow(String vehicleType, String fuelType, String transmission) {
        super(vehicleType, fuelType);
        this.transmission = transmission;
    }

    @Override
    public double calculateFare(double distance) {
        double ratePerKm = 4;
        double additionalCharge = (distance > 8) ? 20 : 0;
        double rate = (distance * ratePerKm) + additionalCharge;
        return rate;
    }


    @Override
    public String toString() {
        return super.toString() + ", AutoRickshaw{" +
                ", capacity=" + capacity +
                '}';
    }
}
