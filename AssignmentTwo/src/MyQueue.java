import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyQueue extends LinkedList<Song> {
    public MyQueue(String filename){
        super();
        if(filename != null){
            BufferedReader bufRdr = null;
            try{
                List<Song> songs = new ArrayList<Song>();
                File file = new File(filename);
                FileReader fileReader = new FileReader(file);
                bufRdr = new BufferedReader(fileReader);
                String line;
                while((line=bufRdr.readLine())!=null) {  
                    songs.add(Playlist.newSong(line.split(",")[1]));
                    //this.push(Playlist.newSong(line.split(",")[1]));
                }
                bufRdr.close();
                songs.sort((s1, s2) -> s1.trackName().compareTo(s2.trackName()));
                for(Song s : songs){
                    this.push(s);
                }
            }
            catch(IOException ioex){
                if(bufRdr != null){
                    try {
                        bufRdr.close();
                    } catch (IOException ioex2) {
                        //Can't do anything more
                    }
                }
            }
        }
    }

    public void push(Song newItem){
        this.addLast(newItem);
    }

    public Song pop(){
        return this.remove();
    }

    public Song top(){
        return this.peekFirst();
    }

    /**
    * Assuming that q1 and q2 are in sorted order.
    * @param q1 : MyQueue<F>
    * @param q2 : MyQueue<F>
    */
    public static MyQueue mergeFunction(MyQueue q1, MyQueue q2){
        MyQueue merged = new MyQueue(null);
        while(!q1.isEmpty() && !q2.isEmpty()){
            int compare = q1.top().trackName().compareTo(q2.top().trackName());
            if(compare < 0){
                merged.push(q1.pop());
            }
            else if(compare > 0){
                merged.push(q2.pop());
            }
            else {
                merged.push(q1.pop());
            }
        }
        //Incase q1 is not empty, when q2 empties.
        while(!q1.isEmpty()){
            merged.push(q1.pop());
        }
        //Incase q2 is not empty, when q1 empties.
        while(!q2.isEmpty()){
            merged.push(q2.pop());
        }
        return merged;
    }
}