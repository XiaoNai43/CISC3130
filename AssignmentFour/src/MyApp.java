import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MyApp {

    public static final String DATA_FILE = "../data/movies.csv";
    public static final int YEAR_ZERO = 2020;
    public static final int YEAR_FIVE = 2015;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("Loading movie data.");
        Movie[] movies = loadData();
        System.out.println("==============================");

        // Data is loaded
        // Populate Dictionary.

        System.out.println("Populating dictionaries with data.");
        Dictionary<String, Genre> dictionaryByGenre = populateDictionaryByGenre(movies);
        Dictionary<String, Genre> dictionaryByYear = populateDictionaryByYear(movies);
        Dictionary<String, Genre> lastFiveYears = getLastFiveYears(dictionaryByGenre);
        System.out.println("==============================");

        printTotalData(dictionaryByGenre);
        printLastFiveYears(lastFiveYears);
        printByYearByGenre(dictionaryByYear);
    }

    @SuppressWarnings("unchecked")
    public static void printTotalData(Dictionary<String, Genre> dict) {
        System.out.println("Printing total timeframe worth of data.");
        System.out.println("==============================");
        for (Iterator i = dict.iterator(); i.hasNext();) {
            Entry<String, Genre> tmp = (Entry<String, Genre>) i.next();
            System.out.println(String.format("%s | %d", tmp.value().name(), tmp.value().movies().size()));
        }
        System.out.println("==============================");
    }

    @SuppressWarnings("unchecked")
    public static void printLastFiveYears(Dictionary<String, Genre> dict) {
        System.out.println("Printing last 5 years worth of data.");
        System.out.println("==============================");
        for (Iterator i = dict.iterator(); i.hasNext();) {
            Entry<String, Genre> tmp = (Entry<String, Genre>) i.next();
            System.out.println(String.format("%s | %d", tmp.value().name(), tmp.value().movies().size()));
        }
        System.out.println("==============================");
    }

    @SuppressWarnings("unchecked")
    public static void printByYearByGenre(Dictionary<String, Genre> dict) {
        Dictionary<String, Integer> genreCount = new Dictionary<String, Integer>();
        for (Iterator i = dict.iterator(); i.hasNext();) {
            Entry<String, Genre> tmp = (Entry<String, Genre>) i.next();
            for (Movie movie : tmp.value().movies()) {
                for (String genre : movie.genres()) {
                    if (genreCount.find(genre) != null) {
                        genreCount.find(genre).setValue(genreCount.find(genre).value() + 1);
                    } else {
                        genreCount.insert(genre, 1);
                    }
                }
            }
            printGenreCount(tmp.key(), genreCount);
            genreCount = new Dictionary<String, Integer>();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void printGenreCount(String year, Dictionary<String, Integer> genreCount) {
        System.out.println(String.format("Release Year: %s", year));
        System.out.println("===============");
        for (Iterator ii = genreCount.iterator(); ii.hasNext();) {
            Entry<String, Integer> tmp2 = (Entry<String, Integer>) ii.next();
            System.out.println(String.format("%s | %d", tmp2.key(), tmp2.value()));
        }
        System.out.println("===============");
    }

    @SuppressWarnings("unchecked")
    public static Dictionary<String, Genre> getLastFiveYears(Dictionary<String, Genre> dict) {
        Dictionary<String, Genre> retDict = new Dictionary<String, Genre>();
        for (Iterator i = dict.iterator(); i.hasNext();) {
            Entry<String, Genre> tmp = (Entry<String, Genre>) i.next();
            retDict.insert(tmp.key, new Genre(tmp.key));
        }
        for (Iterator i = dict.iterator(); i.hasNext();) {
            Entry<String, Genre> tmp = (Entry<String, Genre>) i.next();
            for (Movie movie : tmp.value.movies()) {
                if (movie.releaseYear() >= YEAR_FIVE && movie.releaseYear() <= YEAR_ZERO) {
                    retDict.find(tmp.key).value().AddMovie(movie);
                }
            }
        }
        return retDict;
    }

    public static Dictionary<String, Genre> populateDictionaryByGenre(Movie[] movies) {
        Dictionary<String, Genre> dictionary = new Dictionary<String, Genre>();

        for (Movie movie : movies) {
            for (String genre : movie.genres()) {
                if (dictionary.find(genre) != null) {
                    dictionary.find(genre).value().AddMovie(movie);
                } else {
                    Genre newGenre = new Genre(genre);
                    newGenre.AddMovie(movie);
                    dictionary.insert(newGenre.name(), newGenre);
                }
            }
        }
        return dictionary;
    }

    public static Dictionary<String, Genre> populateDictionaryByYear(Movie[] movies) {
        Dictionary<String, Genre> dictionary = new Dictionary<String, Genre>();

        for (Movie movie : movies) {
            if (dictionary.find(Integer.toString(movie.releaseYear())) != null) {
                dictionary.find(Integer.toString(movie.releaseYear())).value().AddMovie(movie);
            } else {
                Genre newGenre = new Genre(Integer.toString(movie.releaseYear()));
                newGenre.AddMovie(movie);
                dictionary.insert(newGenre.name(), newGenre);
            }
        }
        return dictionary;
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
            while (repeat) {
                try {
                    segments[ii] = segments[ii].trim();
                    char[] year = { segments[ii].charAt(segments[ii].length() - 6),
                            segments[ii].charAt(segments[ii].length() - 5),
                            segments[ii].charAt(segments[ii].length() - 4),
                            segments[ii].charAt(segments[ii].length() - 3) };
                    releaseYear = Integer.parseInt(new String(year));
                    title = segments[ii - 1] + "," + segments[ii].charAt(segments[ii].length() - 1);
                    genres = segments[ii + 1].split("\\|");
                    repeat = false;
                } catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ex) {
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
            genres = segments[2].split("\\|");
        }
        return new Movie(title, releaseYear, genres, movieId);
    }
}