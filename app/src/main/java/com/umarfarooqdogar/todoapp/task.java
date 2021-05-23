package com.umarfarooqdogar.todoapp;

import android.os.Parcel;
import android.os.Parcelable;

public class task implements Parcelable {

    int id,priority;
    String taskname,date,time,items;

    public task() {
    }

    public task(int id, int priority, String taskname, String date, String time, String items) {
        this.id = id;
        this.priority = priority;
        this.taskname = taskname;
        this.date = date;
        this.time = time;
        this.items = items;
    }

    public task(int priority, String taskname, String date, String time, String items) {
        this.priority = priority;
        this.taskname = taskname;
        this.date = date;
        this.time = time;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "task{" +
                "id=" + id +
                ", priority=" + priority +
                ", taskname='" + taskname + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", items='" + items + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.priority);
        dest.writeString(this.taskname);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.items);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.priority = source.readInt();
        this.taskname = source.readString();
        this.date = source.readString();
        this.time = source.readString();
        this.items = source.readString();
    }

    protected task(Parcel in) {
        this.id = in.readInt();
        this.priority = in.readInt();
        this.taskname = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.items = in.readString();
    }

    public static final Parcelable.Creator<task> CREATOR = new Parcelable.Creator<task>() {
        @Override
        public task createFromParcel(Parcel source) {
            return new task(source);
        }

        @Override
        public task[] newArray(int size) {
            return new task[size];
        }
    };
}
