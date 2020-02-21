import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class streamingArtists {
    public static void main(String[] args) throws IOException {
        List<String> data = null; 
        
        //try-catch to read the input from .csv file into the data List
        try {
            Path input = Paths.get("../data/global.csv");
            data = Files.readAllLines(input);

        } catch (Exception e) {
            e.printStackTrace();
        } //END try-catch

        String[][] artistsInfo = new String[data.size()][];
        int index = 0;

        //loops through the data and and spilts it at ","
        for (String value : data) {
            //ignores the "," in the quotation marks
            artistsInfo[index++] = value.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); 
        } //END for loop

        //Creats HashMap
        Map<Artist, Integer> artists = new HashMap<>();

        //Puts the artist names into HashMap
        for (String[] artistInfo : artistsInfo) {
            Artist artist = new Artist(artistInfo[2]);
            Integer j = artists.get(artist);
            artists.put(artist, (j == null) ? 1 : j + 1);
        }

        /*
        * Creates a list topArtists
        * Sorts the list by the amount of times 
        * they apeared in the list
        */
        List<String> topArtists = artists.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue())).limit(200).
                map(Object::toString).collect(Collectors.toList());
        
        topArtists.sort(Comparator.comparing(String::toString));
        
        //Creates a new topArtists.txt file if it doesn't exsist already
        Path path = Paths.get("topGlobal.txt");
        //writes the topArtist list to the topArtists.txt file
        Files.write(path,topArtists);


    }// END main method
} //END class


class Artist {
    private String name;

    //Constructor
    Artist(String artist) {
        this.name = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) { 
            return false;
        }
        
        Artist artist = (Artist) obj;
        return name.equals(artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name.replaceAll("\"", "");
    }   
}