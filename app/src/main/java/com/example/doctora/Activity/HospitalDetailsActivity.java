package com.example.doctora.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.doctora.R;

public class HospitalDetailsActivity extends AppCompatActivity {

    private TextView hospitalName, hospitalAddress, hospitalContact, hospitalDescription; // New TextView for description
    private ImageView hospitalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        hospitalName = findViewById(R.id.hospital_name);
        hospitalAddress = findViewById(R.id.hospital_address);
        hospitalContact = findViewById(R.id.hospital_contact);
        hospitalImage = findViewById(R.id.hospital_image);
        hospitalDescription = findViewById(R.id.hospital_description); // Find the new TextView

        String name = getIntent().getStringExtra("hospital_name");
        String address = getIntent().getStringExtra("hospital_address");
        String contact = getIntent().getStringExtra("hospital_contact");
        int imageResourceId = getIntent().getIntExtra("hospital_image", R.drawable.gears); // Default image
        String description = getIntent().getStringExtra("hospital_description"); // Get the description

        // Set the data to views
        hospitalName.setText(name);
        hospitalAddress.setText(address);
        hospitalContact.setText(contact);
        hospitalImage.setImageResource(imageResourceId);
        hospitalDescription.setText(description); // Set the description to the TextView
    }
}
