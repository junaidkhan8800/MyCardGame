package com.m90.mycardgame;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    public static final String BASE_URL = "http://jackpot.kalyanstarline.site:8081/";

    public static Retrofit retrofit = null;

    public static Retrofit getService(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
