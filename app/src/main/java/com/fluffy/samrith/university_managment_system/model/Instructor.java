package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Instructor extends RowItem {
    int range;
    String office;
    String phone;

    public Instructor(int id, String name, String image) {
        super(id, name, image);
    }

    public Instructor(int id, String name, String image, int range, String office, String phone) {
        super(id, name, image);
        this.range = range;
        this.office = office;
        this.phone = phone;
        if (image.equals("")) image= "instructor";
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
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
