package com.example.kelvin.liangmah__habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


// This class handles the habit history
// will display when habit was created, the count for the number of times habit has been done
// will also display days that habit is to be completed.
// and will give options for the user to delete the habit, add to completion count

public class history_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);
        // get habit obj
        Intent intent = getIntent();
        Habit currentHabit = (Habit) intent.getSerializableExtra("habit");
        // Set the text views
        TextView textView = (TextView) findViewById(R.id.AddHabitTitle);
        textView.setText(currentHabit.getHabitName());
        textView = (TextView) findViewById(R.id.habit_date);
        textView.setText("Added on: " + currentHabit.getDate().toString());
        textView = (TextView) findViewById(R.id.habitCount);
        textView.setText("Times Completed: " + String.valueOf(currentHabit.getCount()));

    }
}
