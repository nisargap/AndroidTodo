package com.example.todo.androidtodo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.todo.db.TaskTableCreator;
import com.example.todo.db.TaskTableHelper;

import java.util.ArrayList;

/*********************
 *  Tutorial By: Nisarga Patel
 *  Mostly everything commented out is part of the database version
 *  If you want to read a tutorial on the database version go to:
 *  http://www.sitepoint.com/starting-android-development-creating-todo-app/
 *  It's a great tutorial by a dude by the name of Aldo Ziflaj but I had to make
 *  some minor changes to his tutorial to get it to work with the current Android Studio.
 *
 *********************/
public class MainActivity extends AppCompatActivity {

    // Database Version variable
    //private TaskTableHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        updateUI();
    }
    // Database Version variable
    // private SimpleCursorAdapter listAdapter;

    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> items;

    private void updateUI() {

        /* Database Version method part
        helper = new TaskTableHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskTableCreator.TABLE,
                new String[]{TaskTableCreator.Columns._ID, TaskTableCreator.Columns.TASK},
                null, null, null, null, null);
        */


        taskAdapter = new ArrayAdapter<>(this, R.layout.task_view, R.id.taskTextView, items);
        /* Database Version method part
        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[]{TaskTableCreator.Columns.TASK},
                new int[]{R.id.taskTextView},
                0
        );
        */

        // Needed for all versions
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(taskAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_add_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add a task");
                builder.setMessage("What do you want to do?");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String task = inputField.getText().toString();
                        Log.d("MainActivity",task);
                        items.add(task);

                        /* Database Version
                        helper = new TaskTableHelper(MainActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.clear();
                        values.put(TaskTableCreator.Columns.TASK, task);

                        db.insertWithOnConflict(TaskTableCreator.TABLE, null, values,
                                SQLiteDatabase.CONFLICT_IGNORE);
                        */
                        updateUI();
                    }

                });

                builder.setNegativeButton("Cancel",null);

                builder.create().show();
                return true;

            default:
                return false;
        }
    }
    public void onDoneClick(View view) {
        /* Database Version
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();
        String sql = String.format("DELETE FROM %s WHERE %s = %s",
                TaskTableCreator.TABLE,
                TaskTableCreator.Columns.TASK,
                task);
        Log.d("MainActivity", sql);

        helper = new TaskTableHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();

        String whereClause = TaskTableCreator.Columns.TASK + "=?";
        sqlDB.delete(TaskTableCreator.TABLE, whereClause, new String[]{task});
        */
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

        for (int i = 0; i < items.size(); i++){

            if(items.get(i).equals(task)){

                items.remove(i);
            }
        }

        // Debug Statement
        // Log.d("MainActivity",Integer.toString(R.id.taskTextView));

        updateUI();
    }
}
