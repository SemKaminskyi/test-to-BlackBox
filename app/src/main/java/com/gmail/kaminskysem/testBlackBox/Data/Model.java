package com.gmail.kaminskysem.testBlackBox.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "game")
public class Model {


    @NonNull
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "game_id")
    private int gameID;


    @ColumnInfo
    private int id;

    public Model(int id) {
        this.id = id;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }


    public int getGameID() {
        return gameID;
    }


    public int getId() {
        return id;
    }
}
