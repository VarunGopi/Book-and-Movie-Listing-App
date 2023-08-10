package com.example.booksmovies;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class tools extends AppCompatActivity {
    ImageButton rb,rm;
    Button ticket,news,musicbtn;
    String theme;
    ConstraintLayout layout;

    ActivityResultLauncher<Intent> themeSettingsResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    theme = intent.getStringExtra("theme");
                    if (theme.equals("sunny day")) {
                        layout.setBackgroundResource(R.drawable.gk);
                    }
                    else {
                        layout.setBackgroundResource(R.drawable.warmeveningbackground);
                    }
                }
            });

    ActivityResultLauncher<Intent> themeAfterView = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                if (theme.equals("sunny day")) {
                    layout.setBackgroundResource(R.drawable.gk);
                } else {
                    layout.setBackgroundResource(R.drawable.warmeveningbackground);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        musicbtn=findViewById(R.id.musicbtn);
        musicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tools.this,music.class);
                startActivity(intent);
            }
        });
        news=findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tools.this,web.class);
                startActivity(intent);

            }
        });

        rb=findViewById(R.id.rbtn);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://recommendmeabook.com/");
            }
        });
        rm=findViewById(R.id.rmbtn);
        rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://pickamovieforme.com/");
            }
        });
        ticket=findViewById(R.id.ticket);
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://in.bookmyshow.com/");
            }
        });

    }
    private void gotourl(String s){
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}