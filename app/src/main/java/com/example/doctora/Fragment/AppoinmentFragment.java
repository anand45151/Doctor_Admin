package com.example.doctora.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctora.Adapter.AppointmentAdapter;
import com.example.doctora.Model.Appointment;
import com.example.doctora.R;
import com.example.doctora.Utils.ApiService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppoinmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppointmentAdapter appointmentAdapter;

    public AppoinmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appoinment, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        appointmentAdapter = new AppointmentAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(appointmentAdapter);

        fetchAppointments();

        return view;
    }

    private void fetchAppointments() {
        int doctorId = 10;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103/androidapi/Doctor_Appointment_fetch_Api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService apiInterface = retrofit.create(ApiService.class);
        Call<List<Appointment>> call = apiInterface.getAppointments(doctorId);

        call.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Appointment> appointmentList = response.body();
                    appointmentAdapter = new AppointmentAdapter(getContext(), appointmentList);
                    recyclerView.setAdapter(appointmentAdapter);
                } else {
                    Log.e("API Error", "Error fetching appointments: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {
                Log.e("API Error", "Error: " + t.getMessage());
            }
        });
    }
}
