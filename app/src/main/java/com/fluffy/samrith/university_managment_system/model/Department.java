package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Department  extends RowItem{

    String code;
    String office;
    String phone;

    public Department(int id, String name, String image) {
        super(id, name, image);
    }

    public Department(int id, String name, String image, String code, String office, String phone) {
        super(id, name, image);
        this.code = code;
        this.office = office;
        this.phone = phone;
        if(image.equals("")) image="department";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
