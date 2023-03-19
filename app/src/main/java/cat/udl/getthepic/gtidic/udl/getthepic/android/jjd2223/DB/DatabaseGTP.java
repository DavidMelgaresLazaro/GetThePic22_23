package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.HumanPlayer;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.Player;

@Database(entities = {Game.class}, version = 1)
public abstract class DatabaseGTP extends RoomDatabase {
    public abstract GameDAO gameDAO();
}