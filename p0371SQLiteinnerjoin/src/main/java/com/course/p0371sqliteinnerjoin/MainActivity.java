package com.course.p0371sqliteinnerjoin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // данные для таблицы должностей
    int[] position_id = { 1, 2, 3, 4 };
    String[] position_name = { "Директор", "Программист", "Бухгалтер", "Охранник" };
    int[] position_salary = { 15000, 100000, 10000, 8000 };

    // данные для таблицы людей
    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключаемся к БД
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        // Описание курсора
        Cursor c;

        // выводим в лог данные по должностям
        Toast.makeText(getApplicationContext(), "--- Table position ---", Toast.LENGTH_SHORT).show();
        c = db.query("position", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Toast.makeText(getApplicationContext(), "--- ---", Toast.LENGTH_SHORT).show();

        // выводим в лог данные по людям
        Toast.makeText(getApplicationContext(), "--- Table people ---", Toast.LENGTH_SHORT).show();
        c = db.query("people", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Toast.makeText(getApplicationContext(), "--- ---", Toast.LENGTH_SHORT).show();

        // выводим результат объединения
        // используем rawQuery
        Toast.makeText(getApplicationContext(), "--- INNER JOIN with rawQuery---", Toast.LENGTH_SHORT).show();
        String sqlQuery =
                "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
        c = db.rawQuery(sqlQuery, new String[] {"12000"});
        logCursor(c);
        c.close();
        Toast.makeText(getApplicationContext(), "--- ---", Toast.LENGTH_SHORT).show();

        // выводим результат объединения
        // используем query
        Toast.makeText(getApplicationContext(), "--- INNER JOIN with query---", Toast.LENGTH_SHORT).show();

        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String columns[] = { "PL.name as Name", "PS.name as Position", "salary as Salary" };
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(c);
        c.close();
        Toast.makeText(getApplicationContext(), "--- ---", Toast.LENGTH_SHORT).show();

        // закрываем БД
        dbh.close();
    }

    // вывод в лог данных из курсора
    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
            }
        } else {
            Toast.makeText(getApplicationContext(), "Cursor is null", Toast.LENGTH_SHORT).show();
    }   }

    // класс для работы с БД
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(MainActivity context) {
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(getApplicationContext(), "--- onCreate database ---", Toast.LENGTH_SHORT).show();

            ContentValues cv = new ContentValues();

            // создаем таблицу должностей
            db.execSQL("create table position ("
                    + "id integer primary key,"
                    + "name text," + "salary integer"
                    + ");");

            // заполняем ее
            for (int i = 0; i < position_id.length; i++) {
                cv.clear();
                cv.put("id", position_id[i]);
                cv.put("name", position_name[i]);
                cv.put("salary", position_salary[i]);
                db.insert("position", null, cv);
            }

            // создаем таблицу людей
            db.execSQL("create table people ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "posid integer"
                    + ");");

            // заполняем ее
            for (int i = 0; i < people_name.length; i++) {
                cv.clear();
                cv.put("name", people_name[i]);
                cv.put("posid", people_posid[i]);
                db.insert("people", null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
