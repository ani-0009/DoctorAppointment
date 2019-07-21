package com.example.doctorappointment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doctorappointment.com.dbtask.DoctorConstant;
import com.example.doctorappointment.com.dbtask.DoctorManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class AppointmentDetails extends Fragment {

    ImageButton doag,doa;
    EditText txtdoag,txtdoa,txtdid,txtappno,txtname,txtproblem,txtphone,txtappid;
    String day;
Button btn_submit;


    DoctorManager manager;
    SQLiteDatabase sqLiteDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager= new DoctorManager(getContext());
        sqLiteDatabase=manager.openDB();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.appointmentdetails,container,false);
doag=view.findViewById(R.id.doag);
txtdoa=view.findViewById(R.id.txtdoa);
txtdoag=view.findViewById(R.id.txtdoag);
txtdid=view.findViewById(R.id.txtdid);
doa=view.findViewById(R.id.doa);


txtappno=view.findViewById(R.id.txtappno);
txtname=view.findViewById(R.id.txtname);
txtphone=view.findViewById(R.id.txtphone);
txtappid=view.findViewById(R.id.txtappid);
txtproblem=view.findViewById(R.id.txtproblem);
btn_submit=view.findViewById(R.id.btn_submit);

        doag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String dateStr = dayOfMonth+"/"+monthOfYear+"/"+year;
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        Date d= null;
                        try {
                            d = dateformat.parse(dateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                         day = (String) android.text.format.DateFormat.format("EEEE", d);
                        txtdoag.setText(dateStr);
                    }
                }, yy, mm, dd);
                datePicker.show();

            }
        });


        doa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yyy = calendar.get(Calendar.YEAR);
                int mmm = calendar.get(Calendar.MONTH);
                int ddd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date =year+"/"+monthOfYear+"/"+dayOfMonth;

                        txtdoa.setText(date);
                    }
                }, yyy, mmm, ddd);
                datePicker1.show();
            }
        });

   btn_submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String appdoctorid = txtdid.getText().toString();
        String appID = txtappid.getText().toString();
        String appNO = txtappno.getText().toString();
        String appPhone = txtphone.getText().toString();
        String appName = txtname.getText().toString();
        String appProblem = txtproblem.getText().toString();
        String appdoag = txtdoag.getText().toString();
        String appdoa = txtdoa.getText().toString();


        ContentValues contentValues = new ContentValues();
        contentValues.put(DoctorConstant.colpappid, appID);
        contentValues.put(DoctorConstant.colpdid, appdoctorid);
        contentValues.put(DoctorConstant.colpdoag, appdoag);
        contentValues.put(DoctorConstant.colpdoa, appdoa);
        contentValues.put(DoctorConstant.colpappno, appNO);
        contentValues.put(DoctorConstant.colpphone, appPhone);
        contentValues.put(DoctorConstant.colpname, appName);
        contentValues.put(DoctorConstant.colpproblem, appProblem);
        long row = sqLiteDatabase.insert(DoctorConstant.tabletwo, null, contentValues);
        if (row > 0) {
            Toast.makeText(getActivity(), "Data Added ", Toast.LENGTH_SHORT).show();


        }


        Cursor cursor = sqLiteDatabase.rawQuery("select DoctorDays from DoctorDetails where DoctorID=" + appdoctorid, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {


                    String weekday = cursor.getString(cursor.getColumnIndex("DoctorDays"));


                    if (weekday.contains(day)) {
                        Toast.makeText(getActivity(), "Doctor Available", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }


                } while (cursor.moveToNext());

            }
        }


        Cursor cursor1 = sqLiteDatabase.rawQuery("select DateOfAppointment from PatientDetails ", null);
    }


});
















        return view;
    }
    }
