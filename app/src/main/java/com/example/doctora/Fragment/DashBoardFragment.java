package com.example.doctora.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doctora.Adapter.HospitalAdapter;
import com.example.doctora.Model.Hospital;
import com.example.doctora.R;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    TextView doctorname;

    public DashBoardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        doctorname = view.findViewById(R.id.doctorname);
        RecyclerView recyclerView = view.findViewById(R.id.nearestHospitals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("City Hospital", "123 Main St", "+123456789", R.drawable.hospital1, "A full-service hospital with state-of-the-art equipment and experienced staff."));
        hospitals.add(new Hospital("Green Valley Hospital", "456 Oak St", "+987654321", R.drawable.hospital3, "Providing specialized healthcare services with a focus on wellness and recovery."));
        hospitals.add(new Hospital("Sunshine Medical", "789 Pine St", "+123987654", R.drawable.hospitals4, "Known for its advanced diagnostic facilities and personalized care."));
        hospitals.add(new Hospital("Oceanview Hospital", "101 Beach Rd", "+321654987", R.drawable.hospitals5, "A coastal hospital specializing in emergency care and trauma services."));
        hospitals.add(new Hospital("Mountainview Clinic", "234 Hilltop Ln", "+654321987", R.drawable.hospital6, "Focused on outpatient services and routine medical procedures in a serene environment."));
        hospitals.add(new Hospital("Riverfront Health Center", "345 River Rd", "+456123789", R.drawable.hospitals7, "Offers a comprehensive range of medical services with a focus on family health."));
        HospitalAdapter adapter = new HospitalAdapter(hospitals,getContext());
        recyclerView.setAdapter(adapter);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE);
        String doctorName = sharedPreferences.getString("DoctorName", "Doctor Name not found");
        doctorname.setText(doctorName);

        return view;
    }

}
