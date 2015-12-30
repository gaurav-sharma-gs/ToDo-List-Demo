package com.myproject.todolistv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ITEM_REQUEST_CODE = 101;
    ArrayList<ToDoItem> toDoItems;
    ToDoListAdapter adapter;
    ListView itemsListView;
    private TODoItemDatabase todoItemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoItemDatabase = new TODoItemDatabase(getBaseContext());
        readDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        if (savedInstanceState != null) {
//            toDoItems = savedInstanceState.getParcelableArrayList("ITEMS");
//        } else {
//            toDoItems = new ArrayList<>();
//            ToDoItem item = new ToDoItem("Title 1","This is description");
//            ToDoItem item2 = new ToDoItem("Title 2","This is description");
//            toDoItems.add(item);
//            toDoItems.add(item2);
//        }
        itemsListView = (ListView) findViewById(R.id.listView);
        adapter = new ToDoListAdapter(getBaseContext(), toDoItems);
        itemsListView.setAdapter(adapter);

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ToDoItemViewActivity.class);
                intent.putParcelableArrayListExtra("TODO_ITEMS", toDoItems);
                intent.putExtra("SELECTED_POSITION", position);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    public void addItem() {
        Intent intent = new Intent(this, AddToDoItemActivity.class);
        startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
    }

    private void readDatabase() {
        toDoItems = (ArrayList) todoItemDatabase.getAllTodoItems();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                ToDoItem item = data.getParcelableExtra("ADD_ITEM");
                todoItemDatabase.addTodoItem(item);
                toDoItems.add(item);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("ITEMS", toDoItems);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        toDoItems = (ArrayList) todoItemDatabase.getAllTodoItems();
        updateUI();
    }

    private void updateUI() {
        adapter.notifyDataSetChanged();
    }

}
