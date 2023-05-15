public class Main implements Runnable{
    static Menustate menustate;
    static Gamestate gamestate;
    static GameArena gameArena;

    public static void main(String[] args){
        gameArena = new GameArena(1000, 600);
        menustate = new Menustate(gameArena);
        gamestate = new Gamestate(gameArena);

        Main main = new Main();
        Thread t = new Thread(main);
        t.start();
        
        gameArena.run();
    }
    
    public void run(){
        boolean isRunning = true;
        boolean newGame = true; // flag for if the game hasn't been initiated yet;
        while (isRunning){
            // If we are currently playing the game:
            if (menustate.getIsGamePlaying()){
                if (newGame){
                    gamestate = new Gamestate(gameArena);
                    gamestate.draw();
                    newGame = false;
                }
                // If the user has chosen to cheat the game:
                if (menustate.getIsGameCheated()){
                    gamestate.setCheatedGame();
                }

                gamestate.update();
                //gameArena.pause();

                // If we need to change to main menu again (game is over):
                if (gamestate.getGoToMainMenu()){
                    menustate.setNewMainMenu();
                    gamestate.resetArena();
                    newGame = true;
                }
            }

            // If we are in the menu:
            else{
                menustate.update();
                // Commenting this out makes the menu not update:
                //System.out.println("here");
                gameArena.pause();
                // However having this statement below allows the game to run without printing anything:
            }
        }
    }
}