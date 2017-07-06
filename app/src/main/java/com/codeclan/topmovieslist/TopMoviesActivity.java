package com.codeclan.topmovieslist;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.transition.move;

public class TopMoviesActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list);
        TopMovies topMovies = new TopMovies();
        ArrayList<Movie> list = topMovies.getList();

        TopMoviesAdapter movieAdaptor = new TopMoviesAdapter(this, list);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(movieAdaptor);
    }

    public void getMovie(View listItem){
        Movie movie = (Movie)listItem.getTag();
        Log.d("Movie Title: ", movie.getTitle());
        Intent intent = new Intent(this, FavourtiesActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);

//        StringBuilder sb = new StringBuilder();
//        sb.append("You clicked: ");
//        sb.append(movie.getTitle());

//        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();

//        Snackbar snackbar = Snackbar.make(listItem, sb.toString(), Snackbar.LENGTH_LONG);
//        snackbar.setAction("Say hello", this);
//        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Hello from snackbar", Toast.LENGTH_SHORT).show();
    }
}
