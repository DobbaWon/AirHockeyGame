public class Gamestate extends State{
    private GameArena gameArena;
    private Table table;
    private int scorePlayerOne;
    private int scorePlayerTwo;
    private int secondsLeft;
    private boolean hasDrawn = false; // Don't ask...

    public Gamestate(GameArena gameArena){
        this.gameArena = gameArena;
        table = new Table(this.gameArena);
        secondsLeft = 150;
    }

     public void update(){
        table.update(gameArena);

    }
    public void draw(){
        if (!hasDrawn){
            table.fillThings(this.gameArena); 
            hasDrawn = true;
        }
    }
}