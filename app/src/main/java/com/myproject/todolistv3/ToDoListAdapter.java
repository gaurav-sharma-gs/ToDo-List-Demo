package com.myproject.todolistv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav.gs on 25/12/15.
 */
public class ToDoListAdapter extends BaseAdapter {

    Context context;
    List<ToDoItem> toDoItems;

    public ToDoListAdapter(Context context, List<ToDoItem> toDoItems) {
        this.context = context;
        this.toDoItems = toDoItems;
    }

    @Override
    public int getCount() {
        return toDoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mainView;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            mainView = inflater.inflate(R.layout.list_row, null);
            ViewHolder vh = new ViewHolder();
            vh.titleTextView = (TextView) mainView.findViewById(R.id.titleTextView);
            mainView.setTag(vh);
        } else {
            mainView = convertView;
        }

        String title = toDoItems.get(position).getTitle();
        ViewHolder vh = (ViewHolder) mainView.getTag();
        vh.titleTextView.setText(title);
        return mainView;
    }

    private static class ViewHolder {
        TextView titleTextView;

    }
}
