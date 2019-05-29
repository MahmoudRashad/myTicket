package com.example.myticket.Model.MovieModel;

public class MovieDetails {
    private String movieName;
    private String movieImage;
    private String rate;
    private String numberReviews;

    public MovieDetails(String movieName, String movieImage, String rate, String numberReviews) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.rate = rate;
        this.numberReviews = numberReviews;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNumberReviews() {
        return numberReviews;
    }

    public void setNumberReviews(String numberReviews) {
        this.numberReviews = numberReviews;
    }
}
