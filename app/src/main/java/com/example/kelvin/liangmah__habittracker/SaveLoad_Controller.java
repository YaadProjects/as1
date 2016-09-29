//package com.example.kelvin.liangmah__habittracker;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
///**
// * Created by Kelvin on 9/28/2016.
// */
//public class SaveLoad_Controller {
//
//    public SaveLoad_Controller() {}
//
//    private void loadFromFile() {
//        try {
//            FileInputStream fis = openFileInput(FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//
//            Gson gson = new Gson();
//
//            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
//            Type listType = new TypeToken<ArrayList<Normal_Habit>>(){}.getType();
//
//            habitList = gson.fromJson(in,listType);
//
//        } catch (FileNotFoundException e) {
//            habitList = new ArrayList<Habit>();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }
//
//    private void saveInFile() {
//        try {
//            FileOutputStream fos = openFileOutput(FILENAME, 0);
//
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
//
//            Gson gson = new Gson();
//            gson.toJson(habitList, out);
//            out.flush();
//
//            fos.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }
//
//    public void clear() {
//        habitList.clear();
//        deleteFile(FILENAME); // delete file
//        adapter.notifyDataSetChanged(); // update}
//    }
//}
