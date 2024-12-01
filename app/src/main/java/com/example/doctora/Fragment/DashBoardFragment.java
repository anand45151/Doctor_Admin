package com.example.doctora.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctora.Adapter.HospitalAdapter;
import com.example.doctora.ApiConstant;
import com.example.doctora.Model.Hospital;
import com.example.doctora.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    private TextView doctorname;
    private String doctorPhotoFilename;
    private String doctorPhotoUrl;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        // Initialize views
        doctorname = view.findViewById(R.id.doctorname);
        ImageView doctorPhoto = view.findViewById(R.id.doctor_Image);  // ImageView for doctor's photo
        RecyclerView recyclerView = view.findViewById(R.id.nearestHospitals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("AIIMS Delhi", "Ansari Nagar, New Delhi", "+91 11 26588500", R.drawable.hospital1, "A premier medical institute offering world-class healthcare services and medical education."));
        hospitals.add(new Hospital("Fortis Hospital", "Sector 62, Noida, Uttar Pradesh", "+91 120 2400222", R.drawable.hospital3, "Renowned for its cutting-edge treatments and specialized healthcare services."));
        hospitals.add(new Hospital("Apollo Hospital", "Greams Road, Chennai, Tamil Nadu", "+91 44 28293333", R.drawable.hospitals4, "A multi-specialty hospital known for its advanced facilities and comprehensive care."));
        hospitals.add(new Hospital("Tata Memorial Hospital", "Dr. E Borges Road, Mumbai, Maharashtra", "+91 22 24177000", R.drawable.hospitals7, "Specializing in cancer treatment and research with a focus on affordability and accessibility."));
        hospitals.add(new Hospital("Manipal Hospital", "Old Airport Road, Bangalore, Karnataka", "+91 80 25024444", R.drawable.hospital6, "One of India’s leading healthcare providers, offering multi-specialty care with state-of-the-art technology."));
        hospitals.add(new Hospital("Narayana Health", "Bommasandra Industrial Area, Bangalore, Karnataka", "+91 80 27835000", R.drawable.hospitals5, "A network of hospitals providing affordable and quality healthcare to all."));

        // Set up the RecyclerView adapter
        HospitalAdapter adapter = new HospitalAdapter(hospitals, getContext());
        recyclerView.setAdapter(adapter);

        // Retrieve doctor information from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE);
        String doctorName = sharedPreferences.getString("DoctorName", "Doctor Name not found");
        doctorname.setText(doctorName);

        // Load the doctor's image from SharedPreferences
        doctorPhotoFilename = sharedPreferences.getString("DoctorPhoto", "default_image.png");
        doctorPhotoUrl = ApiConstant.DASHBOARD_DOCTOR_PHOTO + doctorPhotoFilename;

        // Use Picasso to load the doctor's image into the ImageView
        Picasso.get()
                .load(doctorPhotoUrl)  // URL for the doctor's photo
                .placeholder(R.drawable.doctor)  // Placeholder image while loading
                .error(R.drawable.circle_shape)  // Error image if loading fails
                .into(doctorPhoto);  // Target the ImageView

        return view;
    }
}
