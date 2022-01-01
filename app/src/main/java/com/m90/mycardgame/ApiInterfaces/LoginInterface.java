package com.m90.mycardgame.ApiInterfaces;

import com.m90.mycardgame.Models.GameEnterModel;
import com.m90.mycardgame.Models.GetGameIdModel;
import com.m90.mycardgame.Models.GetWalletPointsModel;
import com.m90.mycardgame.Models.GetWalletTransactionModel;
import com.m90.mycardgame.Models.IsFirstModel;
import com.m90.mycardgame.Models.LoginModel;
import com.m90.mycardgame.Models.MyGameHistoryModel;
import com.m90.mycardgame.Models.OtpModel;
import com.m90.mycardgame.Models.RefferalModel;
import com.m90.mycardgame.Models.TodayResultHistoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginModel> userLogin(
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("user/otpVerify")
    Call<OtpModel> otpverify(
            @Field("mobile") String mobile,
            @Field("otp") String otp
    );


    @FormUrlEncoded
    @POST("user/isfirst")
    Call<IsFirstModel> isFirst(
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("game/get_game_id")
    Call<GetGameIdModel> getGameID(
            @Field("game_type") String gameType
    );


    @FormUrlEncoded
    @POST(" user/user_join_history_insert")
    Call<GameEnterModel> enterGame(
            @Field("user_id") String userId,
            @Field("selected_card") int selectedCard,
            @Field("points_used") int pointsUsed,
            @Field("game_id") String gameId
    );


    @FormUrlEncoded
    @POST("user/get_wallet")
    Call<GetWalletPointsModel> getWalletPoints(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("user/get_transactions")
    Call<List<GetWalletTransactionModel>> getWalletTransactions(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("user/get_all_history")
    Call<List<MyGameHistoryModel>> getMyGameHistory(
            @Field("user_id") String user_id
    );


    @GET("game/today_games")
    Call<List<TodayResultHistoryModel>> getTodaysGameHistory();


    @FormUrlEncoded
    @POST("user/refferal")
    Call<RefferalModel> getRefferalPoints(
            @Field("user_id") String user_id,
            @Field("refferal_code") String refferal_code
    );

}
