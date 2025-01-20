package com.narola.krushit.entity;

public enum VehicleType {
    TWOWHEELER("2W"),
    THREEWHEELER("3W"),
    FOURWHEELER("4W");

    String name;

    VehicleType(String name){
        this.name = name;
    }
}
