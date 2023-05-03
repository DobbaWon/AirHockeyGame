import java.awt.Menu;
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
                gamestate.draw();
                gamestate.update();
                System.out.println("HERE 1"); // DONT GET RID OF THIS IT MAKES THE CODE WORK SOMEHOW

                if (gamestate.getGoToMainMenu()){
                    menustate.setNewMainMenu();
                    gamestate.resetArena();
                }
            }
            else{
                menustate.update();
                System.out.println("HERE 2");
            }
        }
    }
}