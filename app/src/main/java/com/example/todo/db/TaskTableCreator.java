/**
 * Created by nisarga on 10/2/15.
 */
package com.example.todo.db;

import android.provider.BaseColumns;

public class TaskTableCreator {

    public static final String DB_Name = "com.example.todo.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
        public static final String TASK = "task";
        public static final String _ID = BaseColumns._ID;
    }

}
