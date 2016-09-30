package com.example.kelvin.liangmah__habittracker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Kelvin on 9/28/2016.
 */
public class SaveLoad_Controller {
    private Context context;
    private ArrayList<Habit> MasterHabitList = new ArrayList<Habit>();
    private static final String FILENAME = "file.sav";

    public SaveLoad_Controller(Context context) {
        this.context = context;
    }

    public ArrayList<Habit> loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Normal_Habit>>() {
            }.getType();

            MasterHabitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            MasterHabitList = new ArrayList<Habit>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return MasterHabitList;
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(MasterHabitList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void clear() {
        MasterHabitList.clear();
        context.deleteFile(FILENAME); // delete file
    }
}
