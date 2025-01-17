
package com.narola.krushit;

public class SedanCar extends Car {
    public static final int capacity = 4;
    private String wheelBase;
    private double carLength;

    public SedanCar() {
    }

    public SedanCar(int vehicleID, String vehicleType, String fuelType, String groundClearance, String wheelBase, double carLength) {
        super(vehicleID, vehicleType, fuelType, groundClearance);
        this.wheelBase = wheelBase;
        this.carLength = carLength;
    }

    public String getWheelBase() {
        return wheelBase;
    }

    public void setWheelBase(String wheelBase) {
        this.wheelBase = wheelBase;
    }

    public double getCarLength() {
        return carLength;
    }

    public void setCarLength(double carLength) {
        this.carLength = carLength;
    }

/*    @Override
    public double calculateFare(double distance) {
        String fuelType = getFuelType();
        double rate = 1200;
        if(fuelType.equalsIgnoreCase("petrol") || fuelType.equalsIgnoreCase("diesel")){
            rate += distance * 25;
        } else if(fuelType.equalsIgnoreCase("CNG")){
            rate += distance * 9;
        }
        if (distance > 50) {
            rate += (carLength > 4.5 ? 100 : 50);
        }
        return rate;
    }*/

    @Override
    public double calculateFare(double distance) {
        double baseRate = 1200;
        double distanceRate = 20;

        double rate = baseRate + (distance * distanceRate);

        if (distance > 50) {
            rate += (carLength > 4.5 ? 250 : 75);
        }
        return rate;
    }

}
