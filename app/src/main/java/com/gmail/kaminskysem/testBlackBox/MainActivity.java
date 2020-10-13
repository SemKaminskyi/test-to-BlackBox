package com.gmail.kaminskysem.testBlackBox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String urlString = "https://html5test.com/";
    private WebView webView;
    private OkHttpClient client = new OkHttpClient();

    private static final String GAME_ID = "game";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.wv_HTML5test);

        String settingsTAG = "AppNameSettings";



        if (((MyApp) getApplication()).getFirstRun()) {
            if (savedInstanceState != null)
                webView.restoreState(savedInstanceState);
            else{
            startConnect();
            }
        } else {
            openGame();
        }


    }

    //open webView
    //Cookies used in WebView on default configuration

    private void startConnect() {
        //add javaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //add cache
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ((MyApp) getApplication()).setRunned();
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

    private void openGame() {
        Intent intentFailure = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intentFailure);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}

