package com.iqbalk21.popularmovies.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqbalk21.popularmovies.DescriptionActivity;
import com.iqbalk21.popularmovies.R;
import com.iqbalk21.popularmovies.dataset.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Azeem on 3/7/2016.
 */
public class GridMoviesAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private int resource;
    private ArrayList<Movie> data;

    public GridMoviesAdapter(Context context, int resource, ArrayList<Movie> moviesList) {
        super(context, resource, moviesList);
        this.context = context;
        this.resource = resource;
        this.data = moviesList;
    }

    public void setNewGridData(ArrayList<Movie> moviesList){
        data.clear();
        data.addAll(moviesList);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        if(item==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            item = inflater.inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.image = (ImageView) item.findViewById(R.id.poster_thumb);
            holder.title = (TextView) item.findViewById(R.id.title);
            item.setTag(holder);
        }
        else{
            holder = (ViewHolder) item.getTag();
        }

        final Movie movie = data.get(position);
        holder.title.setText(movie.getOriginalTitle());
        Picasso.with(context).load(movie.getImagePath()).into(holder.image);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("moviedata",movie);
                context.startActivity(intent);
            }
        });
        return item;
    }

    static class ViewHolder{
        TextView title;
        ImageView image;
    }
}
