package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class habit_activity extends AppCompatActivity {
    private EditText bodyText;
    private ListView dayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String [] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_activity);
        bodyText = (EditText) findViewById(R.id.habit_name);
        dayList = (ListView) findViewById(R.id.listDays);
        adapter = new ArrayAdapter<String>(this,R.layout.list_day, days);
        dayList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dayList.setItemsCanFocus(false);
        dayList.setAdapter(adapter);


    }

    public void addHabit(View view) {
        Intent intent = new Intent();
        String message = bodyText.getText().toString();
        intent.putExtra("habitResult", message);
        setResult(Activity.RESULT_OK, intent);
        finish();
        }

    public void cancelHabit(View view) {
        finish();
    }
}




