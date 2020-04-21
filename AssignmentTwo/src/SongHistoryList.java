public class SongHistoryList {
    private Song first;
    private Song previous;
    public SongHistoryList(){
        first = null;
        previous = null;
    }

    public void addSong(Song s){
        previous = first;
        first = s;
        first.setNext(previous);
    }

    public Song lastListened(){
        return first.nextSong();
    }
}