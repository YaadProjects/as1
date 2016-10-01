package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

// this class handles the activity in which
// the user can add habits
// class handles data of days the habit occurs and habit name
public class habit_activity extends AppCompatActivity {
    private EditText bodyText;
    private ListView dayList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> daysOfHabit = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_activity);
        setFont();

        bodyText = (EditText) findViewById(R.id.habit_name);
        dayList = (ListView) findViewById(R.id.listDays);
        adapter = new ArrayAdapter<String>(this, R.layout.list_day, days);
        dayList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dayList.setItemsCanFocus(false);
        dayList.setAdapter(adapter);
        // checks which days are checked off
        dayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(daysOfHabit.contains(days[position]) == false) {
                    daysOfHabit.add(days[position]);
                }
            }
        });
    }

    // adds the habit, sending data back to main activity
    public void addHabit(View view) {
        Intent intent = new Intent();
        String habit_name = bodyText.getText().toString();
        if(habit_name == null || daysOfHabit.size() == 0) {
            finish();
        }
        intent.putStringArrayListExtra("daysOfWeek", daysOfHabit);
        intent.putExtra("habitResult", habit_name);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    // change fonts
    public void setFont() {
        TextView textview = (TextView) findViewById(R.id.AddHabitTitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "CaviarDreams_BoldItalic.ttf");
        textview.setTypeface(typeface);
        typeface = Typeface.createFromAsset(getAssets(), "Caviar_Dreams_Bold.ttf");
        Button buttonText = (Button) findViewById(R.id.button);
        buttonText.setTypeface(typeface);
        buttonText = (Button) findViewById(R.id.button2);
        buttonText.setTypeface(typeface);
    }

    public void cancelHabit(View view) {
        finish();
    }
}






