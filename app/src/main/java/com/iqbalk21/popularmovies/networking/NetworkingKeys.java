package com.iqbalk21.popularmovies.networking;

/**
 * Created by Azeem on 3/7/2016.
 */
public interface NetworkingKeys {
    //URLs
    public static String URL_POPULAR_MOVIES = "http://api.themoviedb.org/3/movie/popular?";
    public static String URL_TOP_RATED_MOVIES = "http://api.themoviedb.org/3/movie/popular?";
    public static String URL_POSTER_THUMBNAIL = "http://image.tmdb.org/t/p/w185/";

    //API KEY
    public static String API_KEY = "Your key here";

    //
    public static String GET_POPULAR_MOVIES = URL_POPULAR_MOVIES + "api_key=" + API_KEY;
    public static String GET_TOP_RATED_MOVIES = URL_TOP_RATED_MOVIES + "api_key=" + API_KEY;


}
