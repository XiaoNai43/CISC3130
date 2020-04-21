public class Playlist {
    private Song first;
    
    public Playlist(){
        this.first = null;
    }

    public void addSong(Song s){
        first.setNext(s);
    }

    public Song listenToSong(){
        this.first.setNext(first.nextSong());
        return this.first;
    }

    public static Song newSong(String trackname){
        return new Song(trackname);
    }
}

