package com.myproject.todolistv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav.gs on 31/12/15.
 */
public class TODoItemDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "todoListDatabase";

    // Todo table name
    private static final String TABLE_TODO = "todo_items";

    // Todo Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_IS_DELETED = "deleted";


    public TODoItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRIORITY + " TEXT," + KEY_IS_DELETED + " INTEGER" + ")";

        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 7) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
            onCreate(db);
        }
    }

    // Insert record into the database
    public void addTodoItem(ToDoItem item) {
        // Open database connection
        SQLiteDatabase db = this.getWritableDatabase();
        // Define values for each field
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, item.getTitle());
        values.put(KEY_DESCRIPTION, item.getDiscription());
        values.put(KEY_PRIORITY, item.getPriority());
        values.put(KEY_IS_DELETED, item.getIs_deleted());
        // Insert Row
        db.insertOrThrow(TABLE_TODO, null, values);
        db.close(); // Closing database connection
    }

    public ToDoItem getTodoItem(int id) {
        // Open database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Construct and execute query
        Cursor cursor = db.query(TABLE_TODO,  // TABLE
                new String[]{KEY_ID, KEY_TITLE, KEY_DESCRIPTION}, // SELECT
                KEY_ID + "= ?", new String[]{String.valueOf(id)},  // WHERE, ARGS
                null, null, "id ASC", "100"); // GROUP BY, HAVING, ORDER BY, LIMIT
        if (cursor != null)
            cursor.moveToFirst();
        // Load result into model object
        ToDoItem item = new ToDoItem(cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getInt(4));
        item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
        // return todo item
        return item;
    }



    public int getIdForItem(ToDoItem item) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TODO,  // TABLE
                new String[]{KEY_ID}, // SELECT
                KEY_TITLE + " = ? AND " + KEY_DESCRIPTION + " = ?",
                new String[]{String.valueOf(item.getTitle()), String.valueOf(item.getDiscription())},  // WHERE, ARGS
                null, null, null, null); // GROUP BY, HAVING, ORDER BY, LIMIT
        cursor.moveToFirst();
        int id = cursor.getColumnIndex("id");
        return cursor.getInt(id);
    }

    public List<ToDoItem> getAllTodoItems() {
        List<ToDoItem> todoItems = new ArrayList<ToDoItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE " + KEY_IS_DELETED + " = 0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDoItem item = new ToDoItem(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4));
                item.setId(cursor.getInt(0));
                // Adding todo item to list
                todoItems.add(item);
            } while (cursor.moveToNext());
        }

        // return todo list
        return todoItems;
    }

    public int getTodoItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TODO + "WHERE" + KEY_IS_DELETED + " = 0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    public int updateTodoItem(ToDoItem item) {
        // Open database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Setup fields to update
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, item.getTitle());
        values.put(KEY_DESCRIPTION, item.getDiscription());
        values.put(KEY_PRIORITY, item.getPriority());
        values.put(KEY_IS_DELETED, item.getIs_deleted());
        // Updating row
        int result = db.update(TABLE_TODO, values, KEY_ID +  " = ?",
                new String[]{String.valueOf(item.getId())});
        // Close the database
        db.close();
        return result;
    }

    public void hardDeleteTodoItem(ToDoItem item) {
        // Open database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Setup fields to update
        db.delete(TABLE_TODO,KEY_ID + " =? ", new String[] {String.valueOf(item.getId())});
        // Close the database
        db.close();
        return;
    }

    public void deleteTodoItem(ToDoItem item) {
        // Open database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the record with the specified id
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        // Close the database
        db.close();
    }
}
