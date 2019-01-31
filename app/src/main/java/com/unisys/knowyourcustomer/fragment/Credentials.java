package com.unisys.knowyourcustomer.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unisys.knowyourcustomer.R;
import com.unisys.knowyourcustomer.activity.Bank2Cred;
import com.unisys.knowyourcustomer.activity.BankCred;
import com.unisys.knowyourcustomer.activity.CollegeCred;
import com.unisys.knowyourcustomer.activity.GovCred;
import com.unisys.knowyourcustomer.activity.JobApplication;
import com.unisys.knowyourcustomer.activity.JobCred;
import com.unisys.knowyourcustomer.database.DatabaseHelper;
import com.unisys.knowyourcustomer.listener.OnFragmentInteraction;

import static com.unisys.knowyourcustomer.database.DatabaseHelper.TBNAME;
import static com.unisys.knowyourcustomer.database.DatabaseHelper.getDateTime;
import static com.unisys.knowyourcustomer.database.DatabaseHelper.randomAlphaNumericClaim;

public class Credentials extends Fragment {
    private OnFragmentInteraction onFragmentInteraction;
    String collegeDate, collegeClaim, jobDate, companyDate, companyClaim, bankClaim, bankDate, bank2Claim, bank2Date;
    boolean collegeCred, jobApplication, jobCred, bank1Cred, bank2Cred = false;
    TextView text, text2, text3, text4, text5, text6;
    TextView college1, college2, college3, college4;
    TextView company1, company2, company3, company01, company02, bank1;
    ImageView verify1, verify2, verify3, verify4, verify5;
    ContentValues values;
    SQLiteDatabase database, databaseRead;
    DatabaseHelper helper;
    String selectQuery, date;
    Integer collegeBoolean, jobBoolean,companyBoolean,bankBoolean,bank2Boolean;

    public Credentials() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credentials, container, false);
        if (onFragmentInteraction != null) {
            onFragmentInteraction.setActionBarTitle("Credentials");
            collegeClaim = randomAlphaNumericClaim();
            collegeDate = getDateTime();
            companyClaim = randomAlphaNumericClaim();
            jobDate = getDateTime();
            companyDate = getDateTime();
            bankClaim = randomAlphaNumericClaim();
            bankDate = getDateTime();
            bank2Claim = randomAlphaNumericClaim();
            bank2Date = getDateTime();

            selectQuery = "SELECT * FROM " + DatabaseHelper.TBNAME;
            helper = new DatabaseHelper(getActivity());
            database = helper.getWritableDatabase();
            values = new ContentValues();
            databaseRead = helper.getReadableDatabase();
            Cursor cursor = databaseRead.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    date = cursor.getString(9);
                    collegeBoolean=cursor.getInt(14);
                    jobBoolean=cursor.getInt(21);
                    companyBoolean=cursor.getInt(23);
                    bankBoolean=cursor.getInt(27);
                    bank2Boolean=cursor.getInt(31);
                } while (cursor.moveToNext());
            }
            databaseRead.close();
            TextView textView = view.findViewById(R.id.textView4);
            textView.setText(date);

            verify1 = view.findViewById(R.id.verify2);
            verify2 = view.findViewById(R.id.verify3);
            verify3 = view.findViewById(R.id.verify4);
            verify4 = view.findViewById(R.id.verify5);
            verify5 = view.findViewById(R.id.verify6);

            text = view.findViewById(R.id.text);
            text2 = view.findViewById(R.id.text2);
            text3 = view.findViewById(R.id.text3);
            text4 = view.findViewById(R.id.text4);
            text5 = view.findViewById(R.id.text5);
            text6 = view.findViewById(R.id.text6);

            college1 = view.findViewById(R.id.textView12);
            college2 = view.findViewById(R.id.textView22);
            college3 = view.findViewById(R.id.textView32);
            college4 = view.findViewById(R.id.textView42);

            company1 = view.findViewById(R.id.textView23);
            company2 = view.findViewById(R.id.textView33);
            company3 = view.findViewById(R.id.textView43);

            company01 = view.findViewById(R.id.textView34);
            company02 = view.findViewById(R.id.textView44);
            bank1 = view.findViewById(R.id.textView45);

            if(collegeBoolean == 1){
                collegeCred=true;
                verify1.setVisibility(View.VISIBLE);
                text2.setText("View Credential");
                college1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                college2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                college3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                college4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
            }

            if(jobBoolean == 1){
                jobApplication = true;
                verify2.setVisibility(View.VISIBLE);
                text3.setText("View Credential");
                company1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                company2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                company3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
            }

            if(companyBoolean == 1){
                jobCred = true;
                verify3.setVisibility(View.VISIBLE);
                text4.setText("View Credential");
                company01.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                company02.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
            }

            if(bankBoolean == 1){
                bank1Cred = true;
                verify4.setVisibility(View.VISIBLE);
                text5.setText("View Credential");
                bank1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
            }

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), GovCred.class);
                    startActivity(intent);
                }
            });

            view.findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (collegeCred) {
                        Intent intent = new Intent(getActivity(), CollegeCred.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Request Accepted", Toast.LENGTH_SHORT).show();
                        collegeCred = true;
                        verify1.setVisibility(View.VISIBLE);
                        text2.setText("View Credential");
                        database = helper.getWritableDatabase();
                        try {
                            values.put("CollegeBoolean", 1);
                            values.put("Degree", "MS");
                            values.put("Average", "78%");
                            values.put("BirthYear", "09-09-91");
                            values.put("CollegeStatus","Graduated");
                            values.put("CollegeDate",collegeDate);
                            values.put("CollegeClaim",collegeClaim);
                            values.put("BirthYear","09-09-1991");
                            database.update(TBNAME,values,null,null);
                        } catch (Exception exp) {
                            System.out.println(exp.toString());
                        }
                        database.close();
                        college1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                        college2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                        college3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                        college4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                    }
                }
            });


            text3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (jobApplication) {
                        Intent intent = new Intent(getActivity(), JobApplication.class);
                        startActivity(intent);
                    } else {
                        if (collegeCred) {
                            Toast.makeText(getActivity(), "Request Accepted", Toast.LENGTH_SHORT).show();
                            jobApplication = true;
                            verify2.setVisibility(View.VISIBLE);
                            text3.setText("View Credential");
                            database = helper.getWritableDatabase();
                            try {
                                values.put("JobApplicationBoolean", 1);
                                values.put("CompanyClaim", companyClaim);
                                values.put("JobApplicationDate", jobDate);
                                database.update(TBNAME,values,null,null);
                            } catch (Exception exp) {
                                System.out.println(exp.toString());
                            }
                            database.close();
                            company1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                            company2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                            company3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                        } else
                            Toast.makeText(getActivity(), "You do not have necessary credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            text4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (jobCred) {
                        Intent intent = new Intent(getActivity(), JobCred.class);
                        startActivity(intent);
                    } else {
                        if (jobApplication) {
                            Toast.makeText(getActivity(), "Request Accepted", Toast.LENGTH_SHORT).show();
                            jobCred = true;
                            verify3.setVisibility(View.VISIBLE);
                            text4.setText("View Credential");
                            database = helper.getWritableDatabase();
                            try {
                                values.put("JobCertificateBoolean", 1);
                                values.put("Experience", "4 years");
                                values.put("Salary", 50000);
                                values.put("JobCertificateDate", companyDate);
                                values.put("JobCertificateStatus","Employee");
                                database.update(TBNAME,values,null,null);
                            } catch (Exception exp) {
                                System.out.println(exp.toString());
                            }
                            database.close();
                            company01.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                            company02.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                        } else
                            Toast.makeText(getActivity(), "You do not have necessary credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            text5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bank1Cred) {
                        Intent intent = new Intent(getActivity(), BankCred.class);
                        startActivity(intent);
                    } else {
                        if (jobCred) {
                            Toast.makeText(getActivity(), "Request Accepted", Toast.LENGTH_SHORT).show();
                            bank1Cred = true;
                            verify4.setVisibility(View.VISIBLE);
                            text5.setText("View Credential");
                            database = helper.getWritableDatabase();
                            try {
                                values.put("Bank1Boolean", 1);
                                values.put("Bank1Date", bankDate);
                                values.put("Bank1Claim", bankClaim);
                                database.update(TBNAME,values,null,null);
                            } catch (Exception exp) {
                                System.out.println(exp.toString());
                            }
                            database.close();
                            bank1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
                        } else
                            Toast.makeText(getActivity(), "You do not have necessary credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            text6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bank2Cred) {
                        Intent intent = new Intent(getActivity(), Bank2Cred.class);
                        startActivity(intent);
                    } else {
                        if (bank1Cred || jobCred) {
                            Toast.makeText(getActivity(), "Request Accepted", Toast.LENGTH_SHORT).show();
                            bank2Cred = true;
                            verify5.setVisibility(View.VISIBLE);
                            text6.setText("View Credential");
                            database = helper.getWritableDatabase();
                            try {
                                values.put("Bank2Boolean", 1);
                                values.put("Bank2Date", bank2Date);
                                values.put("Bank2Claim", bank2Claim);
                                database.update(TBNAME,values,null,null);
                            } catch (Exception exp) {
                                System.out.println(exp.toString());
                            }
                            database.close();
                        } else
                            Toast.makeText(getActivity(), "You do not have necessary credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onFragmentInteraction = (OnFragmentInteraction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteraction = null;
    }

}

