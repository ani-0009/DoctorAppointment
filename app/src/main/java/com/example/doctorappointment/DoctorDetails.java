package com.example.doctorappointment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorappointment.com.dbtask.DoctorConstant;
import com.example.doctorappointment.com.dbtask.DoctorManager;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class DoctorDetails extends Fragment

{

EditText txtid,txtname,txtemail,txtphone,txtadd;

CheckBox chkmon,chktue,chkwed,chkthu,chkfri,chksat,chksun;

SearchableSpinner spinprofession,spintimings;

Button btn_register;
    Spinner spinner;
    String[] options;
    Spinner spinn;
    String[] choice;
    DoctorManager manager;
    SQLiteDatabase sqLiteDatabase;
    String checkBoxdata="";

    String timings;
    String profession;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new DoctorManager(getContext());
        sqLiteDatabase=manager.openDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctordetails, container, false);
        txtid=view.findViewById(R.id.txtid);
        txtname=view.findViewById(R.id.txtname);
        txtemail=view.findViewById(R.id.txtemail);
        txtphone=view.findViewById(R.id.txtphone);
        txtadd= view.findViewById(R.id.txtadd);
        chkmon=view.findViewById(R.id.chkmon);
        chktue=view.findViewById(R.id.chktue);
        chkwed=view.findViewById(R.id.chkwed);
        chkthu=view.findViewById(R.id.chkthu);
        chkfri=view.findViewById(R.id.chkfri);
        chksat=view.findViewById(R.id.chksat);
        chksun=view.findViewById(R.id.chksun);
        spintimings=view.findViewById(R.id.spintimings);
        spinprofession=view.findViewById(R.id.spinprofession);
        btn_register=view.findViewById(R.id.btn_register);





        spinner=view.findViewById(R.id.spintimings);
        spinn=view.findViewById(R.id.spinprofession);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.Timings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        options = DoctorDetails.this.getResources().getStringArray(R.array.Timings);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.Profession, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(adapter1);

        choice = DoctorDetails.this.getResources().getStringArray(R.array.Profession);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 timings=adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        profession=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=txtid.getText().toString();
                String name=txtname.getText().toString();
                String email=txtemail.getText().toString();
                String phone= txtphone.getText().toString();
                String address=txtadd.getText().toString();

                if(id.equals("")){


                    txtid.setError("Email should not be empty");
                }
                else if(name.equals(""))
                {
                    txtname.setError("NName should not be empty");
                }
                else if(email.equals(""))
                {
                    txtemail.setError("Email should not be empty");
                }
                else if(phone.equals(""))
                {
                    txtphone.setError("Phone should not be empty");
                }
                else if(address.equals(""))
                {
                    txtadd.setError("Address should not be empty");
                }


                else {

                    if (chkmon.isChecked()) {
                        checkBoxdata = checkBoxdata + "Monday ";
                    }
                    if (chktue.isChecked()) {
                        checkBoxdata = checkBoxdata + "Tuesday ";
                    }
                    if (chkwed.isChecked()) {
                        checkBoxdata = checkBoxdata + "Wednesday ";
                    }
                    if (chkthu.isChecked()) {
                        checkBoxdata = checkBoxdata + "Thursday ";
                    }
                    if (chkfri.isChecked()) {
                        checkBoxdata = checkBoxdata + "Friday ";
                    }
                    if (chksat.isChecked()) {
                        checkBoxdata = checkBoxdata + "Saturday ";
                    }
                    if (chksun.isChecked()) {
                        checkBoxdata = checkBoxdata + "Sunday ";
                    }


                    // Toast.makeText(getActivity(), "data is "+checkBoxdata, Toast.LENGTH_SHORT).show();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DoctorConstant.coldid, id);
                    contentValues.put(DoctorConstant.coldname, name);
                    contentValues.put(DoctorConstant.coldemail, email);
                    contentValues.put(DoctorConstant.coldphone, phone);
                    contentValues.put(DoctorConstant.coldaddress, address);
                    contentValues.put(DoctorConstant.colddays, checkBoxdata);
                    contentValues.put(DoctorConstant.coldtimings, timings);
                    contentValues.put(DoctorConstant.coldprofession, profession);

                    long row = sqLiteDatabase.insert(DoctorConstant.tableone, null, contentValues);
                    if (row > 0) {
                        Toast.makeText(getActivity(), "Data Added ", Toast.LENGTH_SHORT).show();


                    }
                }

            }
        });













        return view;

    }






    }



