package com.example.kelvin.liangmah__habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class completedHistory_activity extends AppCompatActivity {

    private Habit currentHabit;
    private ListView oldDatesList;
    private ArrayAdapter<Date> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_history_activity);

        // get habit obj
        Intent intent = getIntent();
        currentHabit = (Habit) intent.getSerializableExtra("habit");
        // Set the text views
        TextView textView = (TextView) findViewById(R.id.habitTitle);
        textView.setText(currentHabit.getHabitName());
        ArrayList<Date> dates = currentHabit.getDatesCompleted();
        oldDatesList = (ListView) findViewById(R.id.dates);
        adapter = new ArrayAdapter<Date>(this,
                R.layout.habit_view, dates);
        oldDatesList.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void back(View view) {finish();}
}
