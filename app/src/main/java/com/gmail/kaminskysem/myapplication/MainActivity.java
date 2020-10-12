package com.gmail.kaminskysem.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.kaminskysem.myapplication.GameFragments.FragmentGame1;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String urlString = "https://html5test.com/";
    private boolean connectToSite = false;
    private WebView webView;
    private OkHttpClient client = new OkHttpClient();
    private int numOfGame=1;
    private static final String GAME_ID = "game";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView= findViewById(R.id.wv_HTML5test);


        if (connectToSite){
            startConnect();
        }else {
            openGame();
        }


    }

    //open webView
    private void startConnect() {
        Request request = new Request.Builder()
                .url(urlString)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                connectToSite = false;
                openGame();

                Log.e("HTTP", "failed to load http call", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                runOnUiThread(() -> webView.loadUrl(urlString));
                Log.e("HTTP", "successful to load http call");
            }

        });

    }
    private void openGame (){
        Intent intentFailure = new Intent(getApplicationContext(), GameActivity.class);
        intentFailure.putExtra(GAME_ID, numOfGame);
        startActivity(intentFailure);





    }

    public boolean isConnectToSite() {
        return connectToSite;
    }
}

