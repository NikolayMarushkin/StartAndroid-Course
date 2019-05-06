package com.course.p0441simplelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "itemClick: position = " + position + ", id = "
                        + id, Toast.LENGTH_SHORT).show();
            }
        });

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "itemSelect: position = " + position + ", id = "
                                + id, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),
                        "itemSelect: nothing", Toast.LENGTH_SHORT).show();
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Toast.makeText(getApplicationContext(),
                        "scrollState = " + scrollState,
                        Toast.LENGTH_SHORT).show();
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Toast.makeText(getApplicationContext(),
                        "scroll: firstVisibleItem = " + firstVisibleItem
                                + ", visibleItemCount" + visibleItemCount
                                + ", totalItemCount" + totalItemCount,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
