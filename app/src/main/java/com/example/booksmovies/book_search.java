package com.example.booksmovies;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class book_search extends AppCompatActivity {
    BooksCustomAdapter customAdapter;
    DataBaseHelper dataBaseHelper;
    ArrayList<String> ids, names, authors, dates, comments, ratings;
    EditText bookin;
    public String[] data={"wings of fire","the god of small things","malgudi days","parva","malegali madumagalu","bhagavdhgeta","kavirajamarga"};
    RecyclerView booksView;
    ConstraintLayout layout;
    Button seah;



    ActivityResultLauncher<Intent> afterAdding = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                recreate();
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        bookin=findViewById(R.id.editext1);
        booksView=findViewById(R.id.books_view);
        seah=findViewById(R.id.booksearch);

        dataBaseHelper = new DataBaseHelper(book_search.this, "books", null, 1);
        ids = new ArrayList<>();
        names = new ArrayList<>();
        authors = new ArrayList<>();
        dates = new ArrayList<>();
        comments = new ArrayList<>();
        ratings = new ArrayList<>();
        getData();

        customAdapter = new BooksCustomAdapter(book_search.this, this, ids, names,
                authors, dates, comments, ratings);
        booksView.setAdapter(customAdapter);
        booksView.setLayoutManager(new LinearLayoutManager(book_search.this));
        seah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchvalue=bookin.getText().toString();
                boolean elementfoud= search_movies.SearchUtil.searchElement(data,searchvalue);
                if(elementfoud){
                    Toast.makeText(book_search.this, "book found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(book_search.this, "book not found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Books");
        }
    }
    void getData() {
        Cursor cursor = dataBaseHelper.readAll();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                authors.add(cursor.getString(2));
                dates.add(cursor.getString(3));
                comments.add(cursor.getString(4));
                ratings.add(cursor.getString(5));
                }

        }
    }

}