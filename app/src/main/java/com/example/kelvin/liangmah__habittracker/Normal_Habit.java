package com.example.kelvin.liangmah__habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kelvin on 9/26/2016.
 */
public class Normal_Habit extends Habit implements Habit_printable {

    public Normal_Habit(String message){
        super(message);
    }
    public Normal_Habit(String message, Date date, ArrayList<String> days) { super(message, date, days); }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
