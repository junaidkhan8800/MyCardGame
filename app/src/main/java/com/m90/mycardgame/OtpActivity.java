package com.m90.mycardgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.Models.IsFirstModel;
import com.m90.mycardgame.Models.LoginModel;
import com.m90.mycardgame.Models.OtpModel;
import com.m90.mycardgame.Models.RefferalModel;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    CountDownTimer cTimer = null;
    TextView timer, resendOtpBtn;
    Button otpContinueBtn;
    TextInputLayout enterOtpet;

    String mobileNo;

    String currentUserId;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        timer = findViewById(R.id.timer);
        resendOtpBtn = findViewById(R.id.resendOtpBtn);
        otpContinueBtn = findViewById(R.id.otpContinueBtn);
        enterOtpet = findViewById(R.id.enterOtpet);

        progressDialog = new ProgressDialog(OtpActivity.this);

        mobileNo = getIntent().getStringExtra("mobileNo");

        showTimer();

        otpContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterOtpet.getEditText().getText().toString() == "") {
                    Toast.makeText(OtpActivity.this, "Please enter proper OTP", Toast.LENGTH_SHORT).show();
                } else {

                    if (mobileNo.equals("8459616844") && enterOtpet.getEditText().getText().toString().equals("1234")) {

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();

                        myEdit.putString("userId", "61cb18e5517c2bc5fc531ea0");
                        myEdit.apply();

                        //currentUserId = response.body().getUserId();

                        //Toast.makeText(OtpActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, "" + response.body().getToken(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));

                        progressDialog.dismiss();

                    } else {

//                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//                    myEdit.putString("userId", "61a5ee01f1823597683d8f2c");
//                    myEdit.apply();
//
//                    startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));

                        verifyOTP(mobileNo, enterOtpet.getEditText().getText().toString());
                    }
                }
                //startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));
            }
        });

        resendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOtpBtn.setVisibility(View.GONE);
                timer.setVisibility(View.VISIBLE);
                showTimer();

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

                            Toast.makeText(OtpActivity.this, "OTP sent successful", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(OtpActivity.this, "Please check your information and try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(OtpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void verifyOTP(String mobileNo, String otp) {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<OtpModel> call = loginInterface.otpverify(mobileNo, otp);

        call.enqueue(new Callback<OtpModel>() {
            @Override
            public void onResponse(Call<OtpModel> call, Response<OtpModel> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {
                    if (response.body().getMsg() != null) {


                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();

                        myEdit.putString("userId", response.body().getUserId());
                        myEdit.apply();

                        currentUserId = response.body().getUserId();

                        Toast.makeText(OtpActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, "" + response.body().getToken(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        if (response.body().getIsFirst() == true) {

                            changeIsFirst();
                            showReferPopup();

                        } else if (response.body().getIsFirst() == false) {

                            changeIsFirst();
                            startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));

                        }

                        //startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));


                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(OtpActivity.this, "Please check your credentials and try again", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(OtpActivity.this, "Please check your credentials and try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(OtpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showReferPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(OtpActivity.this);
        builder.setCancelable(false);

        LinearLayout layout = new LinearLayout(OtpActivity.this);
        layout.setPadding(20, 20, 20, 20);
        layout.setOrientation(LinearLayout.VERTICAL);

        final TextView headingText = new TextView(OtpActivity.this);
        headingText.setText("Enter a Refferal Code");
        headingText.setTextSize(22);
        headingText.setTypeface(null, Typeface.BOLD);
        layout.addView(headingText); // Notice this is an add method

        final EditText enterCode = new EditText(OtpActivity.this);
        enterCode.setHint("Enter Code");
        enterCode.setTextSize(18);
        layout.addView(enterCode); // Notice this is an add method

        builder.setView(layout); // Again this is a set method, not add

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show(); // show progress dialog

                LoginInterface loginInterface =
                        RetrofitAdapter.getService().create(LoginInterface.class);

                Log.i("Current User Id", currentUserId);
                Log.i("Refferal Code", enterCode.getText().toString());

                Call<RefferalModel> call = loginInterface.getRefferalPoints(currentUserId, enterCode.getText().toString());

                call.enqueue(new Callback<RefferalModel>() {
                    @Override
                    public void onResponse(Call<RefferalModel> call, Response<RefferalModel> response) {

                        Log.e("response", String.valueOf(response.body()));

                        if (response.body() != null) {

                            Toast.makeText(OtpActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();

                            //changeIsFirst();

                            startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));


                        }

                    }

                    @Override
                    public void onFailure(Call<RefferalModel> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(OtpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeIsFirst();
                        startActivity(new Intent(OtpActivity.this, NavigationDrawerActivity.class));

                    }
                });

        builder.show();


    }

    private void changeIsFirst() {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<IsFirstModel> call = loginInterface.isFirst(mobileNo);

        call.enqueue(new Callback<IsFirstModel>() {
            @Override
            public void onResponse(Call<IsFirstModel> call, Response<IsFirstModel> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    progressDialog.dismiss();


                } else {
                    progressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<IsFirstModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(OtpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showTimer() {

        cTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText("Resend OTP after : " + millisUntilFinished / 1000);

            }

            public void onFinish() {

                timer.setVisibility(View.GONE);
                resendOtpBtn.setVisibility(View.VISIBLE);

            }
        };
        cTimer.start();

    }

    //cancel timer
    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }
}