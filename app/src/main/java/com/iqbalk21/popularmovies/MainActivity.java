package com.iqbalk21.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.iqbalk21.popularmovies.adapters.GridMoviesAdapter;
import com.iqbalk21.popularmovies.dataset.Movie;
import com.iqbalk21.popularmovies.networking.GetMoviesAsync;
import com.iqbalk21.popularmovies.networking.NetworkingKeys;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridMoviesCallback{

    private static final String TAG = "MainActivity";
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private GridView gridView;
    private GridMoviesAdapter moviesAdapter;
    private GetMoviesAsync getPopularMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPopularMovies = new GetMoviesAsync(MainActivity.this, NetworkingKeys.GET_POPULAR_MOVIES,this);
        getPopularMovies.execute();

        gridView = (GridView) findViewById(R.id.grid_view);
        moviesAdapter = new GridMoviesAdapter(this,R.layout.item_grid_movie,moviesList);
        gridView.setAdapter(moviesAdapter);
    }

    @Override
    public void gridMoviesLoadDone() {
        Log.d(TAG,"gridMoviesLoadDone");
        moviesAdapter.setNewGridData(getPopularMovies.getMoviesList());
        //moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popular:
                break;
            case R.id.top:
                break;
        }
        return true;
    }


}
