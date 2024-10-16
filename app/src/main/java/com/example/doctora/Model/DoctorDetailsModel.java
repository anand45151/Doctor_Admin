package com.example.doctora.Model;
import com.google.gson.annotations.SerializedName;

public class DoctorDetailsModel {

    @SerializedName("Doctor_Name")
    private String doctorName;

    @SerializedName("Doctor_Specialty")
    private String doctorSpecialty;

    @SerializedName("Doctor_Experiences")
    private String doctorExperiences;

    @SerializedName("Doctor_Location")
    private String doctorLocation;

    @SerializedName("Doctor_Photo")
    private String doctorPhoto;

    // Getters and setters for each field
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }

    public String getDoctorExperiences() {
        return doctorExperiences;
    }

    public void setDoctorExperiences(String doctorExperiences) {
        this.doctorExperiences = doctorExperiences;
    }

    public String getDoctorLocation() {
        return doctorLocation;
    }

    public void setDoctorLocation(String doctorLocation) {
        this.doctorLocation = doctorLocation;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }
}
