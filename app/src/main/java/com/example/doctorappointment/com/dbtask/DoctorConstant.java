package com.example.doctorappointment.com.dbtask;

public class DoctorConstant

{

    public static final String database ="DoctorAppointment";
    public static final String tableone ="DoctorDetails";
    public static final String tabletwo ="PatientDetails";

    public static final String coldid ="DoctorID";
    public static final String coldname="DoctorName";
    public static final String coldemail="DoctorEmail";
    public static final String coldaddress="DoctorAddress";
    public static final String coldphone= "DoctorPhoneNo";
    public static final String colddays= "DoctorDays";
    public static final String coldtimings= "DoctorTimings";
    public static final String coldprofession= "DoctorProfession";



    public static final String colpappid= "PatientAppointmentID";
    public static final String colpdid= "DoctorID";
    public static final String colpdoag= "DateOfAppointmentGiven";
    public static final String colpdoa= "DateOfAppointment";
    public static final String colpappno= "PatientAppointmentNo";
    public static final String colpphone= "PatientPhone";
    public static final String colpname= "PatientName";
    public static final String colpproblem= "PatientProblem";


    public static final int databaseversion= 1;
    public static final String tableonequery= "create table DoctorDetails(DoctorID text,DoctorName text, DoctorEmail text, DoctorAddress text ,DoctorPhoneNo text , DoctorDays text, DoctorTimings text, DoctorProfession text )";
    public static final String tabletwoquery= "create table PatientDetails(PatientAppointmentID text primary key, DoctorID text , DateOfAppointmentGiven date, DateOfAppointment date, PatientAppointmentNo int, PatientPhone text, PatientName text, PatientProblem text)";





}



