package com.example.karol.wildboartraining.DataBasePackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.TextView;

import com.example.karol.wildboartraining.R;
//import com.example.karol.wildboartraining.R;

public class ShowPlanActivity extends AppCompatActivity {
    private PlanDatabase plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan);
        plan = new PlanDatabase(this);

        addRow("Poniedziałek", "nogi", 0);
        addRow("Wtorek", "", 0);
        addRow("Środa", "plecy", 0);
        addRow("Czwartek", "", 0);
        addRow("Piątek", "brzuch", 0);
        addRow("Sobota", "", 0);
        addRow("Niedziela", "", 0);
        Cursor cur = getData();
        showTable(cur);
    }

    private void addRow(String day, String ex, int cal) {
        SQLiteDatabase database = plan.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlanDatabase.days, day);
        values.put(PlanDatabase.exercises, ex);
        values.put(PlanDatabase.calories, cal);
        database.insertOrThrow(PlanDatabase.name, null, values);
    }

    private Cursor getData(){
        SQLiteDatabase database = plan.getWritableDatabase();
        String[] values = {PlanDatabase.days, PlanDatabase.exercises, PlanDatabase.calories};
        Cursor cursor = database.query(PlanDatabase.name, values, null, null, null, null, null);
        startManagingCursor(cursor);
        return cursor;
    }

    private void showTable(Cursor cur){
        String rows = "GRAFIK:\n";
        while(cur.moveToNext())
            rows += cur.getString(0) +": " +cur.getString(1) +"(" +cur.getInt(2) + ")\n";
        TextView table = (TextView)findViewById(R.id.schedule);
        table.setText(rows);
    }
}
