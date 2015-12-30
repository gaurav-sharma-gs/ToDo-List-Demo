package com.myproject.todolistv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

public class AddToDoItemActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_item);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
    }

    public void done(View view) {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        ToDoItem item = new ToDoItem(title, description);

        Intent intent = new Intent();
        intent.putExtra("ADD_ITEM", item);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        finish();
    }

}
