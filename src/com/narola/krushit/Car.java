package com.narola.krushit;

public abstract class Car extends Vehicle {
    private String groundClearance;

    public Car() {
    }

    public Car(int vehicleID, String vehicleType, String fuelType, String groundClearance) {
        super(vehicleID, vehicleType, fuelType);
        this.groundClearance = groundClearance;
    }
}
