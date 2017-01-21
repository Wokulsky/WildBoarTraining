package com.example.karol.wildboartraining.DataBasePackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAdapter {
    private static final String baseName = "database.db";
    private static final String tableName = "excercises";
    private static final String keyID = "id";
    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;
    public String BuildCommand(){
        StringBuilder command = new StringBuilder();
        command.append("CREATE TABLE ");
        command.append(tableName);
        command.append(" ( ").append(keyID).append(" INTEGER PRIMARY KEY AUTOINCREMENT ")
                .append(" , descryption TEXT , kindofExc TEXT );");
        return command.toString();
    }
    public class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(BuildCommand());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}

