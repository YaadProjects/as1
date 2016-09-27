package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class habit_activity extends AppCompatActivity {
    private EditText bodyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_activity);
        bodyText = (EditText) findViewById(R.id.habit_name);
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



