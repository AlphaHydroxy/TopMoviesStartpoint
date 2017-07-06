package com.codeclan.topmovieslist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FavourtiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourties);

//        got shared preference with a name FAVOURITES and made private
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

//        got a string version of our favourite ArrayList from shared preferences(SERIALIZED)
        String favouriteMovies = sharedPref.getString("MyFavourites", new ArrayList<Movie>().toString());
        Log.d("FAVOURITES", favouriteMovies);

//        use GSON to deserialize our ArrayList (Convert string format back to ArrayList)
        Gson gson = new Gson();

//        tells GSON what to convert JSON back to, in this case ArrayList
        TypeToken<ArrayList<Movie>> movieArrayList = new TypeToken<ArrayList<Movie>>(){};

//        gets JSON data and converts to ArrayList<Movie>
        ArrayList<Movie> myFavourites = gson.fromJson(favouriteMovies, movieArrayList.getType());

//         create a new favourite movie object from the ListView item
        Movie newFavouriteMovie = (Movie)getIntent().getSerializableExtra("movie");

//        add to ArrayList
        if(!myFavourites.contains(newFavouriteMovie)) {
            myFavourites.add(newFavouriteMovie);
        }
        Log.d("MY_FAVOURITES", myFavourites.toString());

//      save/serialize
        SharedPreferences.Editor editor = sharedPref.edit();

//        Convert ArrayList to JSON (String object) and save to shared preferences
        editor.putString("MyFavourites", gson.toJson(myFavourites));
        editor.apply();

        Toast.makeText(this, "Movie Added", Toast.LENGTH_SHORT).show();

        TextView list = (TextView)findViewById(R.id.favourites_list);
        String movieString = "";

        for(Movie movie : myFavourites){
            movieString += movie.getTitle() + " " + movie.getYear() + "\n";

            list.setText(movieString);
        }
    }
}
