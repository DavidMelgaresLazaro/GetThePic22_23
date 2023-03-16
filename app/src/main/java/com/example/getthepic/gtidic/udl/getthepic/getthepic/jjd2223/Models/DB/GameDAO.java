package com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.getthepic.gtidic.udl.getthepic.getthepic.jjd2223.Models.Game;

import java.util.List;

@Dao
public interface GameDAO {
    @Insert
    void insertAll(Game... comics);


    @Query("SELECT * FROM game")
    List<Game> getAll();

    @Delete
    void deleteOne(Game comic);

    @Query("DELETE FROM game")
    void deleteAll();

    // max points
    //last game
}
