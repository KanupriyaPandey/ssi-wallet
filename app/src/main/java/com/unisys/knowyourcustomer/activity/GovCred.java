package com.unisys.knowyourcustomer.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.unisys.knowyourcustomer.R;
import com.unisys.knowyourcustomer.database.DatabaseHelper;

public class GovCred extends AppCompatActivity {
    String Firstname, Middlename, Lastname, Email, Address, City, Country, GovDate, GovClaim, GovDID;
    Integer Id, Pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_cred);

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TBNAME;
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getInt(0);
                Firstname = cursor.getString(1);
                Middlename = cursor.getString(2);
                Lastname = cursor.getString(3);
                Email = cursor.getString(4);
                Address = cursor.getString(5);
                City = cursor.getString(6);
                Country = cursor.getString(7);
                Pincode = cursor.getInt(8);
                GovDate = cursor.getString(9);
                GovClaim = cursor.getString(10);
                GovDID = cursor.getString(11);
            } while (cursor.moveToNext());
        }
        database.close();

        TextView textView = findViewById(R.id.textGov);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText("{\n  'success' : true \n  'proof'{\n  " +
                "'firstname'[\n      claim : " + GovClaim + "\n      firstname : " + Firstname + "\n      ]\n   " +
                "'middlename'[\n      claim : "+GovClaim+"\n      middlename : "+Middlename+"\n      ]\n   " +
                "'lastname'[\n      claim : "+GovClaim+"\n      lastname : "+Lastname+"\n      ]\n   " +
                "'email'[\n      claim : "+GovClaim+"\n      email : "+Email+"\n      ]\n   " +
                "'address'[\n      claim : "+GovClaim+"\n      address : "+Address+"\n      ]\n   " +
                "'city'[\n      claim : "+GovClaim+"\n      city : "+City+"\n      ]\n   " +
                "'country'[\n      claim : "+GovClaim+"\n      country : "+Country+"\n      ]\n   " +
                "'pincode'[\n      claim : "+GovClaim+"\n      pincode : "+Pincode+"\n      ]\n   " +
                "'legal_identity'[\n      claim : "+GovClaim+"\n      legal_identity : "+GovDID+"\n      ]\n    " +
                "'issue_date[\n      claim : "+GovClaim+"\n      issue_date : "+GovDate+"\n      ]\n  }\n}");
    }
}
