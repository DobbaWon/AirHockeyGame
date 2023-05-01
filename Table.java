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


public class Table extends JPanel implements KeyListener{
    private Rectangle body, verticalLineOne, verticalLineTwo, horizontalLineOne, horizontalLineTwo, goalOne, goalTwo, centerLineOuter;
    private Ball centerCircleOutline, centerCircle;

    private Mallet malletOne, malletTwo;
    private Puck puck;

    private int width = 1000;
    private int height = 500;
    private int lineThickness = 20;
    private int centerLineThickness = 4;
    private int goalLength = 140;
    private int centerCircleDiameter = 160;

    private JFrame frame;

    public Table(GameArena gameArena){
        // Rectangle = (x, y, width, height, colour, layer);
        
        body = new Rectangle(0, 0, width, height, "WHITE", 0);
        verticalLineOne = new Rectangle(0, 0, lineThickness, height, "BLUE", 1); // The Top Line
        verticalLineTwo = new Rectangle(width-lineThickness, 0, lineThickness, height, "BLUE", 1); // The Bottom Line
        horizontalLineOne = new Rectangle(0, 0, width, lineThickness, "BLUE", 1); // The Left Line
        horizontalLineTwo = new Rectangle(0, height-lineThickness, width, lineThickness, "BLUE", 1); // The Right Line
        centerLineOuter = new Rectangle(width/2, 0, centerLineThickness, height, "BLUE", 1); // The Center Line before and after the Center Circle

        centerCircleOutline = new Ball(width/2, height/2, centerCircleDiameter, "BLUE", 1); // The outline of the Circle in the Center
        centerCircle = new Ball(width/2, height/2, centerCircleDiameter-centerLineThickness, "WHITE", 1); // The Center Circle

        // Goals are half as wide as lines. They also come infront of the border lines, so they need to be drawn after 0+lineThickness, and before Width-(1.5*lineThickness)
        goalOne = new Rectangle(lineThickness, (height-goalLength)/2, lineThickness/2, goalLength, "GREY", 1); // The Left Goal
        goalTwo = new Rectangle(width-(1.5*lineThickness), (height-goalLength)/2, lineThickness/2, goalLength, "GREY", 1); // The Right Goal


        // Mallets are controlled by the users:
        malletOne = new Mallet(200, height/2);
        malletTwo = new Mallet(800, height/2);
        
        puck = new Puck(Double.valueOf(width/2), Double.valueOf(height/2));

        frame = (JFrame) SwingUtilities.getWindowAncestor(gameArena);
        frame.addKeyListener(this);
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
        gameArena.addBall(malletOne.getBall());
        gameArena.addBall(malletTwo.getBall());
        gameArena.addBall(puck.getBall());
    }

    public void update(GameArena gameArena){
        frame = (JFrame) SwingUtilities.getWindowAncestor(gameArena);
        malletOne.updateMallet();
        malletTwo.updateMallet();
        puck.updatePuck(this);
    }

    @Override public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W){
            malletOne.setY(malletOne.getY() - malletOne.getVelocity());
        }
        
        if (key == KeyEvent.VK_A){
            malletOne.setX(malletOne.getX() - malletOne.getVelocity());
        }

        if (key == KeyEvent.VK_S){
            malletOne.setY(malletOne.getY() + malletOne.getVelocity());
        }

        if (key == KeyEvent.VK_D){
            malletOne.setX(malletOne.getX() + malletOne.getVelocity());
        }

        if (key == KeyEvent.VK_I){
            malletTwo.setY(malletTwo.getY() - malletTwo.getVelocity());
        }
        
        if (key == KeyEvent.VK_J){
            malletTwo.setX(malletTwo.getX() - malletTwo.getVelocity());

        }

        if (key == KeyEvent.VK_K){
            malletTwo.setY(malletTwo.getY() + malletTwo.getVelocity());
        }

        if (key == KeyEvent.VK_L){
            malletTwo.setX(malletTwo.getX() + malletTwo.getVelocity());
        }
    }

    @Override public void keyReleased(KeyEvent e){

    }

    @Override public void keyTyped(KeyEvent e){
        
    }

    public Mallet getMalletOne(){
        return malletOne;
    }

    public Mallet getMalletTwo(){
        return malletTwo;
    }

}