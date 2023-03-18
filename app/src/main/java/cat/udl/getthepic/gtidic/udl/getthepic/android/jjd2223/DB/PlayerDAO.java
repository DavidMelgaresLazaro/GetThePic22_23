/*package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;
import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Player.Player;

@Dao
public interface PlayerDAO {

    @Insert
    String insert(Player player);

    @Query("SELECT * FROM player")
    List<Game> getAll();
    @Delete
    void deleteOne(Player player);

    @Query("DELETE FROM player")
    void deleteAll();

    // max Points from a player
    @Query("SELECT max(maxPoints) FROM Player")
    int getMaxPoints();

    // last level
    @Query("SELECT LAST_LEVEL from player")
    int getLastGamePoints();

    @Update
    void update(Player player);
}*/
