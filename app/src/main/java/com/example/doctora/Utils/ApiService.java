package com.example.doctora.Utils;

import com.example.doctora.Model.DoctorDetailsModel;
import com.example.doctora.Model.LoginResponse_Model;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @Multipart
    @POST("Doctor_Register_Api.php")
    Call<ResponseBody> registerDoctor(
            @Part("Doctor_Name") RequestBody doctorName,
            @Part("Doctor_Specialty") RequestBody doctorSpecialty,
            @Part("Doctor_Experiences") RequestBody doctorExperience,
            @Part("Doctor_Location") RequestBody doctorLocation,
            @Part("Doctor_Photo") RequestBody doctorPhoto
    );

    @FormUrlEncoded
    @POST("Doctor_Login_Api.php")
    Call<LoginResponse_Model> loginDoctor(
            @Field("doctor_id") String doctorId,
            @Field("doctor_name") String doctorName
    );

    @GET("Doctor_Profile_Api.php")
    Call<DoctorDetailsModel> getDoctorDetails(@Query("doctor_name") String doctorName);


}
