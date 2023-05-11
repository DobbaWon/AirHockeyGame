import java.awt.SystemTray;
import java.io.InvalidObjectException;

public class Gamestate extends State{
    private GameArena gameArena;
    private Table table;

    private int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;

    private long gameDuration = 150;
    private long secondsLeft = 150; // 150 per game.

    private boolean hasDrawn = false; // Don't ask...
    private boolean isGameOver = false; 
    private boolean goToMainMenu = false;
    private boolean winnerBoxShowing = false;
    private boolean isGameCheated = false;

    private Textbox winnerBox;
    private String winnerText;

    private Textbox timer;
    private long startTime;
    private long elapsedTime;

    private Textbox wasd;
    private Textbox ijkl;
    private Textbox playerOneScoreText;
    private Textbox playerTwoScoreText;
    
    public Gamestate(GameArena gameArena){
        this.gameArena = gameArena;
        table = new Table(this.gameArena);
        secondsLeft = 150;

        wasd = new Textbox("WASD", 100, 20, 110, 60, 150, 60, 40, "GREEN", "WHITE", 1);
        ijkl = new Textbox("IJKL", 750, 20, 780, 60, 150, 60, 40, "GREEN", "WHITE", 1);
        timer = new Textbox("", 440, 10, 455, 80, 120, 80, 50, "RED", "WHITE", 1);
        playerOneScoreText = new Textbox(String.valueOf(scorePlayerOne), 400, 20, 410, 60, 40, 60, 40, "BLUE", "WHITE", 1);
        playerTwoScoreText = new Textbox(String.valueOf(scorePlayerTwo), 560, 20, 570, 60, 40, 60, 40, "BLUE", "WHITE", 1);

    }

    public void setCheatedGame(){
        isGameCheated = true;
    }
    
    public void update(){
        table.update(gameArena, this);

        playerOneScoreText = new Textbox(String.valueOf(scorePlayerOne), 400, 20, 410, 60, 40, 60, 40, "BLUE", "WHITE", 1);
        playerTwoScoreText = new Textbox(String.valueOf(scorePlayerTwo), 560, 20, 570, 60, 40, 60, 40, "BLUE", "WHITE", 1);
        
        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        secondsLeft = gameDuration - elapsedTime; 
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

        if (secondsLeft <= 0){
            isGameOver = true;
        }

        if (secondsLeft <= -3){
            goToMainMenu = true;
        }

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

        if (table.getPlayerOneHasScored()){
            scorePlayerOne++;
        }
        if (table.getPlayerTwoHasScored()){
            scorePlayerTwo++;
        }

    }
    
    public void draw(){
        if (!hasDrawn){
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
            hasDrawn = true;
            initArena();
        }
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