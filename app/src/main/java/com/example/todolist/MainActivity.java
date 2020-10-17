package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.FingerprintGestureController;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
   static ArrayList<String> notes;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuAddNote:
                Intent intent = new Intent(MainActivity.this,TakeNoteActivty.class);
                PrefConfig.writeList(getApplicationContext());
                startActivity(intent);

                return true;
            default:
                return false;
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notes = new ArrayList<>();






        Log.i("reach",Integer.toString(notes.size()));

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.todolist", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("first_time",true))
        {
            notes.add("Click to add task");
            sharedPreferences.edit().putBoolean("first_time",false).apply();
        }
        else
        {   notes = PrefConfig.getList(getApplicationContext());

        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(notes);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);



    }
}
