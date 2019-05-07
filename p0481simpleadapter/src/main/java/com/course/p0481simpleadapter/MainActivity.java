package com.course.p0481simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // имена аттрибутов для мап
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_TEXT1 = "text1";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // массивы данных
        String[] texts = {"sometext 1", "sometext 2", "sometext 3", "sometext 4", "sometext 5" };
        boolean[] checked = {true, false, true, false, false};
        int[] img = {R.drawable.ic_android_black_24dp,
                R.drawable.ic_announcement_black_24dp,
                R.drawable.ic_apps_black_24dp,
                R.drawable.ic_archive_black_24dp,
                R.drawable.ic_arrow_back_black_24dp};

        String[] textChecked = {"Один", "Два", "Три", "Четыре", "Пять" };

        // упаковка данных в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>> (texts.length);
        Map<String, Object> m;

        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_TEXT1, textChecked[i]);
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img[i]);
            data.add(m);
        }

        // массив имен аттрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TEXT1};
        // массив ID view компоненков, в которые будут вставляться данные
        int[] to = {R.id.tvText, R.id.cbChecked, R.id.ivImg, R.id.cbChecked};

        // создаем адаптер
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        // определяем список и присваиваем ему адаптер
        listView = findViewById(R.id.lvSimple);
        listView.setAdapter(simpleAdapter);
    }
}
