package com.example.doctora.Model;

public class Appointment {
    private String patient_first_name;
    private String patient_last_name;
    private String patient_dob;
    private String disease_description;
    private String Appointment_date;
    public String getPatientFirstName() {
        return patient_first_name;
    }

    public String getPatientLastName() {
        return patient_last_name;
    }

    public String getPatientDob() {
        return patient_dob;
    }

    public String getDiseaseDescription() {
        return disease_description;
    }

    public String getAppointmentDate() {
        return Appointment_date;
    }
}
