package com.myproject.todolistv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddToDoItemActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText descriptionEditText;
    Spinner spinner;
    private static final String[] paths = {"P0", "P1", "P2", "P3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_item);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);

        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paths);
//
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void done(View view) {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String priority = spinner.getSelectedItem().toString();

        ToDoItem item = new ToDoItem(title, description, priority, 0);


        Intent intent = new Intent();
        intent.putExtra("ADD_ITEM", item);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        finish();
    }

}
