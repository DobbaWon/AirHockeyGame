public class Mallet{

    // Variables that describe our mallets:
    private Ball malletBody;
    private double x;
    private double y;
    private int malletDiameter = 50;
    private boolean isWASD;
    private final double malletSpeed = 0.00005;
    private final double maxMalletVelocity = 0.0005;
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
        return 1;
    }

    public double getVelocityY(){
        return 1;
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
        if (isWASD){
            if (gameArena.letterPressed('W')){
                if (velocityY > -1 * maxMalletVelocity){
                    velocityY += malletSpeed;
                }

                if (y > 121){
                    y -= velocityY;
                }
            }
            if (gameArena.letterPressed('A')){
                if (x > 21){
                    x -= malletSpeed;
                }
            }
            if (gameArena.letterPressed('S')){
                if (y < 579){
                    y += malletSpeed;
                }
            }
            if (gameArena.letterPressed('D')){
                if (x < 499){
                    x += malletSpeed;
                }
            }
        }
        else{
            if (gameArena.letterPressed('I')){
                if (velocityY > -1 * maxMalletVelocity){
                    velocityY += malletSpeed;
                }

                if (y > 121){
                    y -= velocityY;
                }
            }
            if (gameArena.letterPressed('J')){
                if (x > 501){
                    x -= malletSpeed;
                }
            }
            if (gameArena.letterPressed('K')){
                if (y < 579){
                    y += malletSpeed;
                }
            }
            if (gameArena.letterPressed('L')){
                if (x < 979){
                    x += malletSpeed;
                }
            }
        }
    }
}