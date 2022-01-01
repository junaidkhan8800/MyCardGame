package com.m90.mycardgame.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("msg")
    @Expose
    private String msg;

    public LoginModel(String msg) {
        this.msg = msg;
    }
}
