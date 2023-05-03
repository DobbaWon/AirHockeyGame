import java.io.InvalidObjectException;

public class Gamestate extends State{
    private GameArena gameArena;
    private Table table;

    private int scorePlayerOne;
    private int scorePlayerTwo;

    private long gameDuration = 150;
    private long secondsLeft = 150; // 150 per game.

    private boolean hasDrawn = false; // Don't ask...

    private Textbox timer;
    private long startTime;
    private long elapsedTime;

    private Textbox wasd;
    private Textbox ijkl;

    public Gamestate(GameArena gameArena){
        this.gameArena = gameArena;
        table = new Table(this.gameArena);
        secondsLeft = 150;

        wasd = new Textbox("WASD", 100, 20, 110, 60, 150, 60, 40, "GREEN", "WHITE", 1);
        ijkl = new Textbox("IJKL", 750, 20, 780, 60, 150, 60, 40, "GREEN", "WHITE", 1);
        timer = new Textbox("", 440, 10, 455, 80, 120, 80, 50, "RED", "WHITE", 1);

        startTime = System.currentTimeMillis();
    }

    
    public void update(){
        table.update(gameArena);
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
            hasDrawn = true;
        }
    }
}