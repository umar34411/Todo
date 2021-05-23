package com.umarfarooqdogar.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class AddTask extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    Button dateButton;
    Button timeButton,btnSave;
    int hour, minute;
    EditText et_TaskName,et_ItemName;
    ImageView iv_AddItem;
    ListView rv_items;
    CheckBox h_priority,l_priority,m_priority;
    RecyclerView.LayoutManager manager;
    ArrayList<String> items=new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;
    DatabaseHelper dbHelper=new DatabaseHelper(this);
    task task=new task();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initDatePicker();
       init();

       Intent it=getIntent();
       task=it.getParcelableExtra("task");

       if(task!=null)
       {
           int priority=task.getPriority();
          String its[]=task.items.split(",");
           for (String item:its) {
               items.add(item);
           }
          et_TaskName.setText(task.getTaskname());
          dateButton.setText(task.getDate());
          timeButton.setText(task.getTime());

          if(priority==1)
          {
              l_priority.setChecked(true);
              m_priority.setChecked(false);
              h_priority.setChecked(false);
          }else if(priority==2)
          {
              l_priority.setChecked(false);
              m_priority.setChecked(true);
              h_priority.setChecked(false);
          }else
          {
              l_priority.setChecked(false);
              m_priority.setChecked(false);
              h_priority.setChecked(true);
          }


       }

     // rv_items.setLayoutManager(manager);
        itemsAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        rv_items.setAdapter(itemsAdapter);


       dateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openDatePicker(v);
           }
       });
    }

    private void init() {
        dateButton = findViewById(R.id.datePicker);
        timeButton = findViewById(R.id.timeButton);
        manager=new LinearLayoutManager(this);
        et_TaskName=findViewById(R.id.et_Task_Name);
        et_ItemName=findViewById(R.id.et_ItemName);
        iv_AddItem=findViewById(R.id.iv_addItem);
        rv_items=findViewById(R.id.rv_items);
        h_priority=findViewById(R.id.HighPriority);
        m_priority=findViewById(R.id.MediumPriority);
        l_priority=findViewById(R.id.LowPriority);
        btnSave=findViewById(R.id.btnSave);

        dateButton.setText(getTodaysDate());

       iv_AddItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String itemName=et_ItemName.getText().toString().trim();
               if(!itemName.isEmpty())
               {
                   items.add(itemName);
                   itemsAdapter.notifyDataSetChanged();
                   et_ItemName.setText("");


               }
           }
       });

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String t_name,items_name="",date,time;
               Integer t_priority;

               for (String item:items) {
                 items_name=items_name+item+",";
               }

               t_name=et_TaskName.getText().toString().trim();
               date=dateButton.getText().toString().trim();
               time=timeButton.getText().toString().trim();
               if(h_priority.isChecked())
               {
                   t_priority=3;
               }else if(m_priority.isChecked())
               {
                   t_priority=2;
               }else{
                   t_priority=1;
               }

               task task=new task(t_priority,t_name,date,time,items_name);

               MainActivity.list.add(task);
               MainActivity.adapter.notifyDataSetChanged();
               dbHelper.addOne(task);
               finish();
           }
       });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}