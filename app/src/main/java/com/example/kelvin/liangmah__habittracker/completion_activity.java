package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class completion_activity extends AppCompatActivity {

    private ListView oldHabitsList;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayList<Habit> MasterHabitList = new ArrayList<>();
    private ArrayAdapter<Habit> adapter;
    private SaveLoad_Controller saveController;
    private int curPos = 0;
    private static final int history_request = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_activity);
        saveController = new SaveLoad_Controller(this);
        oldHabitsList = (ListView) findViewById(R.id.oldHabitsList);

        MasterHabitList = saveController.loadFromFile();
        completedHabits();
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.habit_view, habitList);
        oldHabitsList.setAdapter(adapter);

        // allows for list to be clickable
        oldHabitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Habit selectedHabit = habitList.get(position);
                curPos = position;
                loadHistoryPage(selectedHabit);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void completedHabits() {
        for(Habit habit : MasterHabitList) {
            if (habit.getCount() > 0) {
                habitList.add(habit);
            }
        }
    }

    public void loadHistoryPage(Habit myHabit) {
        Intent intent = new Intent(this, completedHistory_activity.class);
        intent.putExtra("habit", myHabit);
        setResult(Activity.RESULT_OK, intent);
        startActivityForResult(intent, history_request);
    }

    public void back(View view) {finish();}
}