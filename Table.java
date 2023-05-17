/* Table Class
 * This class stores all of the rectangles and circles required to draw the table on screen.
 * The table is white, with blue edges, 500x250px.
 * The edges are 10px wide.
 */
public class Table{
    // Variables for all the objects on screen:
    private Rectangle body, verticalLineOne, verticalLineTwo, horizontalLineOne, horizontalLineTwo, goalOne, goalTwo, centerLineOuter;
    private Ball centerCircleOutline, centerCircle;

    // Variables for the mallets and puck:
    private Mallet malletOne, malletTwo;
    private Puck puck;

    // Variables to describe the table:
    private int width = 1000;
    private int height = 500;
    private int lineThickness = 20;
    private int centerLineThickness = 4;
    private int goalLength = 140;
    private int centerCircleDiameter = 160;

    // Flags for whether any player has scored, passed through from the puck class:
    private boolean playerOneHasScored, playerTwoHasScored;

    // Class constructor:
    public Table(GameArena gameArena){
        // Initiating all of the objects:
        body = new Rectangle(0, 100, width, height, "WHITE", 0);
        verticalLineOne = new Rectangle(0, 100, lineThickness, height, "BLUE", 1); // The Top Line
        verticalLineTwo = new Rectangle(width-lineThickness, 100, lineThickness, height, "BLUE", 1); // The Bottom Line
        horizontalLineOne = new Rectangle(0, 100, width, lineThickness, "BLUE", 1); // The Left Line
        horizontalLineTwo = new Rectangle(0, (height-lineThickness)+100, width, lineThickness, "BLUE", 1); // The Right Line
        centerLineOuter = new Rectangle(width/2, 100, centerLineThickness, height, "BLUE", 1); // The Center Line before and after the Center Circle

        centerCircleOutline = new Ball(width/2, height/2+100, centerCircleDiameter, "BLUE", 1); // The outline of the Circle in the Center
        centerCircle = new Ball(width/2, height/2+100, centerCircleDiameter-centerLineThickness, "WHITE", 1); // The Center Circle

        // Goals are half as wide as lines. They also come infront of the border lines, so they need to be drawn after 0+lineThickness, and before Width-(1.5*lineThickness)
        goalOne = new Rectangle(lineThickness, (height-goalLength)/2+100, lineThickness/2, goalLength, "GREY", 1); // The Left Goal
        goalTwo = new Rectangle(width-(1.5*lineThickness), (height-goalLength)/2+100, lineThickness/2, goalLength, "GREY", 1); // The Right Goal

        // Mallets are controlled by the users:
        malletOne = new Mallet(200, height/2+100, true);
        malletTwo = new Mallet(800, height/2+100, false);
        
        puck = new Puck(Double.valueOf(width/2), Double.valueOf(height/2)+100);
    }

    // A public method to return the objects in this class to the Things Array.
    public void draw(GameArena gameArena){
        gameArena.addRectangle(body);
        gameArena.addRectangle(verticalLineOne);
        gameArena.addRectangle(verticalLineTwo);
        gameArena.addRectangle(horizontalLineOne);
        gameArena.addRectangle(horizontalLineTwo);
        gameArena.addRectangle(centerLineOuter);
        gameArena.addBall(centerCircleOutline);
        gameArena.addBall(centerCircle);
        gameArena.addRectangle(goalOne);
        gameArena.addRectangle(goalTwo);
        gameArena.addBall(malletOne.getBall());
        gameArena.addBall(malletTwo.getBall());
        gameArena.addBall(puck.getBall());
    }

    // A method to remove things from the game arena:
    public void unDraw(GameArena gameArena){
        gameArena.removeRectangle(body);
        gameArena.removeRectangle(verticalLineOne);
        gameArena.removeRectangle(verticalLineTwo);
        gameArena.removeRectangle(horizontalLineOne);
        gameArena.removeRectangle(horizontalLineTwo);
        gameArena.removeRectangle(centerLineOuter);
        gameArena.removeBall(centerCircleOutline);
        gameArena.removeBall(centerCircle);
        gameArena.removeRectangle(goalOne);
        gameArena.removeRectangle(goalTwo);
        gameArena.removeBall(malletOne.getBall());
        gameArena.removeBall(malletTwo.getBall());
        gameArena.removeBall(puck.getBall());
    }

    // Update method:
    public void update(GameArena gameArena, Gamestate gamestate){
        // Updating the puck and mallets:
        malletOne.updateMallet(gameArena);
        malletTwo.updateMallet(gameArena);

        puck.updatePuck(this, gameArena);

        // Checking if a player has scored:
        if (puck.getPlayerOneHasScored()){
            playerOneHasScored = true;
            resetTable(gamestate);
        }
        if (puck.getPlayerTwoHasScored()){
            playerTwoHasScored = true;
            resetTable(gamestate);
        }

        // Resetting the player scored flags:
        playerOneHasScored = false;
        playerTwoHasScored = false;
    }

    // Getter for the mallets:
    public Mallet getMalletOne(){
        return malletOne;
    }
    public Mallet getMalletTwo(){
        return malletTwo;
    }

    // Method to move the puck and mallets back to their starter location:
    private void resetTable(Gamestate gamestate){
        if (playerOneHasScored){
            puck.setX(width/2+centerCircleDiameter/2);
            gamestate.playerOneScored();
        }
        else{
            puck.setX(width/2-centerCircleDiameter/2);
            gamestate.playerTwoScored();

        }
        puck.setY(350);
        puck.resetVelocity();

        malletOne.setX(200);
        malletTwo.setX(800);
        malletOne.setY(350);
        malletTwo.setY(350);
    }

    // Getter for the playerOneHasScored flag:
    public boolean getPlayerOneHasScored(){
        return playerOneHasScored;
    }

    // Getter for the playerTwoHasScored flag:
    public boolean getPlayerTwoHasScored(){
        return playerTwoHasScored;
    }

    // Getter for the width of the table:
    public int getWidth(){
        return width;
    }

    // Getter for the height of the table:
    public int getHeight(){
        return height;
    }

    // Getter for the lineThickness of the table:
    public int getBorderSize(){
        return lineThickness;
    }

    public Puck getPuck(){
        return puck;
    }
}