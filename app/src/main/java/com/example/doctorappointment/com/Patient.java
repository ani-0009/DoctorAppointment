package com.example.doctorappointment.com;

public class Patient {



    @Override
    public String toString() {
        return "Appointment ID:  "+appointmentID +"  Appointment NO.:  "+appointmentNo+"  DATE Of Appointment:  "+DateOfAppointmentGiven+"  Patient Name:  "+PatientName+"  Patient Problem:  "+PatientProblem;
    }

    public Patient() {
    }

    public Patient(String appointmentID, String appointmentNo, String dateOfAppointmentGiven, String patientName, String patientProblem) {
        this.appointmentID = appointmentID;
        this.appointmentNo = appointmentNo;
        this.DateOfAppointmentGiven = dateOfAppointmentGiven;
        this.PatientName = patientName;
        this.PatientProblem = patientProblem;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getDateOfAppointmentGiven() {
        return DateOfAppointmentGiven;
    }

    public void setDateOfAppointmentGiven(String dateOfAppointmentGiven) {
        DateOfAppointmentGiven = dateOfAppointmentGiven;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientProblem() {
        return PatientProblem;
    }

    public void setPatientProblem(String patientProblem) {
        PatientProblem = patientProblem;
    }

    String appointmentID, appointmentNo, DateOfAppointmentGiven, PatientName ,PatientProblem;


 }
