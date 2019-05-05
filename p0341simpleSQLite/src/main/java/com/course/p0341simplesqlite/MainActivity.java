package com.course.p0341simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnRead, btnClear;
    EditText etName, etEmail;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId()) {
            case R.id.btnAdd:
                Toast.makeText(
                        getApplicationContext(),
                        "--- Insert in mytable: ---",
                        Toast.LENGTH_SHORT).show();
                // подготовим данные для вставки в виде пар: наименование столбца - значение

                cv.put("name", name);
                cv.put("email", email);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, cv);

                Toast.makeText(
                        getApplicationContext(),
                        "row inserted, ID = " + rowID,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRead:
                Toast.makeText(
                        getApplicationContext(),
                        "--- Rows in mytable: ---",
                        Toast.LENGTH_SHORT).show();
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int idColIndex =    c.getColumnIndex("id");
                    int nameColIndex =  c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");

                    do {
                        // получаем значения по номерам столбцов
                        // и пишем все в лог
                        Toast.makeText(
                                getApplicationContext(),
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", email = " + c.getString(emailColIndex),
                                Toast.LENGTH_SHORT).show();
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя),
                        // то false - выходим из цикла
                    } while (c.moveToNext());
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                               "0 rows",
                            Toast.LENGTH_SHORT).show();
                }
                c.close();
                break;
            case R.id.btnClear:
                Toast.makeText(
                        getApplicationContext(),
                        "--- Clear mytable: ---",
                        Toast.LENGTH_SHORT).show();
                // удаляем все записи
                int clearCount = db.delete("mytable", null, null);
                Toast.makeText(
                        getApplicationContext(),
                        "deleted rows count = " + clearCount,
                        Toast.LENGTH_SHORT).show();
                break;
        }
        // закрываем подключение к БД
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(
                    getApplicationContext(),
                    "--- onCreate database ---",
                    Toast.LENGTH_SHORT).show();
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
