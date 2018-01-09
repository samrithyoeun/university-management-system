package com.fluffy.samrith.university_managment_system.model;

import recyclerview.RowItem;

/**
 * Created by samrith on 9/1/18.
 */

public class Session extends RowItem{

    int secnumber;
    int semester;
    String year;
    String room;
    String daytime;


    public Session(int id, String name, String image) {
        super(id, name, image);
    }

    public Session(int id, String name, String image, int secnumber, int semester, String year, String room, String daytime) {
        super(id, name, image);
        this.secnumber = secnumber;
        this.semester = semester;
        this.year = year;
        this.room = room;
        this.daytime = daytime;
        if(image.equals("")) image ="session";

    }

    public int getSecnumber() {
        return secnumber;
    }

    public void setSecnumber(int secnumber) {
        this.secnumber = secnumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }
}
