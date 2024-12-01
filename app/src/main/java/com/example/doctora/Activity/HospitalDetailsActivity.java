package com.example.doctora.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.doctora.R;

public class HospitalDetailsActivity extends AppCompatActivity {

    private TextView hospitalName, hospitalAddress, hospitalContact, hospitalDescription; // New TextView for description
    private ImageView hospitalImage;
    private ProgressBar progressBar; // ProgressBar to show loading

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        // Initialize views
        hospitalName = findViewById(R.id.hospital_name);
        hospitalAddress = findViewById(R.id.hospital_address);
        hospitalContact = findViewById(R.id.hospital_contact);
        hospitalImage = findViewById(R.id.hospital_image);
        hospitalDescription = findViewById(R.id.hospital_description);
        progressBar = findViewById(R.id.progress_bar); // Initialize ProgressBar

        // Initially hide all the data views
        hideDataViews();

        // Show the ProgressBar while loading data
        progressBar.setVisibility(View.VISIBLE);

        // Simulate data fetching with a delay
        new Handler().postDelayed(() -> {
            // Hide ProgressBar after loading data
            progressBar.setVisibility(View.GONE);

            // Get data from Intent
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

            // Show data views
            showDataViews();

            // Load and start the animation
            Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
            hospitalImage.startAnimation(scaleAnimation);
            hospitalName.startAnimation(scaleAnimation);
            hospitalAddress.startAnimation(scaleAnimation);
            hospitalContact.startAnimation(scaleAnimation);
            hospitalDescription.startAnimation(scaleAnimation);
        }, 1000); // Simulate 2 seconds delay for loading
    }

    private void hideDataViews() {
        hospitalName.setVisibility(View.INVISIBLE);
        hospitalAddress.setVisibility(View.INVISIBLE);
        hospitalContact.setVisibility(View.INVISIBLE);
        hospitalImage.setVisibility(View.INVISIBLE);
        hospitalDescription.setVisibility(View.INVISIBLE);
    }

    private void showDataViews() {
        hospitalName.setVisibility(View.VISIBLE);
        hospitalAddress.setVisibility(View.VISIBLE);
        hospitalContact.setVisibility(View.VISIBLE);
        hospitalImage.setVisibility(View.VISIBLE);
        hospitalDescription.setVisibility(View.VISIBLE);
    }
}
