package com.example.doctorappointment.com.dbtask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DoctorManager

{

    DoctorHelper helper;
    Context context;



    public DoctorManager(Context context)
    {
        this.context=context;
        helper=new DoctorHelper(context,DoctorConstant.database,null,DoctorConstant.databaseversion);

    }

    public SQLiteDatabase openDB()
    {
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        return sqLiteDatabase;
    }

    public void closeDB()
    {
        if(helper!=null)
        {
            helper.close();
        }
    }

}
