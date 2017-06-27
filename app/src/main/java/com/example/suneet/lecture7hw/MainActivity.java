package com.example.suneet.lecture7hw;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{

    ArrayList<TodoList> todoLists=new ArrayList<>();
    EditText editText;
    ImageButton imageButton,delete,remove_item;
    RecyclerView recyclerView;
    TodoListAdapter todoListAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.todoitem);
        imageButton=findViewById(R.id.add);
        imageButton.setOnClickListener(this);
         recyclerView=findViewById(R.id.recyclerView);
        delete=findViewById(R.id.deleteMe);
        delete.setOnClickListener(this);
        remove_item=findViewById(R.id.remove_item);
        remove_item.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        if((savedInstanceState!=null)&&(savedInstanceState.containsKey("KEY")))
        {
            todoLists= (ArrayList<TodoList>) savedInstanceState.get("KEY");
            todoListAdapter=new TodoListAdapter(todoLists,this);
            recyclerView.setAdapter(todoListAdapter);

        }else {
            todoListAdapter = new TodoListAdapter(todoLists, this);
            recyclerView.setAdapter(todoListAdapter);
            todoListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.add)
        {

            String s=editText.getText().toString();
            if(s.trim().length()==0)
            {
                Toast.makeText(this,"Write Some Shit",Toast.LENGTH_SHORT).show();

            }else {
                todoLists.add(new TodoList(s, false));
            }
            todoListAdapter.notifyDataSetChanged();


        }
        if(view.getId()==R.id.deleteMe){

            if(todoLists.size()==0)
            {
                Toast.makeText(this, "Nothing to Delete", Toast.LENGTH_SHORT).show();
            }
            else {
                for (int i = 0; i < todoLists.size(); i++) {
                        if(todoLists.get(i).isStatus()){
                            todoLists.remove(i);
                        }
                        else
                            Toast.makeText(this,"Select Items to Delete",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            }


        }
        if(view.getId()==R.id.remove_item)
        {
            editText.setText("");
            Log.e("TAG", "onClick: "+"clicked" );
        }
        todoListAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("KEY",todoLists);
    }
}
