import java.awt.SystemTray;
import java.io.InvalidObjectException;

public class Gamestate{
    // Storing an instance of the gameArena:
    private GameArena gameArena;

    // Making a table:
    private Table table;

    // Score variables:
    private int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;

    // Time variables:
    private long gameDuration = 150;
    private long secondsLeft;
    private boolean isGameOver = false; 
    private Textbox timer;
    private long startTime;
    private long elapsedTime;

    // Flag to see if all of the objects have drawn onto screen:
    private boolean hasDrawn = false;

    // Flags for the state of the game:
    private boolean goToMainMenu = false;
    private boolean winnerBoxShowing = false;

    // Flag for if the game is a cheated game:
    private boolean isGameCheated = false;

    // Textbox which appears at 0 seconds left:
    private Textbox winnerBox;
    private String winnerText;

    // Textboxes for each of the player's information:
    private Textbox wasd;
    private Textbox ijkl;
    private Textbox playerOneScoreText;
    private Textbox playerTwoScoreText;

    // Class constructor: 
    public Gamestate(GameArena gameArena){

        this.gameArena = gameArena;
        table = new Table(this.gameArena);
        secondsLeft = 150;

        // Initiating the textboxes:
        wasd = new Textbox("WASD", 100, 20, 110, 60, 150, 60, 40, "GREEN", "WHITE", 1);
        ijkl = new Textbox("IJKL", 750, 20, 780, 60, 150, 60, 40, "GREEN", "WHITE", 1);
        timer = new Textbox("", 440, 10, 455, 80, 120, 80, 50, "RED", "WHITE", 1);
        playerOneScoreText = new Textbox(String.valueOf(scorePlayerOne), 400, 20, 410, 60, 40, 60, 40, "BLUE", "WHITE", 1);
        playerTwoScoreText = new Textbox(String.valueOf(scorePlayerTwo), 560, 20, 570, 60, 40, 60, 40, "BLUE", "WHITE", 1);

        initArena();
    }

    // Setter for the cheated game flag:
    public void setCheatedGame(){
        isGameCheated = true;
    }

    // Update method:
        public void update(){
        // Update the table:
        table.update(gameArena, this);

        // Check the time of the game:
        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        secondsLeft = gameDuration - elapsedTime; 

        // Edit the format of the textbox based on how many seconds are left:
        if (secondsLeft < 100){
            if (secondsLeft < 10){
                timer.setText("  "+String.valueOf(secondsLeft));
            }
            else{
                timer.setText(" "+String.valueOf(secondsLeft));
            }
        }
        else{
            timer.setText(String.valueOf(secondsLeft));
        }

        // Check if the game is over:
        if (secondsLeft <= 0){
            isGameOver = true;
        }

        // Check if the winner box has been shown for 3 seconds:
        if (secondsLeft <= -3){
            goToMainMenu = true;
        }

        // Display the winnerbox:
        if (isGameOver && !winnerBoxShowing){
            if (scorePlayerOne == scorePlayerTwo){
                winnerText = "       DRAW";
            }
            else{
                if (scorePlayerOne > scorePlayerTwo){
                    winnerText = "PLAYER 1 WINS";
                }
                else{
                    winnerText = "PLAYER 2 WINS";
                }
            }
            winnerBox = new Textbox(winnerText, 300, 200, 350, 310, 400, 200, 40, "GREEN", "WHITE", 6);
            gameArena.addRectangle(winnerBox.getRectangle());
            gameArena.addText(winnerBox.getText());
            winnerBoxShowing = true;
        }

        // Check if the players have scored:
        if (table.getPlayerOneHasScored()){
            scorePlayerOne++;
        }
        if (table.getPlayerTwoHasScored()){
            scorePlayerTwo++;
        }

        playerOneScoreText.setText(String.valueOf(scorePlayerOne));
        playerTwoScoreText.setText(String.valueOf(scorePlayerTwo));
    }
    
    public void draw(){
        table.fillThings(this.gameArena); 
        gameArena.addRectangle(wasd.getRectangle());
        gameArena.addRectangle(ijkl.getRectangle());
        gameArena.addRectangle(timer.getRectangle());
        gameArena.addText(wasd.getText());
        gameArena.addText(ijkl.getText());
        gameArena.addText(timer.getText());
        gameArena.addRectangle(playerOneScoreText.getRectangle());
        gameArena.addRectangle(playerTwoScoreText.getRectangle());
        gameArena.addText(playerOneScoreText.getText());
        gameArena.addText(playerTwoScoreText.getText());
    }

    public void resetArena(){
        gameArena.removeRectangle(wasd.getRectangle());
        gameArena.removeRectangle(ijkl.getRectangle());
        gameArena.removeRectangle(timer.getRectangle());
        gameArena.removeText(wasd.getText());
        gameArena.removeText(ijkl.getText());
        gameArena.removeText(timer.getText());
        gameArena.removeText(winnerBox.getText());
        gameArena.removeRectangle(winnerBox.getRectangle());
        gameArena.removeRectangle(playerOneScoreText.getRectangle());
        gameArena.removeRectangle(playerTwoScoreText.getRectangle());
        gameArena.removeText(playerOneScoreText.getText());
        gameArena.removeText(playerTwoScoreText.getText());
        table.unfillThings(gameArena);

        isGameOver = false;
        goToMainMenu = false;
        scorePlayerOne = scorePlayerTwo = 0;
        hasDrawn = false;
        winnerBoxShowing = false;
    }

    public void initArena(){
        startTime = System.currentTimeMillis();
    }


    public boolean getGoToMainMenu(){
        return goToMainMenu;
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public void playerOneScored(){
        scorePlayerOne++;
    }

    public void playerTwoScored(){
        scorePlayerTwo++;
    }
}