package com.example.dmitry.cashmonitor;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dmitry on 21.03.2016.
 */
public class Note {

    private String name;
    private boolean success;
    private double count;
    private String typeCount;
    private Long id;
    private String year;
    private String month;
    private String day;

    public Note(String name, int success, double count, String typeCount, Long id){
        this.name = name;
        if (success == 1)
            this.success = true;
        else
            this.success = false;
        this.count = count;
        this.typeCount = typeCount;
        this.id = id;
        this.year = null;
        this.month = null;
        this.day = null;
    }


    public Note(String name, int success, double count, String typeCount, Long id, String year, String month, String day){
        this.name = name;
        if (success == 1)
            this.success = true;
        else
            this.success = false;
        this.count = count;
        this.typeCount = typeCount;
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public String getName(){return name;}

    public boolean getSuccess(){
        return success;
    }

    public double getCount(){
        return count;
    }

    public String getTypeCount(){
        return typeCount;
    }

    public Long getId(){return id;}

    public String getYear(){return  year == null?
            (new SimpleDateFormat("yyyy").format(new Date())):
            year;}

    public String getDay(){return  day == null?
            (new SimpleDateFormat("dd").format(new Date())):
            day;}

    public String getMonth(){return  month == null?
            (new SimpleDateFormat("MM").format(new Date())):
            month;}

}
