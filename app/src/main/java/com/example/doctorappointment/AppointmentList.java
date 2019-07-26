package com.example.doctorappointment;


import android.database.Cursor;
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
import android.widget.ListView;

import com.example.doctorappointment.com.Patient;
import com.example.doctorappointment.com.dbtask.DoctorConstant;
import com.example.doctorappointment.com.dbtask.DoctorManager;

import java.util.ArrayList;

public class AppointmentList extends Fragment

{

    ListView listView;
    ArrayList<Patient> arrayList;

    Patient patient;
    SQLiteDatabase sqLiteDatabase;
    DoctorManager manager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new DoctorManager(getContext());
        sqLiteDatabase=manager.openDB();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.appointmentlist,container,false);

        listView=view.findViewById(R.id.listview);


      Cursor cursor= sqLiteDatabase.query(DoctorConstant.tabletwo,null,null,null,null,null,null);

      ArrayList<Patient> arrayList=new ArrayList<Patient>();
      while(cursor!=null&&cursor.moveToNext()) {

          String id = cursor.getString(cursor.getColumnIndex(DoctorConstant.colpappid));
          String no = cursor.getString(cursor.getColumnIndex(DoctorConstant.colpappno));
          String date = cursor.getString(cursor.getColumnIndex(DoctorConstant.colpdoag));
          String name = cursor.getString(cursor.getColumnIndex(DoctorConstant.colpname));
          String problem = cursor.getString(cursor.getColumnIndex(DoctorConstant.colpproblem));



          patient = new Patient(id, no, date, name, problem);

          arrayList.add(patient);

      }

       ArrayAdapter<Patient> arrayAdapter= new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);

            listView.setAdapter(arrayAdapter);






        return view;

    }

}
