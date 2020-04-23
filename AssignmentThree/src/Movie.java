
public class Movie implements Comparison {
    private String title;
    private int releaseYear;
    private String[] genres;
    private int movieId;

    public Movie(String title, int releaseYear, String[] genres, int movieId){
        this.setTitle(title);
        this.setReleaseYear(releaseYear);
        this.setGenres(genres);
        this.setMovieId(movieId);
    }

    public String title(){return this.title;}
    public int releaseYear(){return this.releaseYear;}
    public String[] genres(){return this.genres;}
    public int movieId(){return this.movieId;}
    @Override
    public String valueString(){return this.title();}

    public void setTitle(String title){
        this.title = title;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    }

    public void setGenres(String [] genres){
        this.genres = genres.clone();//Deep Copy
    }

    public void setMovieId(int movieId){
        this.movieId = movieId;
    }
}