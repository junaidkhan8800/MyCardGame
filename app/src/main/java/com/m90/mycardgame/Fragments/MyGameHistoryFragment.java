package com.m90.mycardgame.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.m90.mycardgame.Adapters.MyGameHistoryAdapter;
import com.m90.mycardgame.Adapters.TodayResultHistoryAdapter;
import com.m90.mycardgame.Adapters.WalletAdapter;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.Models.GetWalletTransactionModel;
import com.m90.mycardgame.Models.MyGameHistoryModel;
import com.m90.mycardgame.Models.TodayResultHistoryModel;
import com.m90.mycardgame.R;
import com.m90.mycardgame.RetrofitAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyGameHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    MyGameHistoryAdapter adapter;
    List<MyGameHistoryModel> list;

    ProgressDialog progressDialog;

    String userId;

    public MyGameHistoryFragment() {
        // Required empty public constructor
    }

    public static MyGameHistoryFragment newInstance(String param1, String param2) {
        MyGameHistoryFragment fragment = new MyGameHistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_game_history, container, false);

        progressDialog = new ProgressDialog(getContext());
        recyclerView = view.findViewById(R.id.gameHistoryRv);

        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userId = sh.getString("userId", null);

        list = new ArrayList<>();

//        list.add(new MyGameHistoryModel(true,2,"krjghewkrj","dvsd",1));
//        list.add(new MyGameHistoryModel(false,13,"krjghewkrj","sdvs",0));
//        list.add(new MyGameHistoryModel(false,7,"krjghewkrj","vwrvwfv",0));
//        list.add(new MyGameHistoryModel(true,1, "krjghewkrj","sdfg",1));
//
//        adapter = new MyGameHistoryAdapter(getContext(),list);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//
//        recyclerView.setLayoutManager(layoutManager);
//        // rv_cardThirtreen.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        getMyGameHistory();

        return view;
    }

    private void getMyGameHistory() {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<List<MyGameHistoryModel>> call = loginInterface.getMyGameHistory(userId);

        call.enqueue(new Callback<List<MyGameHistoryModel>>() {
            @Override
            public void onResponse(Call<List<MyGameHistoryModel>> call, Response<List<MyGameHistoryModel>> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    list = response.body();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);


                    adapter = new MyGameHistoryAdapter(getContext(), list);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();


                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<MyGameHistoryModel>> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
}