package com.unisys.knowyourcustomer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static String CLAIM = "-abcdefghijklmnopqrstuvwxyz-0123456789";
    public static String DID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String DBNAME = "DataBase.db";
    public static final int DBVERSION = 1;
    public static final String TBNAME = "DataTable";
    public static final String TBQUERY = "Create Table "+TBNAME +"("
            +"Id integer primary key autoincrement,"
            +"FirstName text,"
            +"MiddleName text,"
            +"LastName text,"
            +"Email text,"
            +"Address text,"
            +"City text,"
            +"Country text,"
            +"Pincode integer,"
            +"GovDate text,"
            +"GovClaim text,"
            +"GovDID text,"
            +"CollegeDate text,"
            +"CollegeClaim text,"
            +"CollegeBoolean integer,"
            +"Degree text,"
            +"CollegeStatus text,"
            +"Average text,"
            +"BirthYear text,"
            +"CompanyClaim text,"
            +"JobApplicationDate text,"
            +"JobApplicationBoolean integer,"
            +"JobCertificateDate text,"
            +"JobCertificateBoolean integer,"
            +"Experience text,"
            +"Salary integer,"
            +"Bank1Date text,"
            +"Bank1Boolean integer,"
            +"Bank1Claim text,"
            +"Bank2Date text,"
            +"Bank2Claim text,"
            +"Bank2Boolean integer,"
            +"Register integer)";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBQUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static String randomAlphaNumericDID() {
        int count=16;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * DID.length());
            builder.append(DID.charAt(character));
        }
        return builder.toString();
    }

    public static String randomAlphaNumericClaim() {
        int count=25;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * CLAIM.length());
            builder.append(CLAIM.charAt(character));
        }
        return builder.toString();
    }
}

