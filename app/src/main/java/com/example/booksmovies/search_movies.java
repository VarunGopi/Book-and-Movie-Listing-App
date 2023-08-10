package com.example.booksmovies;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class search_movies extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    ArrayList<String> ids, names, dates, comments, ratings;
    EditText search;
    ListView listView;
    public String[] data={"om","ddm","google","super","kantra","KGF","kabza"};
    TextView tv;

    Button serch;
    String theme;
    ConstraintLayout layout;
    RecyclerView moviesView;
    MoviesCustomAdapter customAdapter;

    ActivityResultLauncher<Intent> afterAdding = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                recreate();
            });


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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        search = findViewById(R.id.edsearch);
        serch = findViewById(R.id.smbutton);
        moviesView = findViewById(R.id.movies_view);




        serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchvalue=search.getText().toString();
                boolean elementfoud=SearchUtil.searchElement(data,searchvalue);
            if(elementfoud){
                Toast.makeText(search_movies.this, "movie found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(search_movies.this, "movie not found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            theme = bundle.getString("theme");
            if (theme.equals("sunny day")) {
                layout.setBackgroundResource(R.drawable.gk);
            } else {
                layout.setBackgroundResource(R.drawable.warmeveningbackground);
            }
        }
        dataBaseHelper = new DataBaseHelper(search_movies.this, "movies", null, 1);
        ids = new ArrayList<>();
        names = new ArrayList<>();
        dates = new ArrayList<>();
        comments = new ArrayList<>();
        ratings = new ArrayList<>();
        getData();

        customAdapter = new MoviesCustomAdapter(search_movies.this, this, ids, names, dates,
                comments, ratings);
        moviesView.setAdapter(customAdapter);

        moviesView.setLayoutManager(new LinearLayoutManager(search_movies.this));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Movies");
        }


    }
    void getData() {
        Cursor cursor = dataBaseHelper.readAll();
        if (cursor.getCount() !=0 ) {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                dates.add(cursor.getString(3));
                comments.add(cursor.getString(4));
                ratings.add(cursor.getString(5));
            }
        }
    }
    public static class  SearchUtil{
        public  static boolean searchElement(String[] elements,String searchvalue){
            for(String element:elements){
                if(element.equals(searchvalue)){
                    return  true;
                }
            }
            return false;
        }
    }




}