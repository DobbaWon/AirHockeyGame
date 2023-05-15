public class Puck{

    // Declaring the variables that describe the puck:
    private Ball puckBody;
    private double x;
    private double y;
    private int puckDiameter = 30;

    // Declaring flags to show whether a player has scored:
    private boolean playerOneHasScored, playerTwoHasScored;

    // Velocity variables for the Puck's movement:
    private double velocity = 0.1; 
    private double velocityX, velocityY;
    private double tableFriction = 0.000001;

    // Constructor:
    public Puck(double x, double y){
        this.x = x;
        this.y = y;
        puckBody = new Ball(x, y, puckDiameter, "BLACK", 2);
    }

    // Getter for the ball:
    public Ball getBall(){
        return puckBody;
    }

    // Update Method:
    public void updatePuck(Table table, GameArena gameArena){
        // Resetting the score flags:
        playerOneHasScored = false;
        playerTwoHasScored = false;

        // Setting the position of the body to that of the local variables 'x' and 'y'
        puckBody.setXPosition(x);
        puckBody.setYPosition(y);

        // Checking if the puck hits a mallet, and finding its velocity based on that:
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

            // Multiplying the direction of x and y with the velocity:
            velocityX = dx * velocity;
            velocityY = dy * velocity;
        }

        // Checking against walls: 1000X800 arena with 20px thick borders:
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

        // Checking against the goals to see if the player has scored:
        if (x <= 30 && y <= 280 && y >= 140){
            playerTwoHasScored = true;
        }

        if (x >= 970 && y <= 280 && y >= 140){
            playerOneHasScored = true;
        }

        // Checking if the puck needs to be moved:
        if (velocityX > 0 || velocityY > 0){
            movePuck();
        }

        // Checking if the puck has accidentally gone out of bounds:
        if (x > 1000 || x < 0 || y > 600 || y < 100){
            x = 500;
            y = 350;
        }

        // Commenting this out makes mallets jump from corner to corner:
        System.out.println(velocityX + ", " + velocityY);
    }
    
    public void movePuck(){
        x += velocityX;
        y += velocityY;
        
        // Adding friction to the velocities:
        if (velocityX != 0){
            velocityX *= (1-tableFriction);
        }
        if (velocityY != 0){
            velocityY *= (1 - tableFriction);
        }

        // Setting them to 0 if they get low enough (they get stuck for some reason otherwise):
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