package com.example.booksmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web=findViewById(R.id.web);
        web.loadUrl("https://m.timesofindia.com/life-style/books/features/books-being-made-into-movies-in-2022/photostory/88855162.cms");
        web.setWebViewClient(new Web());

    }
    public class Web extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String  url) {
            view.loadUrl(url);
            return true;
        }
    }
}