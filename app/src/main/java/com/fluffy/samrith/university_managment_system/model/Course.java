package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Course  extends RowItem{
    String code;
    int credit;
    int level;
    String desc;


    public Course(int id, String name, String image) {
        super(id, name, image);
    }

    public Course(int id, String name, String image, String code, int credit, int level, String desc) {
        super(id, name, image);
        this.code = code;
        this.credit = credit;
        this.level = level;
        this.desc = desc;
        if(image.equals(""))
            image ="course";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
