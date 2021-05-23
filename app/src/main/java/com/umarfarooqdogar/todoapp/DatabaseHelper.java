package com.umarfarooqdogar.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TIME_COLUMN = "time";
    public static final String DATE_COLUMN = "date";
    public static final String PRIORITY_COLUMN = "priority";
    public static final String TASKNAME_COLUMN = "taskname";
    public static final String ID_COLUMN = "id";
    public static final String TABLE_NAME = "tasks";
    public static final String ITEMS_COLUMN = "items";
    String COLUMN_ID= ID_COLUMN;


    public DatabaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String createTableQuery= "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASKNAME_COLUMN + " STRING , " + PRIORITY_COLUMN + " INTEGER , " + DATE_COLUMN + " String , " + TIME_COLUMN + " String , " + ITEMS_COLUMN + " String)";
      db.execSQL(createTableQuery);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addOne(task task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TASKNAME_COLUMN , task.getTaskname());
        cv.put(PRIORITY_COLUMN , task.getPriority());
        cv.put(DATE_COLUMN , task.getDate());
        cv.put(TIME_COLUMN , task.getTime());
        cv.put(ITEMS_COLUMN , task.getItems());

        db.insert(TABLE_NAME,null,cv);
    }

    public void deleteOne(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM " + TABLE_NAME +" WHERE " + COLUMN_ID + "=" + id;
        db.execSQL(query);
    }

    public ArrayList<task> getAllItems() {
        ArrayList<task> returnList=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        String queryforAll="SELECT * FROM "+TABLE_NAME ;
        Cursor cursor = db.rawQuery(queryforAll, null);
        if(cursor.moveToFirst())
        {
            do
            {
             int id=cursor.getInt(0);
             String taskName=cursor.getString(1);
             int prior=cursor.getInt(2);
             String date=cursor.getString(3);
             String time=cursor.getString(4);
             String items=cursor.getString(5);

             task task=new task(id,prior,taskName,date,time,items);
             returnList.add(task);

              }while (cursor.moveToNext());

        }else{
          return null;
        }


    return returnList;
    }
}
