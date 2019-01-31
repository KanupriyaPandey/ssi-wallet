package com.unisys.knowyourcustomer.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unisys.knowyourcustomer.R;
import com.unisys.knowyourcustomer.activity.Bank2Cred;
import com.unisys.knowyourcustomer.activity.BankCred;
import com.unisys.knowyourcustomer.activity.CollegeCred;
import com.unisys.knowyourcustomer.activity.GovCred;
import com.unisys.knowyourcustomer.activity.JobApplication;
import com.unisys.knowyourcustomer.activity.JobCred;
import com.unisys.knowyourcustomer.database.DatabaseHelper;
import com.unisys.knowyourcustomer.listener.OnFragmentInteraction;

public class Wallet extends Fragment {
    private OnFragmentInteraction onFragmentInteraction;
    String dateGov, dateCollege, dateJob, dateCompany, dateBank1, dateBank2;
    String FirstName, MiddleName, LastName, Adress, City, Country;
    Integer Pincode, collegeBoolean, jobBoolean, companyBoolean, bank1Boolean, bank2Boolean;
    TextView name, address, date, date1, date2, date3, date4, date5;
    TextView cred, cred1, cred2, cred3, cred4, cred5;
    CardView card1, card2, card3, card4, card5;
    SQLiteDatabase databaseRead;
    DatabaseHelper helper;
    String selectQuery;

    public Wallet() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        if (onFragmentInteraction != null) {
            onFragmentInteraction.setActionBarTitle("Wallet");

            selectQuery = "SELECT * FROM " + DatabaseHelper.TBNAME;
            helper = new DatabaseHelper(getActivity());
            databaseRead = helper.getReadableDatabase();
            Cursor cursor = databaseRead.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    FirstName= cursor.getString(1);
                    MiddleName= cursor.getString(2);
                    LastName= cursor.getString(3);
                    Adress = cursor.getString(5);
                    City = cursor.getString(6);
                    Country = cursor.getString(7);
                    Pincode=cursor.getInt(8);
                    dateGov=cursor.getString(9);
                    dateCollege = cursor.getString(12);
                    collegeBoolean = cursor.getInt(14);
                    dateJob = cursor.getString(20);
                    jobBoolean = cursor.getInt(21);
                    dateCompany = cursor.getString(22);
                    companyBoolean = cursor.getInt(23);
                    dateBank1 = cursor.getString(26);
                    bank1Boolean = cursor.getInt(27);
                    dateBank2 = cursor.getString(29);
                    bank2Boolean = cursor.getInt(31);
                } while (cursor.moveToNext());
            }
            databaseRead.close();

            cred = view.findViewById(R.id.text);
            cred1 = view.findViewById(R.id.text1);
            cred2 = view.findViewById(R.id.text2);
            cred3 = view.findViewById(R.id.text3);
            cred4 = view.findViewById(R.id.text4);
            cred5 = view.findViewById(R.id.text5);

            date = view.findViewById(R.id.textView04);
            date1 = view.findViewById(R.id.textView14);
            date2 = view.findViewById(R.id.textView24);
            date3 = view.findViewById(R.id.textView34);
            date4 = view.findViewById(R.id.textView44);
            date5 = view.findViewById(R.id.textView54);

            card1 = view.findViewById(R.id.card1);
            card2 = view.findViewById(R.id.card2);
            card3 = view.findViewById(R.id.card3);
            card4 = view.findViewById(R.id.card4);
            card5 = view.findViewById(R.id.card5);

            name = view.findViewById(R.id.name);
            name.setText(FirstName+" "+MiddleName+" "+LastName);
            address = view.findViewById(R.id.address);
            address.setText(Adress+"\n"+Pincode+"\n"+City+"\n"+Country);
            date.setText(dateGov);

            if(collegeBoolean==1) {
                card1.setVisibility(View.VISIBLE);
                date1.setText(dateCollege);
            }

            if(jobBoolean==1) {
                card2.setVisibility(View.VISIBLE);
                date2.setText(dateJob);
            }

            if(companyBoolean==1) {
                card3.setVisibility(View.VISIBLE);
                date3.setText(dateCompany);
            }

            if(bank1Boolean==1) {
                card4.setVisibility(View.VISIBLE);
                date4.setText(dateBank1);
            }

            if(bank2Boolean==1) {
                card5.setVisibility(View.VISIBLE);
                date5.setText(dateBank2);
            }

            cred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), GovCred.class);
                    startActivity(intent);
                }
            });

            cred1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), CollegeCred.class);
                    startActivity(intent);
                }
            });

            cred2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), JobApplication.class);
                    startActivity(intent);
                }
            });

            cred3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), JobCred.class);
                    startActivity(intent);
                }
            });

            cred4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), BankCred.class);
                    startActivity(intent);
                }
            });


            cred5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), Bank2Cred.class);
                    startActivity(intent);
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

