import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mallet{

    private Ball malletBody;
    private double x;
    private double y;
    private int malletDiameter = 50;
    
    public Mallet(double x, double y){
        this.x = x;
        this.y = y;

        malletBody = new Ball(x, y, malletDiameter, "BLUE", 2);
    }

    public Ball getBall(){
        return malletBody;
    }

    public double getY(){
        return y;
    }

    public double getX(){
        return x;
    }

    public double getVelocity(){
        return 1;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void updateMallet(){
        malletBody.setXPosition(x);
        malletBody.setYPosition(y);
    }
}