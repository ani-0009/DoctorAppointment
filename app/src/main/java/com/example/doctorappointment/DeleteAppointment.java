package com.example.doctorappointment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctorappointment.com.dbtask.DoctorManager;

public class DeleteAppointment extends Fragment
{

    SQLiteDatabase sqLiteDatabase;
    DoctorManager manager;

    Button btn_del;
    EditText id;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new DoctorManager(getContext());
        sqLiteDatabase=manager.openDB();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.deletappointment,container,false);

        btn_del=view.findViewById(R.id.btn_del);
        id=view.findViewById(R.id.id);





        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delid=id.getText().toString();
                int k=0;

               //Cursor cur= sqLiteDatabase.rawQuery("select count(*) from PatientDetails where PatientAppointmentID"+id,null);


                Cursor cursor= sqLiteDatabase.rawQuery("select * from PatientDetails where PatientAppointmentID=\"" + delid+"\"",null);

                 if(cursor.getCount()>0) {
                     sqLiteDatabase.rawQuery("delete from PatientDetails where PatientAppointmentID=\"" + delid+"\"",null).moveToFirst();
                     Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {

                     Toast.makeText(getActivity(), "Data Not Present in Database", Toast.LENGTH_SHORT).show();
                 }




            }
        });


        return view;
    }
}
