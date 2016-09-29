package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Main activity of habittracker
// displays the current day's activities
// other activities are started from here using the controller

public class MainActivity extends AppCompatActivity {

    private ListView oldHabitsList;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;
    // intent requests
    static final int add_habit_request = 1;
    static final int history_request = 2;
    private int curPos = 0;
    private SaveLoad_Controller saveController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveController = new SaveLoad_Controller(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // clears list of habits
        oldHabitsList = (ListView) findViewById(R.id.oldHabitsList);
        Button clearButton = (Button) findViewById(R.id.options);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                // clear list
                saveController.clear();
                adapter.notifyDataSetChanged();
            }
        });

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

    // Code From Lonely Twitter
    @Override
    protected void onStart() {
        super.onStart();
        habitList = saveController.loadFromFile();
        adapter = new ArrayAdapter<Habit>(this, R.layout.habit_view, habitList);
        oldHabitsList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // code to allow for menu option to add a habit
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_habit:
                setResult(RESULT_OK);
                loadHabitPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadHabitPage() {
        Intent intent = new Intent(this, habit_activity.class);
        startActivityForResult(intent, add_habit_request);
    }

    public void loadHistoryPage(Habit myHabit) {
        Intent intent = new Intent(this, history_activity.class);
        intent.putExtra("habit", myHabit);
        setResult(Activity.RESULT_OK, intent);
        startActivityForResult(intent, history_request);
    }

    // code to handle changes made by other activities
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // checks what request code was used
        switch(requestCode) {
            // add the new habit
            case (add_habit_request): {
                if (resultCode == Activity.RESULT_OK) {
                    String message = data.getStringExtra("habitResult");
                    ArrayList<String> daysOfHabit = data.getStringArrayListExtra("daysOfWeek");
                    Date date = new Date();
                    if (message == null || message.isEmpty()) {
                        break;
                    }
                    Habit newHabit = new Normal_Habit(message, date, daysOfHabit);
                    habitList.add(newHabit);
                    adapter.notifyDataSetChanged();
                    saveController.saveInFile();
                }
            }

            // update habit that has changed
            case (history_request): {
                if (resultCode == Activity.RESULT_OK) {
                    if(requestCode != 2) {break;}
                    // TODO handle updated history
                    String isDelete = data.getStringExtra("Delete");
                    // delete the habit
                    if(isDelete.equals("Delete")) {
                        habitList.remove(curPos);
                        adapter.notifyDataSetChanged();
                        saveController.saveInFile();
                    } else  {
                        // update habit stats
                        Habit updateHabit = (Habit) data.getSerializableExtra("habitResult");
                        habitList.remove(curPos);
                        habitList.add(curPos,updateHabit);
                        adapter.notifyDataSetChanged();
                        saveController.saveInFile();
                    }
                }
            }
        }
    }
}
