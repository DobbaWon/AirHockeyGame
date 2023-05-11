import java.awt.Rectangle;

import javax.swing.tree.VariableHeightLayoutCache;

public class Puck{
    private Ball puckBody;
    private double x;
    private double y;
    private int puckDiameter = 30;
    private boolean playerOneHasScored, playerTwoHasScored;

    private double velocity = 0.001; 
    private double velocityX, velocityY;

    private double tableFriction = 0.0001;

    public Puck(double x, double y){
        this.x = x;
        this.y = y;
        puckBody = new Ball(x, y, puckDiameter, "BLACK", 2);
    }

    public Ball getBall(){
        return puckBody;
    }

    public void updatePuck(Table table){
        //System.out.println("HERE");
        playerOneHasScored = false;
        playerTwoHasScored = false;

        puckBody.setXPosition(x);
        puckBody.setYPosition(y);

        double dx, dy; 
        boolean isMalletHit = false;
        Mallet malletHit = null;

        if (puckBody.collides(table.getMalletOne().getBall())){
            malletHit = table.getMalletOne();
            isMalletHit = true;
        }
        if (puckBody.collides(table.getMalletTwo().getBall())){
            malletHit = table.getMalletTwo();
            isMalletHit = true;
        }

        if (isMalletHit){ // if a mallet is hit:
            dx = puckBody.getXPosition() - malletHit.getBall().getXPosition();
            dy = puckBody.getYPosition() - malletHit.getBall().getYPosition();

            velocityX = dx * velocity;
            velocityY = dy * velocity;

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

        if (x <= 30 && y <= 280 && y >= 140){
            playerTwoHasScored = true;
        }

        if (x >= 970 && y <= 280 && y >= 140){
            playerOneHasScored = true;
        }

        if (velocityX > 0 || velocityY > 0){
            movePuck();
        }

        if (x > 1000 || x < 0 || y > 600 || y < 100){
            x = 500;
            y = 350;
        }
        System.out.println(velocityX + ", " + velocityY);
    }
    
    public void movePuck(){
        System.out.println("We here");
        x += velocityX;
        y += velocityY;
        
        if (velocityX != 0){
            velocityX *= (1-tableFriction);
        }

        if (velocityY != 0){
            velocityY *= (1 - tableFriction);
        }

        if (velocityX > -0.4 && velocityX <= 0.4){
            velocityX = 0;
        }
        if (velocityY > -0.4 && velocityY <= 0.4){
            velocityY = 0;
        }
    }

    public boolean getPlayerOneHasScored(){
        return playerOneHasScored;
    }

    public boolean getPlayerTwoHasScored(){
        return playerTwoHasScored;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void resetVelocity(){
        velocityX = 0;
        velocityY = 0;
    }
}