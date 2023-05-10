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

    private Mallet malletOne, malletTwo;
    private Puck puck;

    private int width = 1000;
    private int height = 500;
    private int lineThickness = 20;
    private int centerLineThickness = 4;
    private int goalLength = 140;
    private int centerCircleDiameter = 160;

    private boolean playerOneHasScored, playerTwoHasScored;

    public Table(GameArena gameArena){
        // Rectangle = (x, y, width, height, colour, layer);
        
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
        malletOne = new Mallet(200, height/2+100);
        malletTwo = new Mallet(800, height/2+100);
        
        puck = new Puck(Double.valueOf(width/2), Double.valueOf(height/2)+100);

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

    public void unfillThings(GameArena gameArena){
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

    public void update(GameArena gameArena){
        playerOneHasScored = false;
        playerTwoHasScored = false;
        malletOne.updateMallet();
        malletTwo.updateMallet();
        puck.updatePuck(this);

        if (puck.getPlayerOneHasScored()){
            playerOneHasScored = true;
            resetTable();
        }

        if (puck.getPlayerTwoHasScored()){
            playerTwoHasScored = true;
            resetTable();
        }

        if (gameArena.letterPressed('W')){
            malletOne.setY(malletOne.getY()-0.01);
        }
        if (gameArena.letterPressed('A')){
            malletOne.setX(malletOne.getX()-0.01);
        }
        if (gameArena.letterPressed('S')){
            malletOne.setY(malletOne.getY()+0.01);
        }
        if (gameArena.letterPressed('D')){
            malletOne.setX(malletOne.getX()+0.01);
        }
        if (gameArena.letterPressed('I')){
            malletTwo.setY(malletTwo.getY()-0.01);
        }
        if (gameArena.letterPressed('J')){
            malletTwo.setX(malletTwo.getX()-0.01);
        }
        if (gameArena.letterPressed('K')){
            malletTwo.setY(malletTwo.getY()+0.01);
        }
        if (gameArena.letterPressed('L')){
            malletTwo.setX(malletTwo.getX()+0.01);
        }

        if (malletOne.getX() > 500){
            malletOne.setX(499);
        }

        if (malletTwo.getX() < 500){
            malletTwo.setX(501);
        }

        if (malletOne.getY() > 580){
            malletOne.setY(579);
        }

        if (malletOne.getY() < 120){
            malletOne.setY(121);
        }

        if (malletTwo.getY() > 580){
            malletTwo.setY(579);
        }

        if (malletTwo.getY() < 120){
            malletTwo.setY(121);
        }

        System.out.println(malletOne.getX() + ", " + malletOne.getY() + " || " + malletTwo.getX() + ", " + malletTwo.getY());
    }

    public Mallet getMalletOne(){
        return malletOne;
    }

    public Mallet getMalletTwo(){
        return malletTwo;
    }

    private void resetTable(){
        if (playerOneHasScored){
            puck.setX(width/2-centerCircleDiameter/2);
        }
        else{
            puck.setX(width/2+centerCircleDiameter/2);
        }

        puck.setY(height/2);

        malletOne.setX(200);
        malletTwo.setX(800);
        malletOne.setY(height/2);
        malletTwo.setY(height/2);
    }

    public boolean getPlayerOneHasScored(){
        return playerOneHasScored;
    }

    public boolean getPlayerTwoHasScored(){
        return playerTwoHasScored;
    }
}