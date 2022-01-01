package com.m90.mycardgame.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWalletPointsModel {

    @SerializedName("wallet_id")
    @Expose
    private String walletId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("refferal_code")
    @Expose
    private String refferalCode;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getRefferalCode() {
        return refferalCode;
    }

    public void setRefferalCode(String refferalCode) {
        this.refferalCode = refferalCode;
    }
}
