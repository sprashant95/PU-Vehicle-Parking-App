package com.module.pu_vehicle_app.Model;

public class Users {
     String name,phone,password , email , mis;

    public Users(){
    }

    public Users(String name, String phone, String password, String email, String mis) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.mis = mis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis;
    }
}
