package com.m90.mycardgame.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameEnterModel {

    @SerializedName("msg")
    @Expose
    private String msg;
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
    @SerializedName("in_game")
    @Expose
    private Boolean inGame;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public Boolean getInGame() {
        return inGame;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }

}
