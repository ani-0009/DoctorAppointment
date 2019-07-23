package com.example.doctorappointment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
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


                       SimpleDateFormat dateformat = new SimpleDateFormat("EEEE");

                        Date d= new Date(year,monthOfYear,dayOfMonth-1);
                         day = dateformat.format(d);
                        txtdoag.setText(dateStr +"\t"+day);
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
        final String appPhone = txtphone.getText().toString();
        final String appName = txtname.getText().toString();
        String appProblem = txtproblem.getText().toString();
        String appdoag = txtdoag.getText().toString();
        String appdoa = txtdoa.getText().toString();





        Cursor cursor = sqLiteDatabase.rawQuery("select DoctorProfession,DoctorTimings,DoctorName,DoctorDays from DoctorDetails where DoctorID=" + appdoctorid, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {


                    final String weekday = cursor.getString(cursor.getColumnIndex("DoctorDays"));
                    final String doctorname = cursor.getString(cursor.getColumnIndex("DoctorName"));
                    final String doctortime = cursor.getString(cursor.getColumnIndex("DoctorTimings"));
                    //final String prof = cursor.getString(cursor.getColumnIndex("DoctorProfession"));


                   // Toast.makeText(getActivity(), weekday +" / " +day, Toast.LENGTH_LONG).show();

                    if (weekday.contains(day)) {

                       // Toast.makeText(getActivity(), "Doctor Available", Toast.LENGTH_SHORT).show();

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


                             final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                             builder.setTitle("Doctor Available>>");
                             builder.setMessage("Would you like to Send Appointment Confirmation Message to "+appName);
                             builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     int permissionCheck= ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS);
                                     if(permissionCheck== PackageManager.PERMISSION_GRANTED) {
                                         if (!appPhone.equals("")) {
                                             SmsManager smsManager = SmsManager.getDefault();
                                             smsManager.sendTextMessage(appPhone, null, "Dear " + appName + " Your Appointment is Done for "+day+". Your Doctor Name is "+doctorname+". Your Timings will be "+doctortime+". Thanks for using our services.", null, null);
                                             Toast.makeText(getActivity(), " Message Sent ", Toast.LENGTH_SHORT).show();
                                         }
                                     }

                                 }
                             });
                             builder.setNegativeButton("no",null);
                             builder.setNeutralButton("cancel",null);

                            AlertDialog dialog=builder.create();
                             dialog.show();

                         }




                    else {
                        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("Doctor Unavailable>>");
                        builder.setMessage("Would to like to inform the patient "+appName+" about Appointment Failure");
                        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int permissionCheck= ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
                                if(permissionCheck== PackageManager.PERMISSION_GRANTED) {
                                    if (!appPhone.equals("")) {
                                        Intent intent=new Intent(Intent.ACTION_CALL);
                                        Uri number=Uri.parse("tel:"+appPhone);
                                        intent.setData(number);
                                        startActivity(intent);
                                    }
                                }

                            }
                        });
                        builder.setNegativeButton("no",null);
                        builder.setNeutralButton("cancel",null);

                        AlertDialog dialog=builder.create();
                        dialog.show();


                      /*  AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("Doctor Not Available>>");
                        builder.setMessage("Would to like to inform the patient "+appName+" about Appointment Failure");
                        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(getActivity(), "hey "+weekday, Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Intent.ACTION_CALL);
                                Uri number=Uri.parse("tel:"+appPhone);
                                intent.setData(number);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("no",null);
                        builder.setNeutralButton("cancel",null);*/


                    }


                } while(cursor.moveToNext());

            }
        }



    }


});
















        return view;
    }
    }
