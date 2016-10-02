package com.example.kelvin.liangmah__habittracker;



import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Kelvin on 10/1/2016.
 */
public class HabitUnitTest {

    @Test
    public void getHabitNameTest() {
        Habit habit = new Normal_Habit("test");
        String habitName = habit.getHabitName();
        assertTrue(habitName.equals("test"));
    }

    @Test
    public void testGetDate() {
        Habit habit = new Normal_Habit("test");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        String dateString = sdf.format(date).toString();
        Date habitDate = habit.getDate();
        String dateString2 = sdf.format(habitDate).toString();
        assertTrue(dateString.equals(dateString2));
    }

    @Test
    public void testGetDaysOfHabit () {
        Habit habit = new Normal_Habit("test");
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
        Habit habit = new Normal_Habit("test");
        habit.increaseCount();
        habit.increaseCount();
        assertTrue(habit.getCount() == 2);
    }

    @Test
    public void TestIncreaseCount() {
        Habit habit = new Normal_Habit("test");
        assertTrue(habit.getCount() == 0);
        habit.increaseCount();
        assertTrue(habit.getCount() == 1);
    }

    @Test
    public void TestDecreaseCount() {
        Habit habit = new Normal_Habit("test");
        habit.increaseCount();
        assertTrue(habit.getCount() == 1);
        habit.decreaseCount();
        assertTrue(habit.getCount() == 0);
    }

    @Test
    public void TestAddDate() {
        Habit habit = new Normal_Habit("test");
        Date date = new Date();
        habit.addDate(date);
        ArrayList<Date> dates = habit.getDatesCompleted();
        assertTrue(dates.size() > 0);
    }

    @Test
    public void TestGetDatesCompleted() {
        Habit habit = new Normal_Habit("test");
        Date date = new Date();
        Date date2 = new Date();
        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(date);
        dates.add(date2);
        habit.addDate(date);
        habit.addDate(date2);
        assertTrue(date.equals(habit.getDatesCompleted().get(0)));
        assertTrue(date.equals(habit.getDatesCompleted().get(1)));
    }

    @Test
    public void TestHasCompletions() {
        Habit habit = new Normal_Habit("test");
        habit.addDate(new Date());
        assertTrue(habit.hasCompletions() == true);
    }

    @Test
    public void TestRemoveDate() {
        Habit habit = new Normal_Habit("test");
        Date date = new Date();
        habit.addDate(date);
        assertTrue(habit.hasCompletions() == true);
        habit.removeDate(date);
        assertTrue(habit.hasCompletions() == false);
    }
}
