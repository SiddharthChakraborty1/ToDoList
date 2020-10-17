package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<String> notes;
    Context context;


    public RecyclerAdapter(List<String> notes) {
        this.notes = notes;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
       View view =  layoutInflater.inflate(R.layout.recycler_item_layout,parent,false);
       ViewHolder viewHolder = new ViewHolder(view);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.note.setText(notes.get(position));



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView note;
        Context context;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            note = itemView.findViewById(R.id.tvNote);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new AlertDialog.Builder(context)
                            .setTitle("Are you sure?")
                            .setMessage("This task will be permanently deleted")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    notes.remove(getAdapterPosition());
                                    PrefConfig.writeList(context);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();

                                }
                            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();


                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String existingNote = note.getText().toString();
                    Intent intent = new Intent(context,TakeNoteActivty.class);
                    intent.putExtra("existing",existingNote);
                    intent.putExtra("position",getAdapterPosition());
                    context.startActivity(intent);


                }
            });

        }
    }
}
