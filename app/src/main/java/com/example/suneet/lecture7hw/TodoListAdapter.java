package com.example.suneet.lecture7hw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by suneet on 26/6/17.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    ArrayList<TodoList> todoLists;
    Context c;

    public TodoListAdapter(ArrayList<TodoList> todoLists, Context c) {
        this.todoLists = todoLists;
        this.c = c;
    }

    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=LayoutInflater.from(c).inflate(R.layout.single_item,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoListAdapter.ViewHolder holder, int position) {
        final TodoList todoList=todoLists.get(position);
        holder.textView.setText(todoList.getItem());
        boolean s=(todoList.isStatus());
        holder.checkBox.setChecked(s);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                todoList.setStatus(b);
            }
        });


    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.todoText);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }
}
