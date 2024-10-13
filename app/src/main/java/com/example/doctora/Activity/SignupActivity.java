package com.example.doctora.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.example.doctora.R;
import com.example.doctora.Utils.ApiClient;
import com.example.doctora.Utils.ApiService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private Uri selectedImageUri;
    private EditText doctorName, doctorSpecialty, doctorExperience, doctorLocation;
    private ImageView doctorImage;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializeViews();
        setupApiService();
        setupListeners();
    }

    private void initializeViews() {
        doctorImage = findViewById(R.id.doctor_Image);
        doctorName = findViewById(R.id.Doctor_Name);
        doctorSpecialty = findViewById(R.id.Doctor_Specialty);
        doctorExperience = findViewById(R.id.doctor_experince);
        doctorLocation = findViewById(R.id.Doctor_location);
        Button btnSignUp = findViewById(R.id.SignUpBtn);
    }

    private void setupApiService() {
        apiService = ApiClient.getInstance().getApiService();
    }

    private void setupListeners() {
        doctorImage.setOnClickListener(v -> openGallery());

        findViewById(R.id.SignUpBtn).setOnClickListener(v -> {
            if (isInputValid()) {
                registerDoctor();
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            doctorImage.setImageURI(selectedImageUri);
        }
    }

    private boolean isInputValid() {
        return !doctorName.getText().toString().isEmpty() &&
                !doctorSpecialty.getText().toString().isEmpty() &&
                !doctorExperience.getText().toString().isEmpty() &&
                !doctorLocation.getText().toString().isEmpty();
    }

    private void registerDoctor() {
        RequestBody doctorNameBody = createRequestBody(doctorName.getText().toString());
        RequestBody doctorSpecialtyBody = createRequestBody(doctorSpecialty.getText().toString());
        RequestBody doctorExperienceBody = createRequestBody(doctorExperience.getText().toString());
        RequestBody doctorLocationBody = createRequestBody(doctorLocation.getText().toString());
        RequestBody doctorPhotoBody = createPhotoRequestBody();

        Call<ResponseBody> call = apiService.registerDoctor(doctorNameBody, doctorSpecialtyBody,
                doctorExperienceBody, doctorLocationBody, doctorPhotoBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Doctor registered successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, DashBoardActivity.class));
                } else {
                    showError("Failed to register doctor: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showError("Error: " + t.getMessage());
            }
        });
    }

    private RequestBody createRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    private RequestBody createPhotoRequestBody() {
        if (selectedImageUri != null) {
            File file = new File(getRealPathFromURI(selectedImageUri));
            return RequestBody.create(MediaType.parse("image/*"), file);
        }
        return createRequestBody(""); // Return empty if no image selected
    }

    private void showError(String message) {
        Log.e("SignUp", message);
        Toast.makeText(SignupActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private String getRealPathFromURI(Uri contentUri) {
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri,
                new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        Cursor cursor = loader.loadInBackground();
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return null;
    }
}
