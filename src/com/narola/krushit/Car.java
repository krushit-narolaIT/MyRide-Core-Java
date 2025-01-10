package com.narola.krushit;

public abstract class Car extends Vehicle{
    private String fuelType;

    @Override
    public String toString() {
        return "fuelType='" + fuelType ;
    }

    public Car(int vehicleID, String vehicleType, String fuelType) {
        super(vehicleID, vehicleType);
        this.fuelType = fuelType;
    }
}
