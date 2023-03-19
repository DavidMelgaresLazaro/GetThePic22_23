package cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cat.udl.getthepic.gtidic.udl.getthepic.android.jjd2223.Models.Game;

@Dao
public interface GameDAO {
    @Insert
    long[] insertAll(Game... games);

    @Insert
    long insert(Game game);

    @Query("SELECT * FROM game")
    List<Game> getAll();

    @Delete
    void deleteOne(Game game);

    @Query("DELETE FROM game")
    void deleteAll();

    // max Points
    @Query("SELECT max(maxPoints) FROM GAME")
    int getMaxPoints();

    // last game
    @Query("SELECT maxPoints from Game order by id desc LIMIT 1")
    int getLastGamePoints();

    @Update
    void update(Game game);
}