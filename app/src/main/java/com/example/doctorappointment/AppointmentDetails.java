package com.example.doctorappointment;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class AppointmentDetails extends Fragment {

    ImageButton doag,doa;
    EditText txtdoag,txtdoa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.appointmentdetails,container,false);
doag=view.findViewById(R.id.doag);
txtdoa=view.findViewById(R.id.txtdoa);
txtdoag=view.findViewById(R.id.txtdoag);
doa=view.findViewById(R.id.doa);

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
                        String date = year+"/"+monthOfYear+"/"+dayOfMonth;

                        txtdoag.setText(date);
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
                        String date = year+"/"+monthOfYear+"/"+dayOfMonth;

                        txtdoa.setText(date);
                    }
                }, yyy, mmm, ddd);
                datePicker1.show();
            }
        });


        return view;
    }
    }
