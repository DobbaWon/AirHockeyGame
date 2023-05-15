
public class WinnerScreen{
    private int playerOneScore;
    private int playerTwoScore;

    private boolean draw = false;

    private Textbox winner;
    private String winnerString;

    private Rectangle background;

    private long startTime;
    private long elapsedTime;

    private boolean goToMainMenu = false;

    public WinnerScreen(int playerOneScore, int playerTwoScore, GameArena gameArena){
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;

        if (playerOneScore == playerTwoScore){
            draw = true;
        }

        startTime = System.currentTimeMillis();

        if (draw){
            winnerString = "       DRAW!";
        }
        else{
            if (playerOneScore > playerTwoScore){
                winnerString = "PLAYER 1 WINS";
            }
            else{
                winnerString = "PLAYER 2 WINS";
            }
        }

        winner = new Textbox(winnerString, 300, 200, 310, 320, 400, 200, 50, "BLUE", "WHITE", 2);
        background = new Rectangle(0, 0, 1000, 600, "GREY", 1);

        gameArena.addRectangle(winner.getRectangle());
        gameArena.addRectangle(background);
        gameArena.addText(winner.getText());
    }

    public void update(){
        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        if (elapsedTime >= 5){
            goToMainMenu = true;
        }
    }

    public boolean getGoToMainMenu(){
        return goToMainMenu;
    }

    public void newGame(GameArena gameArena){
        gameArena.removeRectangle(winner.getRectangle());
        gameArena.removeRectangle(background);
        gameArena.removeText(winner.getText());

        goToMainMenu = false;
    }
}