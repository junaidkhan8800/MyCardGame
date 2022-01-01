package com.m90.mycardgame.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyGameHistoryModel {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("selected_card")
    @Expose
    private Integer selectedCard;
    @SerializedName("points_used")
    @Expose
    private Integer pointsUsed;
    @SerializedName("game_id")
    @Expose
    private String gameId;
    @SerializedName("is_winner")
    @Expose
    private Boolean isWinner;
    @SerializedName("points_collected")
    @Expose
    private Integer pointsCollected;
    @SerializedName("in_game")
    @Expose
    private Boolean inGame;
    @SerializedName("game_type")
    @Expose
    private String gameType;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Integer selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }

    public Integer getPointsCollected() {
        return pointsCollected;
    }

    public void setPointsCollected(Integer pointsCollected) {
        this.pointsCollected = pointsCollected;
    }

    public Boolean getInGame() {
        return inGame;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
