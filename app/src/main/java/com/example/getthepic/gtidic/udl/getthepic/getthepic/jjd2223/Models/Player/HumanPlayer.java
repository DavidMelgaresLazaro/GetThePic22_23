package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Player;

public class HumanPlayer extends Player {
    private String name;


    @Override
    public String getName() {
        return name;
    }
    public HumanPlayer(String name){
        this.name = name;
    }
}


