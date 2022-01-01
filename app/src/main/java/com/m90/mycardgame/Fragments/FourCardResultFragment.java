package com.m90.mycardgame.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.m90.mycardgame.Adapters.TodayResultHistoryAdapter;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.Models.TodayResultHistoryModel;
import com.m90.mycardgame.R;
import com.m90.mycardgame.RetrofitAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FourCardResultFragment extends Fragment {

    RecyclerView recyclerView;
    TodayResultHistoryAdapter adapter;
    List<TodayResultHistoryModel> list;

    ProgressDialog progressDialog;

    public FourCardResultFragment() {
        // Required empty public constructor
    }


    public static FourCardResultFragment newInstance(String param1, String param2) {
        FourCardResultFragment fragment = new FourCardResultFragment();

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
        View view = inflater.inflate(R.layout.fragment_four_card_result, container, false);

        progressDialog = new ProgressDialog(getContext());
        recyclerView = view.findViewById(R.id.fResultRv);

        list = new ArrayList<>();

//        list.add(new TodayResultHistoryModel(1,"dvsd","vsv"));
//        list.add(new TodayResultHistoryModel(2,"sdvs","srvwv"));
//        list.add(new TodayResultHistoryModel(3,"vwrvwfv","gdffdg"));
//        list.add(new TodayResultHistoryModel(4,"sdfg","fgrgtr"));
//
//        adapter = new TodayResultHistoryAdapter(getContext(),list,1);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//
//        recyclerView.setLayoutManager(layoutManager);
//        // rv_cardThirtreen.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        getTodayFourResult();

        return view;
    }

    private void getTodayFourResult() {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<List<TodayResultHistoryModel>> call = loginInterface.getTodaysGameHistory();

        call.enqueue(new Callback<List<TodayResultHistoryModel>>() {
            @Override
            public void onResponse(Call<List<TodayResultHistoryModel>> call, Response<List<TodayResultHistoryModel>> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    list = response.body();

                    List<TodayResultHistoryModel> listnew = new ArrayList<>();

                    for (int i = 0; i < list.size(); i++) {

                        if(list.get(i).getGameType().equals("4"))
                        {
                            listnew.add(new TodayResultHistoryModel(list.get(i).getId(),list.get(i).getGameId(),list.get(i).getGameType(),list.get(i).getWinnerCard(),list.get(i).getStatus(),list.get(i).getStartDate(),list.get(i).getStartTime(),list.get(i).getV()));
                        }

                    }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);


                    adapter = new TodayResultHistoryAdapter(getContext(), listnew,1);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();


                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TodayResultHistoryModel>> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}