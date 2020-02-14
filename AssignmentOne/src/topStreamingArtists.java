import java.io.*;
import java.util.*;

public class topStreamingArtists {
    public static void main(String[] args) throws IOException{
        String [][] data = new String[203][7];
        File file = new File("../data/global.csv");

        int  track = 0;
        int artist = 0;

        BufferedReader bufRdr = new BufferedReader(new FileReader(file));
        String line = null;

        while ((line = bufRdr.readLine()) !=null && track < data.length) {
            StringTokenizer sTokenizer = new StringTokenizer(line, ",");
            while (sTokenizer.hasMoreTokens()) {
                data[track][artist] = sTokenizer.nextToken();
                artist++;
            }//END inner WHILE
            artist = 0;
            track++;
        }//END outer WHILE

        System.out.println("artist at 199" + data[200][2]);
        System.out.println("track at 199" + data[200][1]);


        String[] artistData = new String[artist];
        int[] artistCount = new int[artist];
        int[] StreamCount = new int[artist];
        int index = 0;
        int test = -1;

        for (int i = 0; i < artist; i++) {
            
        }
    }//END main


}//END class