package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class College extends RowItem{
    String office;
    String phone;

    public College(int id, String name, String image, String office, String phone) {
        super(id, name, image);
        this.office = office;
        this.phone = phone;
        if(image.equals(""))
            image = "college";
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

