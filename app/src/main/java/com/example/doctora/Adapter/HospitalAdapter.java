package com.example.doctora.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctora.Activity.HospitalDetailsActivity;
import com.example.doctora.Model.Hospital;
import com.example.doctora.R;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    private List<Hospital> hospitalList;
    private Context context; // Added context to launch the activity

    public HospitalAdapter(List<Hospital> hospitalList, Context context) {
        this.hospitalList = hospitalList;
        this.context = context;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);
        holder.hospitalName.setText(hospital.getName());
        holder.hospitalAddress.setText(hospital.getAddress());
        holder.hospitalContact.setText(hospital.getContactNumber());
        holder.hospitalImage.setImageResource(hospital.getImageResourceId());

        // Handle click event to open HospitalDetailsActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, HospitalDetailsActivity.class);
            intent.putExtra("hospital_name", hospital.getName());
            intent.putExtra("hospital_address", hospital.getAddress());
            intent.putExtra("hospital_contact", hospital.getContactNumber());
            intent.putExtra("hospital_image", hospital.getImageResourceId());
            intent.putExtra("hospital_description", hospital.getDescription()); // Pass the description
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public static class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName, hospitalAddress, hospitalContact;
        ImageView hospitalImage;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospital_name);
            hospitalAddress = itemView.findViewById(R.id.hospital_address);
            hospitalContact = itemView.findViewById(R.id.hospital_contact);
            hospitalImage = itemView.findViewById(R.id.hospital_image);
        }
    }
}
