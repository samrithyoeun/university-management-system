package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Student extends RowItem{
    String address;
    String phone;
    String major;
    String dob;


    public Student(int id, String name, String image) {
        super(id, name, image);
    }

    public Student(int id, String name, String image, String address, String phone, String major, String dob) {
        super(id, name, image);
        this.address = address;
        this.phone = phone;
        this.major = major;
        this.dob = dob;
        if (image.equals("")) image= "student";
    }

    public Student(int id, String s) {
        this.id =id;
        this.name =s ;
        image ="student";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
