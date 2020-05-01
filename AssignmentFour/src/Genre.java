import java.util.ArrayList;

public class Genre implements Comparable<Genre>{
    private String name;
    private ArrayList<Movie> movies;

    public Genre(String name){
        this.name = name;
        this.movies = new ArrayList<Movie>();
    }

    public String name(){
        return this.name;
    }

    public ArrayList<Movie> movies(){
        return this.movies;
    }

    public void AddMovie(Movie movie){
        this.movies.add(movie);
    }

    public int compareTo(Genre genre){
        return name.compareTo(genre.name());
    }
}