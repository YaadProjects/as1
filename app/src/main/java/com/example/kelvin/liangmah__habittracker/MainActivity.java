package com.example.kelvin.liangmah__habittracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// Main activity of habittracker
// displays the current day's activities


public class MainActivity extends AppCompatActivity {

    private ListView oldHabitsList;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayList<Habit> MasterHabitList = new ArrayList<>();
    private ArrayAdapter<Habit> adapter;
    // intent requests
    static final int add_habit_request = 1;
    static final int history_request = 2;
    static final int completion_request = 3;
    private int curPos = 0;
    private SaveLoad_Controller saveController;
    private int dayIndex;
    private String dayOfWeek = getDayOfWeek(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    private final String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveController = new SaveLoad_Controller(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oldHabitsList = (ListView) findViewById(R.id.oldHabitsList);


        // find current day
        TextView textView = (TextView) findViewById(R.id.currentDay);
        textView.setText(dayOfWeek + "'s Habits: ");

        // clears list of habits
        Button clearButton = (Button) findViewById(R.id.options);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                // clear list
                saveController.clear();
                updateList();
                adapter.notifyDataSetChanged();
            }
        });
        // sets button to listen so it can call the appropriate dialog
        Button dayButton = (Button) findViewById(R.id.changeDay);
        dayButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                day();
            }
        });
        // sets button to listen so it can start the next activity
        Button historyButton = (Button) findViewById(R.id.history);
        historyButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                loadCompletionHistory();
            }
        });

        // allows for list to be clickable
        oldHabitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Habit selectedHabit = habitList.get(position);
                curPos = MasterHabitList.indexOf(selectedHabit);
                loadHistoryPage(selectedHabit);
            }
        });
    }

    // Code From Lonely Twitter
    @Override
    protected void onStart() {
        super.onStart();
        updateList();
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
    // starts activity for adding a habit
    public void loadHabitPage() {
        Intent intent = new Intent(this, habit_activity.class);
        startActivityForResult(intent, add_habit_request);
    }
    // starts activity to show history of a single habit
    public void loadHistoryPage(Habit myHabit) {
        Intent intent = new Intent(this, history_activity.class);
        intent.putExtra("habit", myHabit);
        setResult(Activity.RESULT_OK, intent);
        startActivityForResult(intent, history_request);
    }
    // starts activity to show history of all completed habits
    public void loadCompletionHistory() {
        Intent intent = new Intent(this, completion_activity.class);
        setResult(Activity.RESULT_OK, intent);
        startActivityForResult(intent, completion_request);
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
                    MasterHabitList.add(newHabit);
                    saveController.saveInFile();
                    updateList();
                }
            }

            // update habit that has changed
            case (history_request): {
                if (resultCode == Activity.RESULT_OK) {
                    if(requestCode != 2) {break;}
                    String isDelete = data.getStringExtra("Delete");
                    // delete the habit
                    if(isDelete.equals("Delete")) {
                        MasterHabitList.remove(curPos);
                        saveController.saveInFile();
                        updateList();
                    } else  {
                        // update habit stats
                        Habit updateHabit = (Habit) data.getSerializableExtra("habitResult");
                        MasterHabitList.remove(curPos);
                        MasterHabitList.add(curPos,updateHabit);
                        saveController.saveInFile();
                        updateList();
                    }
                }
            }
        }
    }

    // used when day button is clicked
    // allows user to change day of the week
    private void day () {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Pick The day of the week")
                .setSingleChoiceItems(week, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dayIndex = whichButton;
                        dayOfWeek = week[dayIndex];
                    }
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView = (TextView) findViewById(R.id.currentDay);
                        textView.setText(dayOfWeek + "'s Habits: ");
                        updateList();
                    }
                })
                .create();
        dialog.show();
    }

    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }
    // generates a new habitlist for the habits that occur on the given day
    public ArrayList<Habit> habitList_forCurrentDay(String day, ArrayList<Habit> curList) {
        ArrayList<Habit> newList = new ArrayList<Habit>();
        ArrayList<String> daysOfHabit;
        for(Habit habit : curList) {
            daysOfHabit = habit.getDaysOfHabit();
            if(daysOfHabit.contains(day)) {
                newList.add(habit);
            }
        }
        return newList;
    }
    // updates all lists and resets the view
    public void updateList() {
        MasterHabitList = saveController.loadFromFile();
        habitList = habitList_forCurrentDay(dayOfWeek, MasterHabitList);
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.habit_view, habitList);
        oldHabitsList.setAdapter(adapter);
    }
}
