package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;

@Database(entities = {Game.class}, version = 1,exportSchema = true)
public abstract class DatabaseGetThePic extends RoomDatabase {
    public abstract GameDAO gameDAO();
}
