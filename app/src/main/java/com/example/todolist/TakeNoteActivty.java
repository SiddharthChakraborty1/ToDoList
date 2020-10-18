package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;





public class TakeNoteActivty extends AppCompatActivity {
    Button button;
    EditText task;
    int alreadyExisting = 0;
    int position;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note_activty);

        String Existing = getIntent().getStringExtra("existing");
        position = getIntent().getIntExtra("position",-1);







        task =(EditText) findViewById(R.id.etTask);
        if(Existing != null)
        {
            task.setText(Existing);


        }

        button = (Button) findViewById(R.id.btnAddTask);


    }

    public void addNote(View view)
    {

        if(task.getText().toString().isEmpty()) {
            task.setError("Can't insert empty tasks");
            task.requestFocus();
        }
        else {

            if (position == -1) {
                MainActivity.notes.add(task.getText().toString());

            } else {
                MainActivity.notes.set(position, task.getText().toString());
            }
            PrefConfig.writeList(getApplicationContext());
            Log.i("Updated", Integer.toString(MainActivity.notes.size()));
            MainActivity.recyclerView.getAdapter().notifyDataSetChanged();
            finish();

        }





    }
}