import java.util.Comparator;

public class Song implements Comparator<Song> {
    private String track;
    private Song next;

    public Song(String track){
        this.track = track;
        this.next = null;
    }

    public Song nextSong(){
        return this.next;
    }

    public String trackName(){
        return this.track;
    }

    public void setNext(Song next){
        this.next = next;
    }

    @Override
    public int compare(Song s1, Song s2) {
        return s1.track.compareTo(s2.track);
    }
}