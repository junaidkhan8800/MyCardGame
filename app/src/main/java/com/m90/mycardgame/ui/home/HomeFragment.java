package com.m90.mycardgame.ui.home;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.FourCardActivity;
import com.m90.mycardgame.LoginActivity;
import com.m90.mycardgame.Models.GetGameIdModel;
import com.m90.mycardgame.Models.GetWalletPointsModel;
import com.m90.mycardgame.Models.LoginModel;
import com.m90.mycardgame.OtpActivity;
import com.m90.mycardgame.R;
import com.m90.mycardgame.RetrofitAdapter;
import com.m90.mycardgame.ThirteenCardActivity;
import com.m90.mycardgame.databinding.FragmentHomeBinding;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    TextView btn_fourCards, btn_thirteenCards, homeWalletBal;

    ProgressDialog progressDialog;

    String userId;

    TextView timerRemaining;

    Button share;

    String newDeepLink;

    String referralCode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userId = sh.getString("userId", null);

        btn_fourCards = root.findViewById(R.id.btn_fourCards);
        btn_thirteenCards = root.findViewById(R.id.btn_thirteenCards);
        homeWalletBal = root.findViewById(R.id.homeWalletBal);
        timerRemaining = root.findViewById(R.id.timerRemaining);

        // Creating a deep link and display it in the UI
        //newDeepLink = buildDeepLink(Uri.parse("https://play.google.com/store/apps/details?id=com.m90.mycardgame"));

        share = root.findViewById(R.id.buttonShare);

        progressDialog = new ProgressDialog(getContext());

        btn_fourCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getGameId("4");

                //Bundle bundle =  new Bundle();
                //bundle.putString("FourORThirteenCards","FourCard");
                //Utilities.launchActivity(getActivity(), FourCardActivity.class,false,bundle);

            }
        });

        btn_thirteenCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getGameIdT("13");

                //Bundle bundle =  new Bundle();
                //bundle.putString("FourORThirteenCards","ThirteenCard");
                //startActivity(new Intent(getContext(), ThirteenCardActivity.class));

                //Utilities.launchActivity(getActivity(),FourCardActivity.class,false,bundle);

            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareDeepLink();

            }
        });

        getTimer();

        getWalletPoint();

        timerRemaining.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (timerRemaining.getText().toString().equals("0 min, 10 seconds")){

                    btn_fourCards.setEnabled(false);
                    btn_thirteenCards.setEnabled(false);

                    btn_fourCards.setAlpha(0.5F);
                    btn_thirteenCards.setAlpha(0.5F);

                }else if (timerRemaining.getText().toString().equals("14 min, 59 seconds")){

                    btn_fourCards.setEnabled(true);
                    btn_thirteenCards.setEnabled(true);

                    btn_fourCards.setAlpha(1);
                    btn_thirteenCards.setAlpha(1);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }

    private void shareDeepLink() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Firebase Deep Link");
        intent.putExtra(Intent.EXTRA_TEXT, "Have a look at this awesome Jackpot Lottery App. Install with my refer code and get 200 Points. "+
                "https://jackpotlottery.page.link/?link=https://play.google.com/store/apps/details?id%3Dcom.m90.mycardgame&apn=com.m90.mycardgame"+
                "\n\nREFFERAL CODE : "+referralCode);

        startActivity(intent);

    }

    private String buildDeepLink(Uri parse) {

        String dynamicLink = String.valueOf(FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(parse.toString()))
                .setDomainUriPrefix("https://jackpotlottery.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build()));
                // Open links with com.example.ios on iOS
//                .setIosParameters(DynamicLink.IosParameters.Builder("com.example.ios").build())
//                .buildDynamicLink();

        String dynamicLinkUri = dynamicLink;

        return dynamicLinkUri;

    }

    private void getTimer() {

        Calendar c = Calendar.getInstance();
        int m = c.get(Calendar.MINUTE);

        long remain=0;
        if (m<15)
        {
            remain = 15-m;
            Log.e("Timer", String.valueOf(remain));
        }
        else if (m<30)
        {
            remain = 30-m;
        }
        else if (m<45)
        {
            remain = 45-m;
        }
        else
        {
            remain = 60-m;
            Log.e("Timer", String.valueOf(remain));
        }
        //remain= start + (remain * 60)* 1000;// convert it to milisecond and plus it to current time;

        remain = remain * 1000 * 60;

        new CountDownTimer(remain, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timerRemaining.setText(""+String.format("%d min, %d seconds",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                //timerRemaining.setText("done!");
                getTimer();
            }
        }.start();

        //timerRemaining.setText(String.valueOf(minutes +" : "+ seconds));

    }

    private void getWalletPoint() {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<GetWalletPointsModel> call = loginInterface.getWalletPoints(userId);

        call.enqueue(new Callback<GetWalletPointsModel>() {
            @Override
            public void onResponse(Call<GetWalletPointsModel> call, Response<GetWalletPointsModel> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    homeWalletBal.setText(String.valueOf(response.body().getPoints()));

                    referralCode = response.body().getRefferalCode();

                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<GetWalletPointsModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getGameIdT(String gameTypeT) {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<GetGameIdModel> call = loginInterface.getGameID(gameTypeT);

        call.enqueue(new Callback<GetGameIdModel>() {
            @Override
            public void onResponse(Call<GetGameIdModel> call, Response<GetGameIdModel> response) {

                Log.e("response", String.valueOf(response.body().getGameId()));

                if (response.body() != null) {

                    Intent intent = new Intent(getContext(), ThirteenCardActivity.class);
                    intent.putExtra("gameId",response.body().getGameId());
                    intent.putExtra("homeWalletBal",homeWalletBal.getText().toString());
                    startActivity(intent);
                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetGameIdModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getGameId(String gameType) {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<GetGameIdModel> call = loginInterface.getGameID(gameType);

        call.enqueue(new Callback<GetGameIdModel>() {
            @Override
            public void onResponse(Call<GetGameIdModel> call, Response<GetGameIdModel> response) {

                Log.e("response", String.valueOf(response.body().getGameId()));

                if (response.body() != null) {

                    Intent intent = new Intent(getContext(), FourCardActivity.class);
                    intent.putExtra("gameId",response.body().getGameId());
                    intent.putExtra("homeWalletBal",homeWalletBal.getText().toString());
                    startActivity(intent);

                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetGameIdModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}