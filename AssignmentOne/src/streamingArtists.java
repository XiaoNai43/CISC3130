import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class streamingArtists {
    public static void main(String[] args) throws IOException {
        List<String> data = null;
        try {
            Path input = Paths.get("../data/global.csv");
            data = Files.readAllLines(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] artistInfo = new String[data.size()][];
        int index = 0;
        for (String value : data) {
            artistInfo[index++] = value.split(",");
        }

        System.out.println(Arrays.toString(data.toArray()));
    }  
}
