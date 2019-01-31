package com.unisys.knowyourcustomer.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.unisys.knowyourcustomer.R;
import com.unisys.knowyourcustomer.database.DatabaseHelper;

public class CollegeCred extends AppCompatActivity {
    String CollegeDate, CollegeClaim,  Degree, CollegeStatus, Average, BirthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_cred);

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TBNAME;
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CollegeDate = cursor.getString(12);
                CollegeClaim = cursor.getString(13);
                Degree = cursor.getString(15);
                CollegeStatus = cursor.getString(16);
                Average = cursor.getString(17);
                BirthYear = cursor.getString(18);
            } while (cursor.moveToNext());
        }
        database.close();

        TextView textView = findViewById(R.id.textCollege);
        textView.setText("{\n  'success' : true \n  'proof'{\n  " +
                "'degree'[\n      claim : " + CollegeClaim + "\n      degree : " + Degree + "\n      ]\n   " +
                "'status'[\n      claim : " + CollegeClaim + "\n      status : " + CollegeStatus + "\n      ]\n   " +
                "'agregate'[\n      claim : " + CollegeClaim + "\n      agregate : " + Average + "\n      ]\n   " +
                "'date of birth'[\n      claim : " + CollegeClaim + "\n      date of birth : " + BirthYear + "\n      ]\n   " +
                "'issue_date[\n      claim : "+CollegeClaim+"\n      issue_date : "+CollegeDate+"\n      ]\n  }\n}");
    }
}

