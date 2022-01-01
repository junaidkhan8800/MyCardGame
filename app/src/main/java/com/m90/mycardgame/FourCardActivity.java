package com.m90.mycardgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.m90.mycardgame.Adapters.FourCardAdapter;
import com.m90.mycardgame.Adapters.FourCardPointsAdapter;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.Models.FourCardModel;
import com.m90.mycardgame.Models.FourCardPointModel;
import com.m90.mycardgame.Models.GameEnterModel;
import com.m90.mycardgame.Models.LoginModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FourCardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FourCardAdapter adapter;
    List<FourCardModel> list;

    RecyclerView recyclerViewPoints;
    FourCardPointsAdapter adapterPoints;
    List<FourCardPointModel> listPoints;

    TextView tv_back, tv_continue;

    int cardNo, selectedPoints;
    String gameId, userId, walletBalance;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_card);

        gameId = getIntent().getStringExtra("gameId");
        walletBalance = getIntent().getStringExtra("homeWalletBal");
        //Toast.makeText(FourCardActivity.this, ""+gameId, Toast.LENGTH_SHORT).show();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userId = sh.getString("userId", null);

        //Toast.makeText(FourCardActivity.this, ""+userId, Toast.LENGTH_SHORT).show();

        progressDialog = new ProgressDialog(FourCardActivity.this);

        recyclerView = findViewById(R.id.fourCardRv);
        list = new ArrayList<>();

        recyclerViewPoints = findViewById(R.id.fourPointsRv);
        listPoints = new ArrayList<>();

        tv_back = findViewById(R.id.tv_back);
        tv_continue = findViewById(R.id.tv_continue);

        list.add(new FourCardModel(R.drawable.ic_cards_leaf));
        list.add(new FourCardModel(R.drawable.ic_asset));
        list.add(new FourCardModel(R.drawable.ic_cards_spade));
        list.add(new FourCardModel(R.drawable.ic_cards_diamond));

        adapter = new FourCardAdapter(this, list, new FourCardAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int position) {
                int pos =position+1;

                cardNo = pos;

                //Toast.makeText(FourCardActivity.this, ""+a, Toast.LENGTH_SHORT).show();


            }
        });

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);
        // rv_cardThirtreen.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        listPoints.add(new FourCardPointModel("10"));
        listPoints.add(new FourCardPointModel("20"));
        listPoints.add(new FourCardPointModel("30"));
        listPoints.add(new FourCardPointModel("40"));
        listPoints.add(new FourCardPointModel("50"));
        listPoints.add(new FourCardPointModel("100"));
        listPoints.add(new FourCardPointModel("200"));
        listPoints.add(new FourCardPointModel("500"));
        listPoints.add(new FourCardPointModel("1000"));
        listPoints.add(new FourCardPointModel("2000"));

        adapterPoints = new FourCardPointsAdapter(this, listPoints, new FourCardPointsAdapter.onPointsClickListener() {

            @Override
            public void onPointsClick(int position, String points) {
                int pos =position+1;

                selectedPoints = Integer.parseInt(points);
                //Toast.makeText(FourCardActivity.this, ""+selectedPoints, Toast.LENGTH_SHORT).show();
            }
        });

        GridLayoutManager layoutManager1 = new GridLayoutManager(this,5);

        recyclerViewPoints.setLayoutManager(layoutManager1);
        // rv_cardThirtreen.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPoints.setAdapter(adapterPoints);


        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardNo == 0){
                    Toast.makeText(FourCardActivity.this, "Please select a Card", Toast.LENGTH_SHORT).show();
                }else if (selectedPoints == 0){
                    Toast.makeText(FourCardActivity.this, "Please select Points", Toast.LENGTH_SHORT).show();
                }else if (Integer.parseInt(walletBalance) < selectedPoints){
                    Toast.makeText(FourCardActivity.this, "Not Enough Balance to join the game", Toast.LENGTH_SHORT).show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(FourCardActivity.this);
                    builder.setCancelable(false);
                    //builder.setTitle("Logout");
                    builder.setMessage("Your selected Card Number is " + cardNo + " and selected Points are " + selectedPoints)
                            .setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    enterTheGame();

                                    dialog.dismiss();

                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }
            }
        });

    }

    private void enterTheGame() {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<GameEnterModel> call = loginInterface.enterGame(userId, cardNo, selectedPoints, gameId);

        call.enqueue(new Callback<GameEnterModel>() {
            @Override
            public void onResponse(Call<GameEnterModel> call, Response<GameEnterModel> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    if(response.body().getInGame() == true){

                        Toast.makeText(FourCardActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    }else{

                        Toast.makeText(FourCardActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(FourCardActivity.this, NavigationDrawerActivity.class);
                        //intent.putExtra("mobileNo",mobileNo);
                        startActivity(intent);

                        progressDialog.dismiss();

                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(FourCardActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<GameEnterModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(FourCardActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}