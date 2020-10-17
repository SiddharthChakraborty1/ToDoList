package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {


    private static final String LIST_KEY ="list_key" ;

    public static void writeList(Context context)
    {   Gson gson = new Gson();
        String jString = gson.toJson(MainActivity.notes);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LIST_KEY,jString);
        editor.apply();


    }

    public static ArrayList<String> getList(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jString = sharedPreferences.getString(LIST_KEY,"");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> list = gson.fromJson(jString,type);

        return list;
    }
}
