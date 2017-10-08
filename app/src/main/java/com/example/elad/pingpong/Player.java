package com.example.elad.pingpong;

/**
 * Created by elad on 18/06/2017.
 */

public class Player {
    String nickname;
    int pk;
    String place;

    public Player() {
    }

    public Player(String nickname, int pk, String place) {
        this.nickname = nickname;
        this.pk = pk;
        this.place = place;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}