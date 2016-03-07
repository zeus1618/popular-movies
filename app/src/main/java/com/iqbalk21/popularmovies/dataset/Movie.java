package com.iqbalk21.popularmovies.dataset;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Picasso;

/**
 * Created by Azeem on 3/7/2016.
 */
public class Movie implements Parcelable {
    private String originalTitle,imagePath,plotSynopsis,userRating,releaseDate;

    public Movie(String originalTitle, String imagePath, String plotSynopsis, String userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.imagePath = imagePath;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "\noriginalTitle='" + originalTitle + '\'' +
                "\nimagePath='" + imagePath + '\'' +
                "\nplotSynopsis='" + plotSynopsis + '\'' +
                "\nuserRating='" + userRating + '\'' +
                "\nreleaseDate='" + releaseDate + '\'' +
                "\n}";
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalTitle);
        dest.writeString(this.imagePath);
        dest.writeString(this.plotSynopsis);
        dest.writeString(this.userRating);
        dest.writeString(this.releaseDate);
    }

    protected Movie(Parcel in) {
        this.originalTitle = in.readString();
        this.imagePath = in.readString();
        this.plotSynopsis = in.readString();
        this.userRating = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
