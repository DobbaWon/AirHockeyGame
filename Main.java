import java.awt.event.KeyListener;

public class Main implements Runnable{

    static Table table;
    static GameArena gameArena;
    public static void main(String[] args){
        Main main = new Main();

        gameArena = new GameArena(500, 250);
        table = new Table(gameArena);

        table.fillThings(gameArena); 

        Thread t = new Thread(main);

        t.start();
        gameArena.run();
    }

    public void run(){
        boolean isRunning = true;
        while (isRunning){
            table.update(gameArena);
        }
    }
}