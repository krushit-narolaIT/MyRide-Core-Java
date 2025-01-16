package com.narola.krushit;

import java.util.Locale;

class AutoRickshaw extends Vehicle {
    public static final int capacity = 3;
    private String transmission;

    public AutoRickshaw() {
    }

    public AutoRickshaw(int vehicleID, String vehicleType, String fuelType, String transmission) {
        super(vehicleID, vehicleType, fuelType);
        this.transmission = transmission;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

/*    @Override
    public double calculateFare(double distance) {
        double rate = 0.0;
        String fuelType = getFuelType();
        if(fuelType.equalsIgnoreCase("petrol") || fuelType.equalsIgnoreCase("diesel")){
            rate += distance * 4;
        } else if(fuelType.equalsIgnoreCase("CNG")){
            rate += distance * 1;
        }
        return distance *  + (distance > 8 ? 20 : 0);
    }*/

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
