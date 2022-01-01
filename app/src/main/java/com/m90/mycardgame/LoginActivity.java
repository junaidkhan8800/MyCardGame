package com.m90.mycardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginContinue;
    TextInputLayout mobileNoEt;
    CheckBox loginCheck;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileNoEt = findViewById(R.id.mobileNoEt);
        loginContinue = findViewById(R.id.loginContinue);

        loginCheck = findViewById(R.id.loginCheck);

        progressDialog = new ProgressDialog(LoginActivity.this);

        loginContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (mobileNoEt.getEditText().getText().toString() != "" && loginCheck.isChecked()){
                    userLogin(mobileNoEt.getEditText().getText().toString());
                }else if (mobileNoEt.getEditText().getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please provide proper mobile number", Toast.LENGTH_SHORT).show();
                }else if (!loginCheck.isChecked()){
                    Toast.makeText(LoginActivity.this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void userLogin(String mobileNo) {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<LoginModel> call = loginInterface.userLogin(mobileNo);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    Toast.makeText(LoginActivity.this, "OTP sent successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    intent.putExtra("mobileNo",mobileNo);
                    startActivity(intent);

                    progressDialog.dismiss();


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Please check your information and try again", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}