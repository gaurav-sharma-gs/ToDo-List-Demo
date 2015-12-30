package com.myproject.todolistv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText editTitleEditText;
    EditText editDescriptionEditText;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();
        ToDoItem item = intent.getParcelableExtra("EDIT_ITEM");
        Log.i("YOYOYOYOYOYOYOYO", String.valueOf(item.getId()));
        Log.i("YOYOYOYOYOYOYOYO", String.valueOf(item.getTitle()));
        editTitleEditText = (EditText) findViewById(R.id.editTitleEditText);
        editDescriptionEditText = (EditText) findViewById(R.id.editDescriptionEditText);
        editTitleEditText.setText(item.getTitle());
        editDescriptionEditText.setText(item.getDiscription());
        id = item.getId();
    }

    public void doneEdit(View view) {
        String title = editTitleEditText.getText().toString();
        Log.i("BhaiSahab",title);
        String description = editDescriptionEditText.getText().toString();
        ToDoItem item = new ToDoItem(title, description);
        item.setId(id);
        Log.i("BhaiSahab", String.valueOf(item.getId()));
        Intent intent = new Intent();
        Log.i("BhaiSahab",item.toString());
        intent.putExtra("EDIT_ITEM", item);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelEdit(View view) {
        finish();
    }

}
