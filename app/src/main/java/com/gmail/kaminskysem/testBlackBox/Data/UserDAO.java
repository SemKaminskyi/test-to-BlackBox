package com.gmail.kaminskysem.testBlackBox.Data;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static androidx.room.FtsOptions.Order.DESC;

@Dao
public interface UserDAO {
    @WorkerThread
    @Query("SELECT * FROM  game WHERE game_id = :gameId")
    Model getGameId(int gameId);

    @WorkerThread
    @Query("SELECT * FROM game WHERE id = :id")
    Model getID(int id);

    @WorkerThread
    @Query("SELECT * FROM  game ORDER BY game_id DESC")
    List<Model> getTaskList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Model model);



}
