public class Puck{

    // Declaring the variables that describe the puck:
    private Ball puckBody;
    private double x;
    private double y;
    private int puckDiameter = 30;

    // Declaring flags to show whether a player has scored:
    private boolean playerOneHasScored, playerTwoHasScored;

    // Velocity variables for the Puck's movement:
    private double velocity = 0.0005; 
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
            deflect(x, y, malletHit.getX(), malletHit.getY(), velocityX, velocityY, malletHit.getVelocityX(), malletHit.getVelocityY());
        }

        // Checking against walls: 1000X800 arena with 20px thick borders:
        if (x + (puckDiameter/2) >= table.getWidth()-table.getBorderSize()){ // Right wall
            velocityX *= -1;
        }
        if (x - (puckDiameter/2) <= table.getBorderSize()){ // Left wall
            velocityX *= -1;
        }

        if (y + (puckDiameter/2) >= (table.getHeight()+100) - table.getBorderSize()){ // Bottom wall
            velocityY *= -1;
        }
        if (y - (puckDiameter/2) <= table.getBorderSize()+100){ // Top wall
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

        // Commenting this out makes mallets jump from corner to corner:
        System.out.println(velocityX + ", " + velocityY);
        System.out.println(x + " ," + y);
    }
    
    public void movePuck(){
        // Adding friction to the velocities:
        if (velocityX != 0){
            velocityX *= (1-tableFriction);
        }
        if (velocityY != 0){
            velocityY *= (1 - tableFriction);
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

    public void deflect(double x, double y, double x2, double y2, double velx, double vely, double velx2, double vely2)
    {
        // The position and speed of each of the two balls in the x and y axis before collision.
        // YOU NEED TO FILL THESE VALUES IN AS APPROPRIATE...
        double xPosition1, xPosition2, yPosition1, yPosition2;
        double xSpeed1, xSpeed2, ySpeed1, ySpeed2;
        xPosition1 = x;
        xPosition2 = x2;
        yPosition1 = y;
        yPosition2 = y2;
        xSpeed1 = velx;
        xSpeed2 = velx2;
        ySpeed1 = vely;
        ySpeed2 = vely2;

        // Calculate initial momentum of the balls... We assume unit mass here.
        double p1InitialMomentum = Math.sqrt(xSpeed1 * xSpeed1 + ySpeed1 * ySpeed1);
        double p2InitialMomentum = Math.sqrt(xSpeed2 * xSpeed2 + ySpeed2 * ySpeed2);
        // calculate motion vectors
        double[] p1Trajectory = {xSpeed1, ySpeed1};
        double[] p2Trajectory = {xSpeed2, ySpeed2};
        // Calculate Impact Vector
        double[] impactVector = {xPosition2 - xPosition1, yPosition2 - yPosition1};
        double[] impactVectorNorm = normalizeVector(impactVector);
        // Calculate scalar product of each trajectory and impact vector
        double p1dotImpact = Math.abs(p1Trajectory[0] * impactVectorNorm[0] + p1Trajectory[1] * impactVectorNorm[1]);
        double p2dotImpact = Math.abs(p2Trajectory[0] * impactVectorNorm[0] + p2Trajectory[1] * impactVectorNorm[1]);
        // Calculate the deflection vectors - the amount of energy transferred from one ball to the other in each axis
        double[] p1Deflect = { -impactVectorNorm[0] * p2dotImpact, -impactVectorNorm[1] * p2dotImpact };
        double[] p2Deflect = { impactVectorNorm[0] * p1dotImpact, impactVectorNorm[1] * p1dotImpact };
        // Calculate the final trajectories
        double[] p1FinalTrajectory = {p1Trajectory[0] + p1Deflect[0] - p2Deflect[0], p1Trajectory[1] + p1Deflect[1] - p2Deflect[1]};
        double[] p2FinalTrajectory = {p2Trajectory[0] + p2Deflect[0] - p1Deflect[0], p2Trajectory[1] + p2Deflect[1] - p1Deflect[1]};
        // Calculate the final energy in the system.
        double p1FinalMomentum = Math.sqrt(p1FinalTrajectory[0] * p1FinalTrajectory[0] + p1FinalTrajectory[1] * p1FinalTrajectory[1]);
        double p2FinalMomentum = Math.sqrt(p2FinalTrajectory[0] * p2FinalTrajectory[0] + p2FinalTrajectory[1] * p2FinalTrajectory[1]);

        // Scale the resultant trajectories if we've accidentally broken the laws of physics.
        double mag = (p1InitialMomentum + p2InitialMomentum) / (p1FinalMomentum + p2FinalMomentum);
        // Calculate the final x and y speed settings for the two balls after collision.
        xSpeed1 = p1FinalTrajectory[0] * mag;
        ySpeed1 = p1FinalTrajectory[1] * mag;
        xSpeed2 = p2FinalTrajectory[0] * mag;
        ySpeed2 = p2FinalTrajectory[1] * mag;
    }
    /**
     * Converts a vector into a unit vector.
     * Used by the deflect() method to calculate the resultant direction after a collision.
     */
    private double[] normalizeVector(double[] vec)
    {
        double mag = 0.0;
        int dimensions = vec.length;
        double[] result = new double[dimensions];
        for (int i=0; i < dimensions; i++)
            mag += vec[i] * vec[i];

        mag = Math.sqrt(mag);
        if (mag == 0.0){
            result[0] = 1.0;
            for (int i=1; i < dimensions; i++)
                result[i] = 0.0;
        }
        else{
            for (int i=0; i < dimensions; i++)
                result[i] = vec[i] / mag;
        }
    return result;
    }
}