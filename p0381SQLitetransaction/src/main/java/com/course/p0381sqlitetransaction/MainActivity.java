package com.course.p0381sqlitetransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbh;
    SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "--- onCreate Activity ---", Toast.LENGTH_SHORT).show();
        dbh = new DBHelper(this);
        myActions();
    }

    void myActions() {
        db = dbh.getWritableDatabase();
        delete(db, "mytable");
        insert(db, "mytable", "val1");
        read(db, "mytable");
        dbh.close();
    }

    void insert(SQLiteDatabase db, String table, String value) {
        Toast.makeText(getApplicationContext(),
                "Insert in table " + table + " value = " + value,
                Toast.LENGTH_SHORT).show();
        ContentValues cv = new ContentValues();
        cv.put("val", value);
        db.insert(table, null, cv);
    }

    void read(SQLiteDatabase db, String table) {
        Toast.makeText(getApplicationContext(),
                "Read table " + table,
                Toast.LENGTH_SHORT).show();
        Cursor c = db.query(table, null, null, null, null, null, null);
        if (c != null) {
            Toast.makeText(getApplicationContext(),
                    "Records count = " + c.getCount(),
                    Toast.LENGTH_SHORT).show();
            if (c.moveToFirst()) {
                do {
                    Toast.makeText(getApplicationContext(),
                            c.getString(c.getColumnIndex("val")),
                            Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
            }
            c.close();
        }
    }

    void delete(SQLiteDatabase db, String table) {
        Toast.makeText(getApplicationContext(),
                "Delete all from table " + table,
                Toast.LENGTH_SHORT).show();
        db.delete(table, null, null);
    }

    // класс для работы с БД
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(MainActivity context) {
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(getApplicationContext(),
                    "--- onCreate database ---",
                    Toast.LENGTH_SHORT).show();

            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "val text"
                    + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
