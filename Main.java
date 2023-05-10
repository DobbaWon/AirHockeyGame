import java.awt.Menu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements Runnable{

    static Menustate menustate;
    static Gamestate gamestate;
    static GameArena gameArena;

    public static void main(String[] args){
        Main main = new Main();
        gameArena = new GameArena(1000, 600);
        menustate = new Menustate(gameArena);
        gamestate = new Gamestate(gameArena);

        Thread t = new Thread(main);
        t.start();

        gameArena.run();
    }

    public void run(){
        boolean isRunning = true;
        while (isRunning){
            if (menustate.getIsGamePlaying()){
                if (menustate.getIsGameCheated()){
                    gamestate.setCheatedGame();
                }
                
                gamestate.draw();
                gamestate.update();
                if (gamestate.getGoToMainMenu()){
                    menustate.setNewMainMenu();
                    gamestate.resetArena();
                }
            }
            else{
                menustate.update();
                // DONT GET RID OF THIS PRINT; CODE BREAKS.
                System.out.print("");
            }
        }
    }
}