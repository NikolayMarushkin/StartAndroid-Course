package com.course.p0401layoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater ltInflater = getLayoutInflater();

        LinearLayout linLayout = findViewById(R.id.linLayout);
        View view1 = ltInflater.inflate(R.layout.activity_text_view, linLayout, true);
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();

        RelativeLayout relLayout = findViewById(R.id.relLayout);
        View view2 = ltInflater.inflate(R.layout.activity_text_view, relLayout, true);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();
    }
}
