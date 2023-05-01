import java.awt.Rectangle;

import javax.sql.rowset.serial.SerialStruct;

public class Puck{
    private Ball puckBody;
    private double x;
    private double y;
    private double baseVelocity = 10;
    private int puckDiameter = 30;
    private double tableFriction = 0.000001;

    private double velocityX = 0, velocityY = 0;

    public Puck(double x, double y){
        this.x = x;
        this.y = y;
        puckBody = new Ball(x, y, puckDiameter, "BLACK", 2);
    }

    public Ball getBall(){
        return puckBody;
    }

    public void updatePuck(Table table){
        puckBody.setXPosition(x);
        puckBody.setYPosition(y);

        double dx, dy; 
        Mallet malletHit = new Mallet(-10000, -10000); // if no mallets get hit;

        if (puckBody.collides(table.getMalletOne().getBall())){
            malletHit = table.getMalletOne();
        }
        if (puckBody.collides(table.getMalletTwo().getBall())){
            malletHit = table.getMalletTwo();
        }

        if (malletHit.getX() != -10000){ // if a mallet is hit:
            dx = puckBody.getXPosition() - malletHit.getBall().getXPosition();
            dy = puckBody.getYPosition() - malletHit.getBall().getYPosition();
    
            velocityX = baseVelocity * dx + 1.5 * malletHit.getVelocity();
            velocityY = baseVelocity * dy + 1.5 * malletHit.getVelocity();
        }

        // Checking against walls: 1000X800 arena with 20px thick borders
        if (x + (puckDiameter/2) >= 980){
            velocityX *= -1;
        }
        if (x - (puckDiameter/2) <= 20){
            velocityX *= -1;
        }
        if (y + (puckDiameter/2) >= 480){
            velocityY *= -1;
        }
        if (y - (puckDiameter/2) <= 20){
            velocityY *= -1;
        }

        // Moving the puck:
        if (velocityX != 0 && velocityY != 0){
            movePuck();
        }
    }
    
    public void movePuck(){
        // We need to use the ratio of x to y to move the puck. 
        // IF X = 30 AND Y = 15, X SHOULD INCREASE 2x AS MUCH AS Y
        // This will be done by 'sharing' the Velocity across the two different velocities.

        double xRatio, yRatio;
        xRatio = baseVelocity / (Math.abs(velocityX) + Math.abs(velocityY)) * velocityX;
        yRatio = baseVelocity / (Math.abs(velocityX) + Math.abs(velocityY)) * velocityY;

        x += xRatio * tableFriction;
        
        if (velocityX > 0){
            velocityX -= xRatio * tableFriction;
        }
        else{
            velocityX += xRatio * tableFriction;
        }

        y += yRatio * tableFriction;

        if (velocityY > 0){
            velocityY -= yRatio * tableFriction;
        }
        else{
            velocityY += yRatio * tableFriction;
        }
    }

        
}