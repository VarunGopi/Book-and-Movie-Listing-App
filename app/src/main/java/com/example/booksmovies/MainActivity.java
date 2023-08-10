package com.example.booksmovies;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout;
    String theme;
    Button booksBtn, moviesBtn, abtun,reco;
    WebView web;

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
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout_main);



        booksBtn = findViewById(R.id.books_btn);
        booksBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, BooksViewActivity.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("theme", theme);
            themeAfterView.launch(intent);
        });

        moviesBtn = findViewById(R.id.movies_btn);
        moviesBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MoviesViewActivity.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("theme", theme);
            themeAfterView.launch(intent);
        });
        reco=findViewById(R.id.recoi);
        reco.setOnClickListener(view -> {
            Intent intent = new Intent(this, tools.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("theme", theme);
            themeAfterView.launch(intent);
        });


        abtun = findViewById(R.id.ab);
        abtun.setOnClickListener(v -> {
            Intent intent = new Intent(this, aboutus.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("theme", theme);
            themeAfterView.launch(intent);
        });
    }
    private void gotourl(String s){
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_main_menu_item) {
            Intent intent = new Intent(this, SettingsMainActivity.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("prev_theme", theme);
            themeSettingsResult.launch(intent);
        }

        if (item.getItemId() == R.id.info_main_menu_item) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("Home Assignment\r\n\nProject 'Books&Movies'\r\n\n" +
                    "Database with ability " +
                    "to record watch movies and read books.\r\n\n" +
                    "Author: Varun.g. yashwin\r\n\ngroup: VG-777.");

            dialog.setNeutralButton("OK", (dialog1, which) -> dialog1.dismiss());
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}