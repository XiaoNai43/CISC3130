import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyApp {

    public static final String DATA_FILE = "../data/movies.csv";

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Loading in data to Tree");
        Movie[] movies = loadData();
        MovieTree<Movie> tree = new MovieTree<Movie>();
        for (Movie movie : movies) {
            tree.add(movie);
        }

        
        String start = "Bugsy (1991)";
        String end = "Harry Potter and the Deathly Hallows: Part 2 (2011)";

        //tree.printTree();
        System.out.println("========================================");
        System.out.println(String.format("Filtering first subset - Start: %s, End: %s", start, end));
        System.out.println("========================================");
        ArrayList<Movie> sample1 = tree.subset(start, end);
        for (Movie movie : sample1) {
            System.out.println(movie.title());
        }

        start = "Ivan Vasilievich: Back to the Future (Ivan Vasilievich menyaet professiyu) (1973)";
        end = "Hulk (2003)";

        System.out.println("========================================");
        System.out.println(String.format("Filtering first subset - Start: %s, End: %s", start, end));
        System.out.println("========================================");
        ArrayList<Movie> sample2 = tree.subset(start, end);
        for (Movie movie : sample2) {
            System.out.println(movie.title());
        }

        start = "Toy Story (1995)";
        end = "WALL·E (2008)";

        System.out.println("========================================");
        System.out.println(String.format("Filtering first subset - Start: %s, End: %s", start, end));
        System.out.println("========================================");
        ArrayList<Movie> sample3 = tree.subset(start, end);
        for (Movie movie : sample3) {
            System.out.println(movie.title());
        }

        System.out.println("========================================");
    }

    public static Movie[] loadData() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        BufferedReader bufRdr = null;
        try {
            File file = new File(DATA_FILE);
            FileReader fileReader = new FileReader(file);
            bufRdr = new BufferedReader(fileReader);
            String line;
            line = bufRdr.readLine();// Skip first line
            while ((line = bufRdr.readLine()) != null) {
                Movie movie = processLine(line);
                movies.add(movie);
            }
            bufRdr.close();
        } catch (IOException ioex) {
            if (bufRdr != null) {
                try {
                    bufRdr.close();
                } catch (IOException ioex2) {
                    // Can't do anything more
                }
            }
        }
        return movies.toArray(new Movie[movies.size()]);
    }

    public static Movie processLine(String line) {
        String title = "";
        int releaseYear = 0000;
        String[] genres = new String[0];
        int movieId;

        String[] segments = line.split(",");

        movieId = Integer.parseInt(segments[0]);
        if (segments[1].charAt(0) == '"') {
            int ii = 2;
            boolean repeat = true;
            while(repeat){
                try{
                    segments[ii] = segments[ii].trim();
                    char[] year = { segments[ii].charAt(segments[ii].length() - 6), segments[ii].charAt(segments[ii].length() - 5),
                            segments[ii].charAt(segments[ii].length() - 4), segments[ii].charAt(segments[ii].length() - 3) };
                    releaseYear = Integer.parseInt(new String(year));
                    title = segments[ii-1] + "," + segments[ii].charAt(segments[ii].length() - 1);
                    genres = segments[ii+1].split("|");
                    repeat = false;
                }
                catch(NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ex){
                    ii++;
                    repeat = true;
                }
            }
        } else {
            segments[1] = segments[1].trim();
            char[] year = { segments[1].charAt(segments[1].length() - 5), segments[1].charAt(segments[1].length() - 4),
                    segments[1].charAt(segments[1].length() - 3), segments[1].charAt(segments[1].length() - 2) };
            releaseYear = Integer.parseInt(new String(year));
            title = segments[1];
            genres = segments[2].split("|");
        }
        return new Movie(title, releaseYear, genres, movieId);
    }
}