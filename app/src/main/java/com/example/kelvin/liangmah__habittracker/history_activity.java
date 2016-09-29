package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;


// This class handles the habit history
// will display when habit was created, the count for the number of times habit has been done
// will also display days that habit is to be completed.
// and will give options for the user to delete the habit, add to completion count

public class history_activity extends AppCompatActivity {

    private Habit currentHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);


        // get habit obj
        Intent intent = getIntent();
        currentHabit = (Habit) intent.getSerializableExtra("habit");
       setHabitViews(currentHabit);

    }

    public void raiseCount(View view) {
        currentHabit.increaseCount();
        String newCount = String.valueOf(currentHabit.getCount());
        updateTextView(newCount);
    }

    public void delete(View view) {
        Intent intent = new Intent();
        intent.putExtra("habitResult", currentHabit);
        intent.putExtra("Delete", "Delete");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void done(View view) {
        Intent intent = new Intent();
        intent.putExtra("habitResult", currentHabit);
        intent.putExtra("Delete", "done");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void updateTextView(String newCount) {

        TextView textView = (TextView) findViewById(R.id.habitCount);
        textView.setText(("Times Completed: " + newCount));
    }

    public void setHabitViews(Habit currentHabit) {

        // Set the text views
        TextView textView = (TextView) findViewById(R.id.AddHabitTitle);
        textView.setText(currentHabit.getHabitName());
        textView = (TextView) findViewById(R.id.habit_date);
        textView.setText("Added on: " + currentHabit.getDate().toString());
        textView = (TextView) findViewById(R.id.habitCount);
        textView.setText("Times Completed: " + String.valueOf(currentHabit.getCount()));
        textView = (TextView) findViewById(R.id.days);
        ArrayList<String> days = currentHabit.getDaysOfHabit();
        textView.setText(findDays(days));
    }

    public String findDays( ArrayList<String> days) {
        String daysOfHabit = "Days Habit occurs: ";
        for(String day : days) {
            if(day == "Monday") {
                daysOfHabit = daysOfHabit + "M ";
            }
            if(day.equals("Tuesday")) {
                daysOfHabit = daysOfHabit + "T ";
            }
            if(day.equals("Wednesday")) {
                daysOfHabit = daysOfHabit + "W ";
            }
            if(day.equals("Thursday")) {
                daysOfHabit = daysOfHabit + "R ";
            }
            if(day.equals("Friday")) {
                daysOfHabit = daysOfHabit + "F ";
            }
            if(day.equals("Saturday")) {
                daysOfHabit = daysOfHabit + "S ";
            }
            if(day.equals("Sunday")) {
                daysOfHabit = daysOfHabit + "U ";
            }
        }
        return daysOfHabit;
    }
}
