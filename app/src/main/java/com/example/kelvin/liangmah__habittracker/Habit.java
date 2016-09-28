package com.example.kelvin.liangmah__habittracker;

import java.util.Date;

/**
 * Created by Kelvin on 9/26/2016.
 */
public abstract class Habit {
    private String habitName;
    private Date date;

    public Habit(String habitName) {
        this.habitName = habitName;
        this.date = new Date();
    }

    public Habit(String habitName, Date date) {
        this.habitName = habitName;
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public abstract Boolean isImportant();

    public String getHabitName() {
        return habitName;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString(){
        return  habitName;
    }
}
