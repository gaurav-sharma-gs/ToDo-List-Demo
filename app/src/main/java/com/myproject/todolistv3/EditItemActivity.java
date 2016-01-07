package com.myproject.todolistv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditItemActivity extends AppCompatActivity {

    EditText editTitleEditText;
    EditText editDescriptionEditText;
    int id;
    Spinner spinner;
    private static final String[]paths = {"P0","P1","P2","P3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();
        ToDoItem item = (ToDoItem) intent.getParcelableExtra("EDIT_ITEM");
//        Log.i("YOYOYOYOYOYOYOYO", String.valueOf(item.getId()));
//        Log.i("YOYOYOYOYOYOYOYO", String.valueOf(item.getTitle()));
        editTitleEditText = (EditText) findViewById(R.id.editTitleEditText);
        editDescriptionEditText = (EditText) findViewById(R.id.editDescriptionEditText);
        editTitleEditText.setText(item.getTitle());
        editDescriptionEditText.setText(item.getDiscription());

        spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.set
//        spinner.setSelection();

        spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(item.getPriority()));


        id = item.getId();


    }

    public void doneEdit(View view) {
        String title = editTitleEditText.getText().toString();
        Log.i("BhaiSahab",title);
        String description = editDescriptionEditText.getText().toString();
        String priority = spinner.getSelectedItem().toString();


        ToDoItem item = new ToDoItem(title, description,priority, 0);
        item.setId(id);
        Log.i("BhaiSahab", String.valueOf(item.getId()));
        Intent intent = new Intent();
        Log.i("BhaiSahab",item.toString());
        intent.putExtra("EDIT_RESULT", item);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelEdit(View view) {
        finish();
    }

}
