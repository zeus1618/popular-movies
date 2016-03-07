package com.iqbalk21.popularmovies.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.iqbalk21.popularmovies.GridMoviesCallback;
import com.iqbalk21.popularmovies.dataset.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Azeem on 3/7/2016.
 */
public class GetMoviesAsync extends AsyncTask<Void,Void,ArrayList<Movie>> {

    private static final String TAG = "GetMoviesAsync";
    private Context context;
    private String urlString;
    private ProgressDialog dialog;
    private ArrayList<Movie> moviesList;
    private GridMoviesCallback callback;

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public GetMoviesAsync(Context context, String urlString, GridMoviesCallback callback) {
        this.context = context;
        this.urlString = urlString;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("please wait...");
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... voids) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();

                while ((line=br.readLine())!=null){
                    response.append(line);
                }
                br.close();
                return createMovieList(String.valueOf(response));
            }
            else
                return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        this.moviesList = movies;
        dialog.dismiss();
        callback.gridMoviesLoadDone();
    }

    private ArrayList<Movie> createMovieList(String response) throws JSONException {
        JSONArray moviesJA = new JSONObject(response).getJSONArray("results");
        JSONObject movieJO = null;
        ArrayList<Movie> moviesList = new ArrayList<>();
        for (int i=0; i<moviesJA.length(); i++){
            movieJO = moviesJA.getJSONObject(i);
            Log.d(TAG,"createMovieList : movie : " + movieJO);
            String title = movieJO.getString("original_title");
            String imagePath = NetworkingKeys.URL_POSTER_THUMBNAIL + movieJO.getString("poster_path");
            String synoposis = movieJO.getString("overview");
            String userRating = movieJO.getString("vote_average");
            String releaseDate = movieJO.getString("release_date");

            moviesList.add(new Movie(title,imagePath,synoposis,userRating,releaseDate));
        }
        return moviesList;
    }
}
