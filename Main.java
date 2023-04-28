public class Main{

    public static void main(String[] args){
        Table table = new Table();
        GameArena gameArena = new GameArena(500, 250);

        table.fillThings(gameArena);
        gameArena.run();
    }
}