package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Instructor extends RowItem {
    int range;
    String office;
    String phone;
    String CName;
    int dcode;

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

    public Instructor(int id, String college_cName, int dept_dCode, String iName) {
        this.id = id;
        this.CName = college_cName;
        this.dcode=  dept_dCode;
        name = iName;
        image ="professor";


    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public int getDcode() {
        return dcode;
    }

    public void setDcode(int dcode) {
        this.dcode = dcode;
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
