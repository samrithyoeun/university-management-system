package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Course  extends RowItem{
    int code;
    int credit;
    int level;
    String desc;


    public Course(int id, String name, String image) {
        super(id, name, image);
    }

    public Course(int id, String name, String image, int code, int credit, int level, String desc) {
        super(id, name, image);
        this.code = code;
        this.credit = credit;
        this.level = level;
        this.desc = desc;
        if(image.equals(""))
            image ="course";
    }

    public Course(String coName) {
        this.name = coName;
        image="course";
    }

    public Course(String coName, int cCode) {
        this.name = coName;
        code = cCode;
        image="course";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
