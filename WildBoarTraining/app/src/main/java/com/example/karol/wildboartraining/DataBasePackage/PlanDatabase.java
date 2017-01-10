package com.example.karol.wildboartraining.DataBasePackage;


import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PlanDatabase extends SQLiteOpenHelper{
    private static final String dbName = "Grafik";
    private static final int dbVersion = 1;

    static final String name = "GRAFIK";
    static final String days = "Dzień";
    static final String exercises = "Ćwiczenia";
    static final String calories = "Kalorie";

    public PlanDatabase(Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " +name +"(" +days +"TEXT, " +exercises +"TEXT, " +calories +"INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
