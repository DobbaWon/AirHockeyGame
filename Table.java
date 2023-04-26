/* Table Class
 * This class stores all of the rectangles and circles required to draw the table on screen.
 * The table is white, with blue edges, 500x250px.
 * The edges are 10px wide.
 */
public class Table{
    private Rectangle body, verticalLineOne, verticalLineTwo, horizontalLineOne, horizontalLineTwo, goalOne, goalTwo, centerLineOuter, centerLineInner;
    private Ball centerCircleOutline, centerCircle;

    private int width = 500;
    private int height = 250;
    private int lineThickness = 10;
    private int centerLineThickness = 2;
    private int goalLength = 70;
    private int centerCircleDiameter = 30;

    private ArrayList<Object> things = new ArrayList<Object>();


    public Table(){
        // Rectangle = (x, y, width, height, colour, layer);
        
        body = new Rectangle(0, 0, width, height, "WHITE", 0);
        verticalLineOne = new Rectangle(0, 0, lineThickness, height, "BLUE", 1); // The Top Line
        verticalLineTwo = new Rectangle(width-lineThickness, 0, lineThickness, height, "BLUE", 1); // The Bottom Line
        horizontalLineOne = new Rectangle(0, 0, width, lineThickness, "BLUE", 1); // The Left Line
        horizontalLineTwo = new Rectangle(0, height-lineThickness, width, lineThickness, "BLUE", 1); // The Right Line
        centerLineOuter = new Rectangle(width/2, 0, centerLineThickness, height, "BLUE", 1) // The Center Line before and after the Center Circle

        centerCircleOutline = new Ball((width-centerCircleDiameter)/2, (height-centerCircleDiameter)/2, centerCircleDiameter, "BLUE", 2); // The outline of the Circle in the Center
        centerCircle = new Ball((width-(centerCircleDiameter-centerLineThickness))/2, (height-(centerCircleDiameter-centerLineThickness))/2, centerCircleDiameter-centerLineThickness, "WHITE", 3); // The Center Circle

        centerLineInner = new Rectangle(width/2, (height-centerCircleDiameter)/2, centerLineThickness, centerCircleDiameter, "BLUE", 3); // The Center Line inside the Center Circle

        // Goals are half as wide as lines. They also come infront of the border lines, so they need to be drawn after 0+lineThickness, and before Width-(1.5*lineThickness)
        goalOne = new Rectangle(lineThickness, (height-goalLength)/2, lineThickness/2, goalLength, "GREY", 1); // The Left Goal
        goalTwo = new Rectangle(width-(1.5*lineThickness), (height-goalLength)/2, lineThickness/2, goalLength, "GREY", 1); // The Right Goal

        // Adding the Things to things.
        things.add(body);
        things.add(verticalLineOne);
        things.add(verticalLineTwo);
        things.add(horizontalLineOne);
        things.add(horizontalLineTwo);
        things.add(centerLineOuter);
        things.add(centerCircleOutline);
        things.add(centerCircle);
        things.add(centerLineInner);
    }

    // A public method to return the objects in this class to be drawn in main.
    public ArrayList<Object> getThings(){
        return things;
    }

}