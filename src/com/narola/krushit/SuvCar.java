package com.narola.krushit;

public class SuvCar extends Car {
    public static final int capacity = 6;
    private char trunkSize;
    private int towingCapacity;
    private double grossVehicleWeight; //in kgs

    public SuvCar() {
    }

    public SuvCar(int vehicleID, String vehicleType, String fuelType, char trunkSize, int towingCapacity, String groundClearance) {
        super(vehicleID, vehicleType, fuelType, groundClearance);
        this.trunkSize = trunkSize;
        this.towingCapacity = towingCapacity;
    }

    public char getTrunkSize() {
        return trunkSize;
    }

    public void setTrunkSize(char trunkSize) {
        this.trunkSize = trunkSize;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    @Override
    public double calculateFare(double distance) {
        double fare = distance * 1000;
        if (grossVehicleWeight > 2000) {
            if(distance > 200){
                fare += 10 * distance;
            }
            fare += 200;
        }
        return fare;
    }

    @Override
    public String toString() {
        return super.toString() + "SuvCar{" +
                "Capacity =" + capacity +
                '}';
    }
}