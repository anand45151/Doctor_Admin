package com.example.doctora.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.doctora.R;
public class DashBoardFragment extends Fragment {

    TextView doctorname;

    public DashBoardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        doctorname = view.findViewById(R.id.doctorname);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DoctorPrefs", Context.MODE_PRIVATE);
        String doctorName = sharedPreferences.getString("DoctorName", "Doctor Name not found");
        doctorname.setText(doctorName);

        return view;
    }
}
