public class Mallet{

    // Variables that describe our mallets:
    private Ball malletBody;
    private double x;
    private double y;
    private int malletDiameter = 50;
    private boolean isWASD;
    private final double malletAccelleration = 0.00005;
    private final double maxMalletVelocity = 0.015;
    private double velocityX, velocityY;
    
    // Class constructor:
    public Mallet(double x, double y, boolean isWASD){
        this.x = x;
        this.y = y;
        this.isWASD = isWASD;

        malletBody = new Ball(x, y, malletDiameter, "BLUE", 2);
    }

    // Getter for the Ball:
    public Ball getBall(){
        return malletBody;
    }

    // Getter for the Y value:
    public double getY(){
        return y;
    }

    // Getter for the X value:
    public double getX(){
        return x;
    }

    // Getter for the Velocities:
    public double getVelocityX(){
        return velocityX;
    }

    public double getVelocityY(){
        return velocityY;
    }

    // Setter for the Y value:
    public void setY(double y){
        this.y = y;
    }

    // Setter for the X value:
    public void setX(double x){
        this.x = x;
    }

    // Update method:
    public void updateMallet(GameArena gameArena){
        move(gameArena);

        // Setting the x and y for the ball object to be equal to that of the local variables:
        malletBody.setXPosition(x);
        malletBody.setYPosition(y);
    }

    public void move(GameArena gameArena){
        // Check the different controls of the mallet to see if they have been pressed:
        if (isWASD){
            // If they have been pressed, check if the max velocity has been reached, if not, add the malletAccelleration value to it:
            if (gameArena.letterPressed('W')){
                if (velocityY > -1 * maxMalletVelocity){
                    velocityY -= malletAccelleration;
                }
            }
            if (gameArena.letterPressed('A')){
                if (velocityX > -1 * maxMalletVelocity){
                    velocityX -= malletAccelleration;
                }
            }
            if (gameArena.letterPressed('S')){
                if (velocityY < maxMalletVelocity){
                    velocityY += malletAccelleration;
                }
            }
            if (gameArena.letterPressed('D')){
                if (velocityX < maxMalletVelocity){
                    velocityX += malletAccelleration;
                }
            }

            // If the mallet is the left mallet and it will still be within the playing bounds, move it:
            if (x + velocityX > 21 && x + velocityX < 499){
                x += velocityX;
            }
        }

        else{
            if (gameArena.letterPressed('I')){
                if (velocityY > -1 * maxMalletVelocity){
                    velocityY -= malletAccelleration;
                }
            }
            if (gameArena.letterPressed('J')){
                if (velocityX > -1 * maxMalletVelocity){
                    velocityX -= malletAccelleration;
                }
            }
            if (gameArena.letterPressed('K')){
                if (velocityY < maxMalletVelocity){
                    velocityY += malletAccelleration;
                }
            }
            if (gameArena.letterPressed('L')){
                if (velocityX < maxMalletVelocity){
                    velocityX += malletAccelleration;
                }
            }

            // If the mallet is the right mallet and moving it will keep it within the playing bounds, move it:
            if (x + velocityX > 501 && x + velocityX < 979){
                x += velocityX;
            }
        }

        // If moving the mallet up or down will keep it within the playing bounds, move it:
        if (y + velocityY > 121 && y + velocityY < 599){
            y += velocityY;
        }

        // Decellerate if the mallet is idle:
        if (velocityX > 0){
            velocityX -= (malletAccelleration/5);
        }

        if (velocityX < 0){
            velocityX += (malletAccelleration/5);
        }

        if (velocityY > 0){
            velocityY -= (malletAccelleration/5);
        }

        if (velocityY < 0){
            velocityY += (malletAccelleration/5);
        }
    }
}