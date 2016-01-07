package com.myproject.todolistv3;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by gaurav.gs on 15/12/15.
 */

public class ToDoItem implements Parcelable {

    private int id;
    private String title;
    private String discription;
    private Time dueTime;
    private String priority;
    private Integer is_deleted;

    public ToDoItem(String title, String discription, String priority, Integer is_deleted) {
        this.title = title;
        this.discription = discription;
        this.priority = priority;
        this.is_deleted = is_deleted;
    }

    protected ToDoItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        discription = in.readString();
        priority = in.readString();
        is_deleted = in.readInt();
    }

    public static final Creator<ToDoItem> CREATOR = new Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel in) {
            return new ToDoItem(in);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(discription);
        dest.writeString(priority);
        dest.writeInt(is_deleted);
    }
}
