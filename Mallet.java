import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mallet{

    private Ball malletBody;
    private int x;
    private int y;
    private int malletDiameter = 50;
    
    private boolean isWASD;

    private int velocity = 1;

    public Mallet(int x, int y, boolean isWASD){
        this.x = x;
        this.y = y;

        malletBody = new Ball(x, y, malletDiameter, "BLUE");
        
        this.isWASD = isWASD;
    }

    public Ball getBall(){
        return malletBody;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }

    public int getVelocity(){
        return velocity;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void updateMallet(){
        malletBody.setXPosition(x);
        malletBody.setYPosition(y);
    }
}