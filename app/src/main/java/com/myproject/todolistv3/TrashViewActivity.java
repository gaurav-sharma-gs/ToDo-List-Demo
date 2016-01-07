package com.myproject.todolistv3;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class TrashViewActivity extends AppCompatActivity {

    ArrayList<ToDoItem> toDoItems;
    private TODoItemTrashDatabase todoItemTrashDatabase;
    ListView trashItemsListView;
    ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_view);
        todoItemTrashDatabase = new TODoItemTrashDatabase(getBaseContext());
        readDatabase();

        trashItemsListView = (ListView) findViewById(R.id.listView3);
        adapter = new ToDoListAdapter(getBaseContext(), toDoItems);
        trashItemsListView.setAdapter(adapter);

        trashItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TrashViewActivity.this, ViewTrashItemActivity.class);
                intent.putParcelableArrayListExtra("TODO_ITEMS", toDoItems);
                intent.putExtra("SELECTED_POSITION", position);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
    private void updateUI() {
        readDatabase();
        adapter = new ToDoListAdapter(getBaseContext(), toDoItems);
        trashItemsListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void readDatabase() {
        toDoItems = (ArrayList) todoItemTrashDatabase.getAllTodoItemsFromTrash();
    }

}
