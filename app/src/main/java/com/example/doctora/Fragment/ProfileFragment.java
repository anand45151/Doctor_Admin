package com.example.doctora.Fragment;

import android.animation.ObjectAnimator;
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
import com.example.doctora.ApiConstant;
import com.example.doctora.R;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private ImageView doctorPhoto;
    private TextView doctorName;
    private TextView doctorSpecialty;
    private TextView doctorExperiences;
    private TextView doctorLocation, doctorID;
    private ProgressBar progressBar;
    private Button logoutbtn;

    public ProfileFragment() {
        // Required empty public constructor
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
        doctorID = view.findViewById(R.id.doctorid);
        loadDoctorDetails();

        return view;
    }

    private void loadDoctorDetails() {
        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DoctorPrefs", getActivity().MODE_PRIVATE);
        String doctorIDValue = sharedPreferences.getString("DoctorID", "default");
        String doctorNameValue = sharedPreferences.getString("DoctorName", "Default Name");
        String doctorSpecialtyValue = sharedPreferences.getString("DoctorSpecialty", "Default Specialty");
        String doctorExperiencesValue = sharedPreferences.getString("DoctorExperience", "Default Experience");
        String doctorLocationValue = sharedPreferences.getString("DoctorLocation", "Default Location");
        String doctorPhotoFilename = sharedPreferences.getString("DoctorPhoto", "default_image.png");
        String doctorPhotoUrl = ApiConstant.PHOTO_URL + doctorPhotoFilename;

        // Set text for doctor details
        doctorID.setText(doctorIDValue);
        doctorName.setText(doctorNameValue);
        doctorSpecialty.setText(doctorSpecialtyValue);
        doctorExperiences.setText(doctorExperiencesValue);
        doctorLocation.setText(doctorLocationValue);

        // Load doctor photo with Picasso
        Picasso.get()
                .load(doctorPhotoUrl)
                .placeholder(R.drawable.doctor)
                .error(R.drawable.patient)
                .into(doctorPhoto);

        // Fade in the doctor details
        ObjectAnimator fadeInName = ObjectAnimator.ofFloat(doctorName, "alpha", 0f, 1f);
        fadeInName.setDuration(500); // 500ms for fade in
        fadeInName.start();

        ObjectAnimator fadeInSpecialty = ObjectAnimator.ofFloat(doctorSpecialty, "alpha", 0f, 1f);
        fadeInSpecialty.setDuration(500);
        fadeInSpecialty.start();

        ObjectAnimator fadeInExperiences = ObjectAnimator.ofFloat(doctorExperiences, "alpha", 0f, 1f);
        fadeInExperiences.setDuration(500);
        fadeInExperiences.start();

        ObjectAnimator fadeInLocation = ObjectAnimator.ofFloat(doctorLocation, "alpha", 0f, 1f);
        fadeInLocation.setDuration(500);
        fadeInLocation.start();

        // Set visibility for text views to visible after loading
        doctorName.setVisibility(View.VISIBLE);
        doctorSpecialty.setVisibility(View.VISIBLE);
        doctorExperiences.setVisibility(View.VISIBLE);
        doctorLocation.setVisibility(View.VISIBLE);

        // Hide the progress bar
        progressBar.setVisibility(View.GONE);

        // Logout button functionality
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
