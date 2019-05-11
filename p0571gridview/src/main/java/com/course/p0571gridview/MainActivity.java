package com.course.p0571gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this, R.layout.item, R.id.tvText, data);
        gvMain = findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView() {
        gvMain.setNumColumns(3);
        gvMain.setColumnWidth(80);
        gvMain.setVerticalSpacing(15);
        gvMain.setHorizontalSpacing(15);
        gvMain.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
    }
}
