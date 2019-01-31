package com.unisys.knowyourcustomer.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.unisys.knowyourcustomer.R;
import com.unisys.knowyourcustomer.database.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.unisys.knowyourcustomer.database.DatabaseHelper.getDateTime;
import static com.unisys.knowyourcustomer.database.DatabaseHelper.randomAlphaNumericClaim;
import static com.unisys.knowyourcustomer.database.DatabaseHelper.randomAlphaNumericDID;

public class Register extends AppCompatActivity {
    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8;
    String firstName, middleName, lastName, email, address, pincode, city, country;
    String govDate, govDID, govClaim;
    boolean value = true;
    DatabaseHelper helper;
    SQLiteDatabase database;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        govDID = randomAlphaNumericDID();
        govClaim = randomAlphaNumericClaim();
        govDate = getDateTime();

        editText = findViewById(R.id.text);
        editText2 = findViewById(R.id.text2);
        editText3 = findViewById(R.id.text3);
        editText4 = findViewById(R.id.text4);
        editText5 = findViewById(R.id.text5);
        editText6 = findViewById(R.id.text6);
        editText7 = findViewById(R.id.text7);
        editText8 = findViewById(R.id.text8);


        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    firstName = editText.getText().toString();
                    if (!isValid(firstName))
                        editText.setError("Enter first name");
                }
            }
        });

        editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lastName = editText3.getText().toString();
                    if (!isValid(lastName))
                        editText3.setError("Enter last name");
                }
            }
        });

        editText4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    email = editText4.getText().toString();
                    if (!isValidEmail(email)) {
                        editText4.setError("Invalid Email");
                    }
                }
            }
        });

        editText5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    address = editText5.getText().toString();
                    if (!isValid(address))
                        editText5.setError("Enter address");
                }
            }
        });

        editText6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    pincode = editText6.getText().toString();
                    if (!isValidNo(pincode)) {
                        editText6.setError("Invalid PIN");
                    }
                }
            }
        });

        editText7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    city = editText7.getText().toString();
                    if (!isValid(city))
                        editText7.setError("Enter city");
                }
            }
        });


        editText8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    country = editText8.getText().toString();
                    if (!isValid(country))
                        editText8.setError("Enter country");
                }
            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                if (value) {
                    middleName = editText2.getText().toString();
                    if (!isValid(country))
                        middleName = "";
                    helper = new DatabaseHelper(getApplicationContext());
                    database = helper.getWritableDatabase();
                    values = new ContentValues();
                    try {
                        values.put("FirstName", firstName);
                        values.put("MiddleName", middleName);
                        values.put("LastName", lastName);
                        values.put("Email", email);
                        values.put("Address", address);
                        values.put("City", city);
                        values.put("Country", country);
                        values.put("Pincode", pincode);
                        values.put("GovDate", govDate);
                        values.put("GovClaim", govClaim);
                        values.put("GovDID", govDID);
                        values.put("CollegeBoolean", 0);
                        values.put("JobApplicationBoolean", 0);
                        values.put("JobCertificateBoolean", 0);
                        values.put("Bank1Boolean", 0);
                        values.put("Bank2Boolean", 0);
                        values.put("Register",1);
                        database.insert(DatabaseHelper.TBNAME, null, values);
                    } catch (Exception exp) {
                        System.out.println(exp.toString());
                    }
                    database.close();
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean isValid(String name) {
        if (name != null && name.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidNo(String mobile) {
        if (mobile != null && mobile.length() == 6) {
            return true;
        }
        return false;
    }

    private boolean isEmpty(EditText text) {
        CharSequence string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }

    private void checkDataEntered() {
        if (isEmpty(editText)) {
            editText.setError("Enter value");
            value = false;
        }else
            value=true;
        if (isEmpty(editText3)) {
            editText3.setError("Enter value");
            value = false;
        }else
            value=true;
        if (isEmpty(editText4)) {
            editText4.setError("Enter value");
            value = false;
        }else
            value=true;
        if (isEmpty(editText5)) {
            editText5.setError("Enter value");
            value = false;
        }else
            value=true;
        if (isEmpty(editText6)) {
            editText6.setError("Enter value");
            value = false;
        }else
            value=true;
        if (isEmpty(editText7)) {
            editText7.setError("Enter value");
            value = false;
        }else
            value=true;
        if (isEmpty(editText8)) {
            editText8.setError("Enter value");
            value = false;
        }else
            value=true;
    }

}