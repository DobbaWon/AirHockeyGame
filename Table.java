/* Table Class
 * This class stores all of the rectangles and circles required to draw the table on screen.
 * The table is white, with blue edges, 500x250px.
 * The edges are 10px wide.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Class;
import java.lang.reflect.*;


public class Table{
    private Rectangle body, verticalLineOne, verticalLineTwo, horizontalLineOne, horizontalLineTwo, goalOne, goalTwo, centerLineOuter;
    private Ball centerCircleOutline, centerCircle;

    private int width = 500;
    private int height = 250;
    private int lineThickness = 10;
    private int centerLineThickness = 2;
    private int goalLength = 70;
    private int centerCircleDiameter = 30;

    public Table(){
        // Rectangle = (x, y, width, height, colour, layer);
        
        body = new Rectangle(0, 0, width, height, "WHITE", 0);
        verticalLineOne = new Rectangle(0, 0, lineThickness, height, "BLUE", 1); // The Top Line
        verticalLineTwo = new Rectangle(width-lineThickness, 0, lineThickness, height, "BLUE", 1); // The Bottom Line
        horizontalLineOne = new Rectangle(0, 0, width, lineThickness, "BLUE", 1); // The Left Line
        horizontalLineTwo = new Rectangle(0, height-lineThickness, width, lineThickness, "BLUE", 1); // The Right Line
        centerLineOuter = new Rectangle(width/2, 0, centerLineThickness, height, "BLUE", 1); // The Center Line before and after the Center Circle

        centerCircleOutline = new Ball(width/2, height/2, centerCircleDiameter, "BLUE", 2); // The outline of the Circle in the Center
        centerCircle = new Ball(width/2, height/2, centerCircleDiameter-centerLineThickness, "WHITE", 3); // The Center Circle

        // Goals are half as wide as lines. They also come infront of the border lines, so they need to be drawn after 0+lineThickness, and before Width-(1.5*lineThickness)
        goalOne = new Rectangle(lineThickness, (height-goalLength)/2, lineThickness/2, goalLength, "GREY", 4); // The Left Goal
        goalTwo = new Rectangle(width-(1.5*lineThickness), (height-goalLength)/2, lineThickness/2, goalLength, "GREY", 4); // The Right Goal

        // Adding the Things to things.

    }

    // A public method to return the objects in this class to the Things Array.
    public void fillThings(GameArena gameArena){
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
        
    }

}