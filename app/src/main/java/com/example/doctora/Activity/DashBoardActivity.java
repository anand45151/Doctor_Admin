package com.example.doctora.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.doctora.Fragment.AppoinmentFragment;
import com.example.doctora.Fragment.DashBoardFragment;
import com.example.doctora.Fragment.ProfileFragment;
import com.example.doctora.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        loadFragment(new DashBoardFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.appoinment) {
                selectedFragment = new AppoinmentFragment();
            } else if (item.getItemId() == R.id.dashBoard) {
                selectedFragment = new DashBoardFragment();
            } else if (item.getItemId() == R.id.profile) {
                selectedFragment = new ProfileFragment();
            }
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
