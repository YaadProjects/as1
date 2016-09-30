package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class completedHistory_activity extends AppCompatActivity {

    private Habit currentHabit;
    private ListView oldDatesList;
    private ArrayAdapter<Date> adapter;
    private ArrayList<Date> dates;
    private int datePos = 0;

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
        dates = currentHabit.getDatesCompleted();
        oldDatesList = (ListView) findViewById(R.id.dates);
        adapter = new ArrayAdapter<Date>(this,
                R.layout.habit_view, dates);
        oldDatesList.setAdapter(adapter);

        // allows for list to be clickable
        oldDatesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                datePos = position;
                delete();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // used when day button is clicked
    // allows user to change day of the week
    private void delete () {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Would you like to delete this completion?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteDate();
                    }
                })
                .create();
        dialog.show();
    }

    public void deleteDate() {
        Date deleteDate = dates.get(datePos);
        currentHabit.decreaseCount();
        currentHabit.removeDate(deleteDate);
        adapter.notifyDataSetChanged();
    }

    public void back(View view) {
        Intent intent = new Intent();
        intent.putExtra("habitResult", currentHabit);
        int count = currentHabit.getCount();
        setResult(Activity.RESULT_OK, intent);
        finish();}
}
