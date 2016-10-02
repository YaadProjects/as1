package com.example.kelvin.liangmah__habittracker;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Kelvin on 10/1/2016.
 */
public class HabitUnitTest {

    @Test
    public void getHabitNameTest() {
        Habit habit = new Habit("test");
        String habitName = habit.getHabitName();
        assertTrue(habitName.equals("test"));
    }

    @Test
    public void testGetDate() {
        Habit habit = new Habit("test");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        String dateString = sdf.format(date).toString();
        Date habitDate = habit.getDate();
        String dateString2 = sdf.format(habitDate).toString();
        assertTrue(dateString.equals(dateString2));
    }

    @Test
    public void testGetDaysOfHabit () {
        Habit habit = new Habit("test");
        ArrayList<String> days = new ArrayList<String>();
        days.add("Monday");
        days.add("Tuesday");
        habit.setDaysOfHabit(days);
        ArrayList<String> days2 = habit.getDaysOfHabit();
        assertTrue(days.get(0).equals(days2.get(0)));
        assertTrue(days.get(1).equals(days2.get(1)));
    }

    @Test
    public void testGetCount() {
        Habit habit = new Habit("test");
        habit.increaseCount();
        habit.increaseCount();
        assertTrue(habit.getCount() == 2);
    }
}
