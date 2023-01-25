package com.module.pu_vehicle_app;

public class Vehicle {

    String vehicleNo;
    String name;
    String phone1;
    String phone2 = "null";
    String email;
    String location;
    String GuardMis;
    String GuardName;

    public Vehicle(String vehicleNo, String name, String phone1, String phone2, String email, String location, String GuardMis, String GuardName) {
        this.vehicleNo = vehicleNo;
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.location = location;
        this.GuardMis = GuardMis;
        this.GuardName = GuardName;
    }

    public Vehicle(){

    }
    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getName() {
        return name;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getGuardMis(){return GuardMis;}
    public String getGuardName(){return GuardName;}
}
