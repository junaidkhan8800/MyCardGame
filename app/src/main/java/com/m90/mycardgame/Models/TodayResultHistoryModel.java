package com.m90.mycardgame.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayResultHistoryModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("game_id")
    @Expose
    private String gameId;
    @SerializedName("game_type")
    @Expose
    private String gameType;
    @SerializedName("winner_card")
    @Expose
    private Integer winnerCard;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("__v")
    @Expose
    private Integer v;


    public TodayResultHistoryModel(String id, String gameId, String gameType, Integer winnerCard, Boolean status, String startDate, String startTime, Integer v) {
        this.id = id;
        this.gameId = gameId;
        this.gameType = gameType;
        this.winnerCard = winnerCard;
        this.status = status;
        this.startDate = startDate;
        this.startTime = startTime;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Integer getWinnerCard() {
        return winnerCard;
    }

    public void setWinnerCard(Integer winnerCard) {
        this.winnerCard = winnerCard;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }


}
