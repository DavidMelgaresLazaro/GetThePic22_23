package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player;

public abstract class Player {
    private int points = 0;

    public abstract String getName();

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
    }

    public int getPoints(){
        return points;
    }
}

