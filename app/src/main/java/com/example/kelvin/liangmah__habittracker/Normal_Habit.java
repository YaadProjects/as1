package com.example.kelvin.liangmah__habittracker;

/**
 * Created by Kelvin on 9/26/2016.
 */
public class Normal_Habit extends Habit implements Habit_printable {

    public Normal_Habit(String message){
        super(message);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
