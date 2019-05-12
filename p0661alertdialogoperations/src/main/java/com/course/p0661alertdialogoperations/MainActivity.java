package com.course.p0661alertdialogoperations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int DIALOG = 1;

    Dialog dialog;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("OK", null);
            dialog = adb.create();

            // обработчик отображения
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialog) {
                    Toast.makeText(getApplicationContext(), "Show", Toast.LENGTH_SHORT).show();
                }
            });

            // обработчик отмены
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                }
            });

            // обработчик закрытия
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    Toast.makeText(getApplicationContext(), "Dismiss", Toast.LENGTH_SHORT).show();
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }


    void method1() {
        removeDialog(DIALOG);
    }

    void method2() {
        showDialog(DIALOG);
    }

    public void onclick(View v) {
        showDialog(DIALOG);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            public void run() {
                method1();
            }
        }, 2000);

        h.postDelayed(new Runnable() {
            public void run() {
                method2();
            }
        }, 4000);
    }
}
