import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer; 

public class MyApp {
    public static void main(String [] args) throws IOException {
        String [] myFiles = {"../data/jpop.csv","../data/kpop.csv","../data/latin.csv"};

        ArrayList<MyQueue> allQueues = new ArrayList<>();
        for(int ii=0; ii<myFiles.length; ii++){
            MyQueue dataExtract = new MyQueue(myFiles[ii]);
            allQueues.add(dataExtract);
        }

        MyQueue merged = new MyQueue(null);
        SongHistoryList history = new SongHistoryList();
        while(!allQueues.isEmpty()){
            merged = MyQueue.mergeFunction(merged, allQueues.get(0));
            allQueues.remove(0);
        }

        Scanner kybd = new Scanner(System.in);
        boolean loop = true;
        String prompt = "Print Play[L]ist | [P]lay | [R]ecent | [S]top";
        do{
            System.out.println("Option: ");
            System.out.println(prompt);
            String input = kybd.nextLine();
            if(input.equals("L") || input.equals("l")){
                loop = true;
                System.out.println("Merged Playlist");
                merged.forEach(new Consumer<Song>() {
                    @Override
                    public void accept(Song s){
                        System.out.println(s.trackName());
                    }
                });
            }
            else if(input.equals("P") || input.equals("p")){
                loop = true;
                play(history, merged);
                prompt = "Print Play[L]ist | [N]ext | [R]ecent | [S]top";
            }
            else if(input.equals("N") || input.equals("n")){
                loop = true;
                play(history, merged);
            }
            else if(input.equals("R") || input.equals("r")){
                loop = true;
                if(history.lastListened() == null){
                    System.out.println("No song history yet.");
                }
                else{
                    System.out.println(history.lastListened().trackName());
                }
            }
            else if(input.equals("S") || input.equals("s")){
                loop = false;
            }
        }while(loop);
    }

    public static void play(SongHistoryList history, MyQueue queue){
        System.out.println(String.format("Now playing: %s", queue.top().trackName()));
        history.addSong(queue.pop());
    }
}