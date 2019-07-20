package com.example.doctorappointment.com;

public class Clerk {


    String id;
    String name;
    String email;

    public Clerk() {
    }

    public Clerk(String id, String name, String email, String password, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    String password;
    String phone;
    String address;

}
