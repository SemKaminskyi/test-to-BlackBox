package com.gmail.kaminskysem.testBlackBox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmail.kaminskysem.testBlackBox.GameFragments.FragmentGame1;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        FragmentGame1 fragmentGame1 = new FragmentGame1();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game_container,fragmentGame1)
                .commit();

    }
}