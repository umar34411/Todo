package com.umarfarooqdogar.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper=new DatabaseHelper(this);
    public static ArrayList<task> list;
    RecyclerView rv;
    RecyclerView.LayoutManager manager;
    public static RecyclerAdapter adapter;
    FloatingActionButton btnAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();



           adapter = new RecyclerAdapter(list, MainActivity.this);

           rv.setAdapter(adapter);




    }

    private void init() {
        manager= new LinearLayoutManager(this);
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(manager);
        btnAction=findViewById(R.id.floating_action_button);

        getAllItems();

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it=new Intent(MainActivity.this,AddTask.class);
                startActivity(it);
            }
        });
    }

    public  void getAllItems()
    {
        list=dbHelper.getAllItems();
    }

    public void HandleClick(int position)
    {
        Intent it=new Intent(MainActivity.this,AddTask.class);
        it.putExtra("task",list.get(position));
        startActivity(it);
    }
    public void HandleLongClick(int position)
    {
        list.remove(list.get(position));
       adapter.notifyDataSetChanged();
       dbHelper.deleteOne(position);
    }
}