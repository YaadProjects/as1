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

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView oldHabitsList;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;
    static final int add_habit_request = 1;
    static final int history_request = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long date = System.currentTimeMillis();
        TextView dateNow = (TextView) findViewById(R.id.currentDate);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        String dateString = sdf.format(date);
        dateNow.setText(dateString);

        oldHabitsList = (ListView) findViewById(R.id.oldHabitsList);
        Button clearButton = (Button) findViewById(R.id.options);
        clearButton.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                // clear list
                habitList.clear();
                deleteFile(FILENAME); // delete file
                adapter.notifyDataSetChanged(); // update}
            }
        });

        oldHabitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Habit selectedHabit = habitList.get(position);
                loadHistoryPage(selectedHabit);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
       // onActivityResult(add_habit_request, Activity.RESULT_OK, intent);
    }

    public void loadHistoryPage(Habit myHabit) {
        Intent intent = new Intent(this, history_activity.class);
        intent.putExtra("habit", myHabit);
        setResult(Activity.RESULT_OK, intent);
        startActivityForResult(intent, history_request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (add_habit_request): {
                if (resultCode == Activity.RESULT_OK) {
                    String message = data.getStringExtra("habitResult");
                    ArrayList<String> daysOfHabit = data.getStringArrayListExtra("daysOfHabit");
                    Date date = new Date();
                    if (message == null || message.isEmpty()) {
                        break;
                    }
                    Habit newHabit = new Normal_Habit(message, date, daysOfHabit);
                    habitList.add(newHabit);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }
            }

            case (history_request): {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO handle updated history
                }
            }
        }
    }


    // Code From Lonely Twitter
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this, R.layout.habit_view, habitList);
        oldHabitsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Normal_Habit>>(){}.getType();

            habitList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
