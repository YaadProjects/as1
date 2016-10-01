package com.example.kelvin.liangmah__habittracker;

import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kelvin on 9/26/2016.
 */

// this class is the abstact base of habit
// based off of the lonelytwitter class Tweet
// implements Serializable so that the class can be passed to other activities


public abstract class Habit implements Serializable {
    private String habitName;
    private Date date;
    private ArrayList<String> daysOfHabit;
    private ArrayList<Date> datesCompleted;
    private static final long serialVersionUID = 1L;
    private int count;

    public Habit(String habitName) {
        this.habitName = habitName;
        this.date = new Date();
        this.daysOfHabit = new ArrayList<String>();
        this.count = 0;
        this.datesCompleted = new ArrayList<Date>();
    }

    public Habit(String habitName, Date date) {
        this.habitName = habitName;
        this.date = date;
        this.daysOfHabit = new ArrayList<String>();
        this.count = 0;
        this.datesCompleted = new ArrayList<Date>();
    }

    public Habit(String habitName, Date date, ArrayList<String> daysOfHabit) {
        this.habitName = habitName;
        this.date = date;
        this.daysOfHabit = daysOfHabit;
        this.count = 0;
        this.datesCompleted = new ArrayList<Date>();
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

    public void setDaysOfHabit(ArrayList<String> daysOfHabit) { this.daysOfHabit = daysOfHabit; }

    public ArrayList<String> getDaysOfHabit() { return daysOfHabit;}

    public int getCount() { return this.count; }

    public void increaseCount() { this.count++; }

    public void decreaseCount() {this.count--; }

    public void addDate(Date date) {
        this.datesCompleted.add(date);
    }

    public ArrayList<Date> getDatesCompleted() {
        return this.datesCompleted;
    }

    public void removeDate(Date dateRM) {
        this.datesCompleted.remove(dateRM);
    }

    public boolean hasCompletions() {
        if(this.getDatesCompleted().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        String dateString = sdf.format(date).toString();
        return  habitName + " | created on: " + dateString;
    }
}
