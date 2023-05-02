import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mallet{

    private Ball malletBody;
    private double x;
    private double y;
    private int malletDiameter = 50;
    boolean isMovingUp = false;
    boolean isMovingDown = false;
    boolean isMovingLeft = false;
    boolean isMovingRight = false;
    
    private double xSpeed = 0; 
    private double ySpeed = 0; 

    private double acceleration = 1.1;

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
        return velocity;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void updateMallet(){
        if (isMovingUp){
            if (xSpeed <= -0.5){
                xSpeed *= acceleration;
            }
            else{
                xSpeed = -0.5;
            }
        }

        if (isMovingDown){
            if (xSpeed >= 0.5){
                xSpeed *= acceleration;
            }
            else{
                xSpeed = 0.5;
            }
        }

        if (isMovingLeft){
            if (ySpeed <= -0.5){
                ySpeed *= acceleration;
            }
            else{
                ySpeed = -0.5;
            }
        }

        if (isMovingRight){
            if (ySpeed >= 0.5){
                ySpeed *= acceleration;
            }
            else{
                ySpeed = 0.5;
            }
        }

        if (xSpeed > 5){
            xSpeed = 5;
        }
        if (xSpeed < -5){
            xSpeed = -5;
        }

        if (ySpeed > 5){
            ySpeed = 5;
        }
        if (ySpeed < -5){
            ySpeed = -5;
        }

        x += xSpeed;
        y += ySpeed;

        malletBody.setXPosition(x);
        malletBody.setYPosition(y);
    }

    public void setIsMovingUp(){
        isMovingUp = true;
        isMovingDown = false;
    }
    public void setIsNotMovingUp(){
        isMovingUp = false;
    }

    public void setIsMovingDown(){
        isMovingDown = true;
        isMovingUp = false;
    }
    public void setIsNotMovingDown(){
        isMovingDown = false;
    }

    public void setIsMovingLeft(){
        isMovingLeft = true;
        isMovingRight = false;
    }
    public void setIsNotMovingLeft(){
        isMovingLeft = false;
    }
    
    public void setIsMovingRight(){
        isMovingRight = true;
        isMovingLeft = false;
    }
    public void setIsNotMovingRight(){
        isMovingRight = false;
    }

    
}