package com.course.p0481simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);

        List<Map<String, Object>> data = GetSampleData();


        SimpleAdapter adapter = new SimpleAdapter(
                this, data, R.layout.item,
                new String[] { "userIcon", "username", "usertext" },  new int[] { R.id.userIcon, R.id.username, R.id.usertext });

        mListView.setAdapter(adapter);
    }

    List<Map<String, Object>> GetSampleData()
    {
        List<Map<String, Object>> list = new ArrayList<>();

        Map map = new HashMap();
        map.put("userIcon", R.drawable.ic_android_black_24dp);
        map.put("username", "Shen");
        map.put("usertext", "This is a simple sample for SimpleAdapter");
        list.add(map);
        map = new HashMap();
        map.put("userIcon", R.drawable.ic_apps_black_24dp);
        map.put("username", "Ricardo");
        map.put("usertext", "This is a simple sample for SimpleAdapter");
        list.add(map);

        return list;
    }
}
