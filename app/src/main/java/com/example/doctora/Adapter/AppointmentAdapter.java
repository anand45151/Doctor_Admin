package com.example.doctora.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctora.Model.Appointment;
import com.example.doctora.R;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> appointments;

    public AppointmentAdapter(Context context, List<Appointment> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appinment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        holder.firstName.setText(appointment.getPatientFirstName());
        holder.lastName.setText(appointment.getPatientLastName());
        holder.dob.setText("DOB: " + appointment.getPatientDob());
        holder.disease.setText(appointment.getDiseaseDescription());
        holder.appointmentDate.setText("Appointment: " + appointment.getAppointmentDate());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, dob, disease, appointmentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.patient_first_name);
            lastName = itemView.findViewById(R.id.patient_last_name);
            dob = itemView.findViewById(R.id.patient_dob);
            disease = itemView.findViewById(R.id.disease_description);
            appointmentDate = itemView.findViewById(R.id.appointment_date);

        }
    }
}
