package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Department  extends RowItem{

    int code;
    String office;
    String phone;

    public Department(int id, String name, String image) {
        super(id, name, image);
    }

    public Department(int id, String name, String image, int code, String office, String phone) {
        super(id, name, image);
        this.code = code;
        this.office = office;
        this.phone = phone;
        if(image.equals("")) image="department";
    }

    public Department(String dName, String dOffice) {
        name =dName;
        office =dOffice;
    }

    public Department(int dCode, String dName) {
            code= dCode;
            name = dName;
            image ="department";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
