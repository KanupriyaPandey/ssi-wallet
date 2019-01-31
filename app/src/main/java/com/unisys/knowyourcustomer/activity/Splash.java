package com.unisys.knowyourcustomer.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unisys.knowyourcustomer.R;
import com.unisys.knowyourcustomer.database.DatabaseHelper;

public class Splash extends AppCompatActivity {
    private static int splash_timeout = 2000;
    Integer register=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TBNAME;
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase databaseRead = helper.getReadableDatabase();
        Cursor cursor = databaseRead.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                register = cursor.getInt(32);
            } while (cursor.moveToNext());
        }
        databaseRead.close();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (register == 1) {
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), Intro.class);
                    startActivity(intent);
                }
            }
        }, splash_timeout);
    }
}