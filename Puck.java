public class Puck{
    private Ball puckBody;
    private float x;
    private float y;
    private float velocity;
    private int puckDiameter = 30;

    public Puck(int x, int y){
        this.x = x;
        this.y = y;
        puckBody = new Ball(x, y, puckDiameter, "BLACK", 2);
    }

    public Ball getBall(){
        return puckBody;
    }
}