package com.example.nishan.donar_list.Model;

/**
 * Created by nishan on 12/21/17.
 */

public class Donar {
    private String name;
    private String email;
    private String permanentAddress;
    private String currentAddresss;
    private String phoneNumber;
    private String bloodGroup;

    public Donar(String name, String email, String permanentAddress, String currentAddresss, String phoneNumber, String bloodGroup) {
        this.name = name;
        this.email = email;
        this.permanentAddress = permanentAddress;
        this.currentAddresss = currentAddresss;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCurrentAddresss() {
        return currentAddresss;
    }

    public void setCurrentAddresss(String currentAddresss) {
        this.currentAddresss = currentAddresss;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
