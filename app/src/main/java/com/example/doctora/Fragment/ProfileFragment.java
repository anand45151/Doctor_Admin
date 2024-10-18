package com.example.doctora.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doctora.Activity.Login;
import com.example.doctora.R;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private ImageView doctorPhoto;
    private TextView doctorName;
    private TextView doctorSpecialty;
    private TextView doctorExperiences;
    private TextView doctorLocation;
    ProgressBar progressBar;
    private Button logoutbtn;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        doctorPhoto = view.findViewById(R.id.Doctor_Photo);
        doctorName = view.findViewById(R.id.Doctor_Name);
        doctorSpecialty = view.findViewById(R.id.Doctor_Specialty);
        doctorExperiences = view.findViewById(R.id.Doctor_Experiences);
        doctorLocation = view.findViewById(R.id.Doctor_Location);
        logoutbtn = view.findViewById(R.id.logoutbtn);
        progressBar = view.findViewById(R.id.progressBar);
        loadDoctorDetails();

        return view;
    }

    private void loadDoctorDetails() {
        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DoctorPrefs", getActivity().MODE_PRIVATE);
        String doctorNameValue = sharedPreferences.getString("DoctorName", "Default Name");
        String doctorSpecialtyValue = sharedPreferences.getString("DoctorSpecialty", "Default Specialty");
        String doctorExperiencesValue = sharedPreferences.getString("DoctorExperiences", "Default Experience");
        String doctorLocationValue = sharedPreferences.getString("DoctorLocation", "Default Location");
        String doctorPhotoFilename = sharedPreferences.getString("DoctorPhoto", "default_image.png");
        String doctorPhotoUrl = "http://192.168.0.103/Doctor_G_Main_WebSite/uploads/" + doctorPhotoFilename;
        doctorName.setText(doctorNameValue);
        doctorSpecialty.setText(doctorSpecialtyValue);
        doctorExperiences.setText(doctorExperiencesValue);
        doctorLocation.setText(doctorLocationValue);

        Picasso.get()
                .load(doctorPhotoUrl)
                .placeholder(R.drawable.doctor)
                .error(R.drawable.patient)
                .into(doctorPhoto);
        progressBar.setVisibility(View.GONE);

    logoutbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
            getActivity().finish();
        }
    });
    }


}
