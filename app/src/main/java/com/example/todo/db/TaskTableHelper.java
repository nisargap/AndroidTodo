package com.example.todo.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by nisarga on 10/2/15.
 */
public class TaskTableHelper extends SQLiteOpenHelper {

    public TaskTableHelper(Context context) {
        super(context, TaskTableCreator.DB_Name, null, TaskTableCreator.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery =
                String.format("CREATE TABLE %s (" +
                                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s TEXT)", TaskTableCreator.TABLE,
                        TaskTableCreator.Columns.TASK);

        Log.d("TaskDBHelper","Query to form table: "+sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS "+TaskTableCreator.TABLE);
        onCreate(sqlDB);
    }
}
