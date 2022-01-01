package com.m90.mycardgame.ui.walletHistory;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.mycardgame.Adapters.FourCardPointsAdapter;
import com.m90.mycardgame.Adapters.WalletAdapter;
import com.m90.mycardgame.ApiInterfaces.LoginInterface;
import com.m90.mycardgame.Models.GameEnterModel;
import com.m90.mycardgame.Models.GetWalletPointsModel;
import com.m90.mycardgame.Models.GetWalletTransactionModel;
import com.m90.mycardgame.Models.WalletModel;
import com.m90.mycardgame.NavigationDrawerActivity;
import com.m90.mycardgame.R;
import com.m90.mycardgame.RetrofitAdapter;
import com.m90.mycardgame.ThirteenCardActivity;
import com.m90.mycardgame.databinding.FragmentWalletHistoryBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletHistoryFragment extends Fragment {

    private FragmentWalletHistoryBinding binding;

    RecyclerView recyclerView;
    WalletAdapter adapter;
    List<GetWalletTransactionModel> list;

    TextView walletBalanceTv;

    String userId;

    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWalletHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userId = sh.getString("userId", null);

        progressDialog = new ProgressDialog(getContext());

        walletBalanceTv = root.findViewById(R.id.walletBalanceTv);

        recyclerView = root.findViewById(R.id.walletHistoryRv);

        list = new ArrayList<>();


        getWalletPoint();

        getWalletTransactions();

        return root;
    }

    private void getWalletTransactions() {

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        LoginInterface loginInterface =
                RetrofitAdapter.getService().create(LoginInterface.class);

        Call<List<GetWalletTransactionModel>> call = loginInterface.getWalletTransactions(userId);

        call.enqueue(new Callback<List<GetWalletTransactionModel>>() {
            @Override
            public void onResponse(Call<List<GetWalletTransactionModel>> call, Response<List<GetWalletTransactionModel>> response) {

                Log.e("response", String.valueOf(response.body()));

                if (response.body() != null) {

                    list = response.body();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);

                    Collections.reverse(list);

                    adapter = new WalletAdapter(getContext(), list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<GetWalletTransactionModel>> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


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

                    walletBalanceTv.setText(String.valueOf(response.body().getPoints()));

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}