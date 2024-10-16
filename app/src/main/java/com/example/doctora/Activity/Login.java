package com.example.doctora.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctora.Model.DoctorDetailsModel;
import com.example.doctora.Model.LoginResponse_Model;
import com.example.doctora.R;
import com.example.doctora.Utils.ApiClient;
import com.example.doctora.Utils.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button SignInBtn;
    Button signUpTXT;
    EditText doctorIdEdt, doctorNameEdt;
    TextView forgotPasswordTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find views by ID
        SignInBtn = findViewById(R.id.SignInBtn);
        signUpTXT = findViewById(R.id.signUpTXT);
        doctorIdEdt = findViewById(R.id.LoginDoctorIdEdt);
        doctorNameEdt = findViewById(R.id.LoginDoctorNameEdt);
        forgotPasswordTXT = findViewById(R.id.forgotPasswordTXT);

        // Handle Sign In button click
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorId = doctorIdEdt.getText().toString();
                String doctorName = doctorNameEdt.getText().toString();



                if (!doctorId.isEmpty() && !doctorName.isEmpty()) {
                    loginDoctor(doctorId, doctorName);
                } else {
                    Toast.makeText(Login.this, "Please enter Doctor ID and Doctor Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        forgotPasswordTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    private void loginDoctor(String doctorId, String doctorName) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<LoginResponse_Model> call = apiService.loginDoctor(doctorId, doctorName);

        call.enqueue(new Callback<LoginResponse_Model>() {
            @Override
            public void onResponse(Call<LoginResponse_Model> call, Response<LoginResponse_Model> response) {
                Log.d("API Response Code", String.valueOf(response.code()));
                Log.d("API Response Body", String.valueOf(response.body()));
                Log.d("API Error Body", String.valueOf(response.errorBody()));

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse_Model loginResponse_model = response.body();
                    if (loginResponse_model.getStatus().equals("success")) {
                        String doctorname = loginResponse_model.getDoctor().getName();
                        saveDoctorNameToSharedPreferences(doctorname);
                        fetchDoctorDetails(doctorname); // Fetch doctor details after login success

                        Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, DashBoardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, loginResponse_model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse_Model> call, Throwable t) {
                Log.e("LoginError", "Error: " + t.getMessage());
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchDoctorDetails(String doctorName) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<DoctorDetailsModel> call = apiService.getDoctorDetails(doctorName);

        call.enqueue(new Callback<DoctorDetailsModel>() {
            @Override
            public void onResponse(Call<DoctorDetailsModel> call, Response<DoctorDetailsModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DoctorDetailsModel doctorDetails = response.body();
                     doctorDetails = response.body();
                    Log.d("DoctorDetails", "Received: " + doctorDetails.toString());
                    saveDoctorDetailsToSharedPreferences(doctorDetails);
                } else {
                    Toast.makeText(Login.this, "Failed to fetch doctor details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DoctorDetailsModel> call, Throwable t) {
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveDoctorDetailsToSharedPreferences(DoctorDetailsModel doctorDetails) {
        SharedPreferences sharedPreferences = getSharedPreferences("DoctorPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DoctorSpecialty", doctorDetails.getDoctorSpecialty());
        editor.putString("DoctorExperiences", doctorDetails.getDoctorExperiences());
        editor.putString("DoctorLocation", doctorDetails.getDoctorLocation());
        editor.putString("DoctorPhoto", doctorDetails.getDoctorPhoto());
        editor.apply();

        Log.d("DoctorDetails", "Saved Specialty: " + doctorDetails.getDoctorSpecialty());
        Log.d("DoctorDetails", "Saved Experiences: " + doctorDetails.getDoctorExperiences());
        Log.d("DoctorDetails", "Saved Location: " + doctorDetails.getDoctorLocation());
        Log.d("DoctorDetails", "Saved Photo URL: " + doctorDetails.getDoctorPhoto());


    }


    private void saveDoctorNameToSharedPreferences(String doctorName) {
        SharedPreferences sharedPreferences = getSharedPreferences("DoctorPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DoctorName", doctorName);
        editor.apply();
    }


}
