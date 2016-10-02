package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


// This class handles the habit history
// will display when habit was created, the count for the number of times habit has been done
// will also display days that habit is to be completed.
// and will give options for the user to delete the habit, add to completion count

public class habit_history extends AppCompatActivity {

    private Habit currentHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);

        // get habit obj
        Intent intent = getIntent();
        currentHabit = (Habit) intent.getSerializableExtra("habit");
        setHabitViews(currentHabit);
        completedToday();
        setFont();
    }

    // sets checkbox view if habit was completed today
    public void completedToday() {
        if(currentHabit.hasCompletions()) {
            Date curDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
            String curDateString = sdf.format(curDate).toString();
            String recHabitCompleteDate = sdf.format(currentHabit.getDatesCompleted().get(currentHabit.getCount()-1));
            if(curDateString.equals(recHabitCompleteDate)) {
                CheckBox checkBox1 = (CheckBox) findViewById(R.id.text1);
                checkBox1.setChecked(true);
            }
        }
    }

    public void raiseCount(View view) {
        currentHabit.increaseCount();
        String newCount = String.valueOf(currentHabit.getCount());
        ArrayList<Date> checkDate = currentHabit.getDatesCompleted();
        Date date = new Date();
        if(checkDate.contains(date) == false) {
            currentHabit.addDate(date);
        }
        updateTextView(newCount);
        completedToday();
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

    // sets the habit text view
    // finds info within habit class
    public void setHabitViews(Habit currentHabit) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        String curDateString = sdf.format(currentHabit.getDate()).toString();

        // Set the text views
        TextView textView = (TextView) findViewById(R.id.AddHabitTitle);
        textView.setText(currentHabit.getHabitName());
        textView = (TextView) findViewById(R.id.habit_date);
        textView.setText("Added on: " + curDateString);
        textView = (TextView) findViewById(R.id.habitCount);
        textView.setText("Times Completed: " + String.valueOf(currentHabit.getCount()));
        textView = (TextView) findViewById(R.id.days);
        ArrayList<String> days = currentHabit.getDaysOfHabit();
        textView.setText(findDays(days));
    }

    // iterates through the list of days contained within habit
    // finds the days in which habit is supposed to occur
    public String findDays( ArrayList<String> days) {
        String daysOfHabit = "Days Habit occurs:\n\n ";
        for(String day : days) {
            if(day.equals("Monday")) {
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

    // change fonts
    public void setFont() {
        TextView textview = (TextView) findViewById(R.id.AddHabitTitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "CaviarDreams_BoldItalic.ttf");
        textview.setTypeface(typeface);
        typeface = Typeface.createFromAsset(getAssets(), "CaviarDreams.ttf");
        textview = (TextView) findViewById(R.id.habit_date);
        textview.setTypeface(typeface);
        textview = (TextView) findViewById(R.id.habitCount);
        textview.setTypeface(typeface);
        textview = (TextView) findViewById(R.id.days);
        textview.setTypeface(typeface);
        CheckBox checkText = (CheckBox) findViewById(R.id.text1);
        checkText.setTypeface(typeface);
        typeface = Typeface.createFromAsset(getAssets(), "Caviar_Dreams_Bold.ttf");
        Button buttonText = (Button) findViewById(R.id.button5);
        buttonText.setTypeface(typeface);
        buttonText = (Button) findViewById(R.id.button4);
        buttonText.setTypeface(typeface);
        buttonText = (Button) findViewById(R.id.button3);
        buttonText.setTypeface(typeface);
    }
}
