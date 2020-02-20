import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamingArtists {
    public static void main(String[] args) throws IOException {
        List<String> data = null; 
        try {
            Path input = Paths.get("../data/global.csv");
            data = Files.readAllLines(input);

        } catch (Exception e) {
            e.printStackTrace();
        } //END try-catch

        String[][] artistsInfo = new String[data.size()][];
        int index = 0;

        for (String value : data) {
            artistsInfo[index++] = value.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        } //END for loop


        Map<Artist, Integer> artists = new HashMap<>();

        for (String[] artistInfo : artistsInfo) {
                Artist artist = new Artist(artistInfo[2]);
                Integer j = artists.get(artist);
                artists.put(artist, (j == null) ? 1 : j + 1);
            }

        List<String> top10 = artists.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue())).limit(10).map(Object::toString).collect(Collectors.toList());
        Path path = Paths.get("top10.txt");
        Files.write(path,top10);
    }// END main method
} //END class


class Artist {
    private String name;

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

    // @Override
    // public String toString() {
    //     return "Artist{" + 
    //             "name='" + name + '\'' +
    //             '}';
    // }

    @Override
    public String toString() {
        return name;
    }   
    
}