public class Main implements Runnable{
    // Initiate variables for the states and GameArena:
    static Menustate menustate;
    static Gamestate gamestate;
    static GameArena gameArena;

    public static void main(String[] args){
        // Instantiate the above variables:
        gameArena = new GameArena(1000, 600);
        menustate = new Menustate(gameArena);
        // Make a new thread for the run() method:
        Main main = new Main();
        Thread t = new Thread(main);
        t.start();
        
        // Run the GameArena to draw things and detect user input:
        gameArena.run();
    }
    
    public void run(){
        boolean isRunning = true; // flag for if the gameArena is active (main loop flag);
        boolean newGame = true; // flag for if the game hasn't been initiated yet;
        while (isRunning){
            // If we are currently playing the game:
            if (menustate.getIsGamePlaying()){
                // First, we need to check if we have made the game yet, and if not, then initiate the gamestate and draw it.
                if (newGame){ // If we haven't made the game yet:
                    gamestate = new Gamestate(gameArena);

                    // If the user has chosen to cheat the game:
                    if (menustate.getIsGameCheated()){
                        gamestate.setCheatedGame();
                    }

                    gamestate.draw();
                    newGame = false;
                }

                gamestate.update();
                //gameArena.pause();

                // If we need to change to main menu again (game is over):
                if (gamestate.getGoToMainMenu()){
                    menustate.setNewMainMenu();
                    gamestate.unDraw();
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