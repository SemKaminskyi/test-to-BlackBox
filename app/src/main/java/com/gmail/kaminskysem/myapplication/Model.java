package com.gmail.kaminskysem.myapplication;

import android.media.Image;
import android.widget.ImageButton;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "game")
public class Model {
    @ColumnInfo
    private boolean connectToSite;

    @ColumnInfo
    @PrimaryKey (autoGenerate = true)
    private int gameID;

//    @ColumnInfo
//    private Image task;
//
//    @ColumnInfo
//    private ImageButton variant1;
//
//    @ColumnInfo
//    private ImageButton variant2;
//
//    @ColumnInfo
//    private ImageButton variant3;
//
//    @ColumnInfo
//    private ImageButton variant4;
//
//    @ColumnInfo
//    private ImageButton solution;



}
