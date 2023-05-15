public class Mallet{

    // Variables that describe our mallets:
    private Ball malletBody;
    private double x;
    private double y;
    private int malletDiameter = 50;
    
    // Class constructor:
    public Mallet(double x, double y){
        this.x = x;
        this.y = y;

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

    // Getter for the Velocity:
    public double getVelocity(){
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
    public void updateMallet(){
        // Setting the x and y for the ball object to be equal to that of the local variables:
        malletBody.setXPosition(x);
        malletBody.setYPosition(y);
    }
}