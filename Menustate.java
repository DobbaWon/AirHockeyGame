import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Class;
import java.lang.reflect.*;

public class Menustate extends State implements KeyListener{
    private GameArena gameArena;
    private Rectangle background;
    private Textbox titleText;
    private Textbox playText;
    private Textbox exitText;
    private Textbox cheatBox;
    private boolean isGamePlaying = false;
    private boolean isGameCheated = false;

    private JFrame frame;
    
    public Menustate(GameArena gameArena){
        background = new Rectangle(0,0,1000,600,"CYAN", 1);
        titleText = new Textbox("Air Hockey", 400, 50, 420, 100, 200, 80, 30, "BLUE", "WHITE", 2);
        playText = new Textbox("PLAY - 'P'", 300, 200, 400, 260, 400, 80, 50, "GREEN", "WHITE", 2);
        exitText = new Textbox("EXIT - 'E'", 300, 300, 400, 360, 400, 80, 50, "RED", "WHITE", 2);
        cheatBox = new Textbox("No CheatiNg!", 300, 400, 500, 350, 400, 80, 50, "BLACK", "WHITE", 2);

        this.gameArena = gameArena;
        fillGameArena();

        frame = (JFrame) SwingUtilities.getWindowAncestor(gameArena);
        frame.addKeyListener(this);
    }

    private void play(){
        unfillGameArena();
        isGamePlaying = true;
    }

     public boolean getIsGamePlaying(){
        return isGamePlaying;
    }

    private Rectangle[] getRectangles(){
        Rectangle[] rectangles = new Rectangle[4];
        rectangles[0] = background;
        rectangles[1] = titleText.getRectangle();
        rectangles[2] = playText.getRectangle();
        rectangles[3] = exitText.getRectangle(); 
        return rectangles;
    }

    private Text[] getText(){
        Text[] text = new Text[3];
        text[0] = titleText.getText();
        text[1] = playText.getText();
        text[2] = exitText.getText();
        return text;
    }

    public void fillGameArena(){
        Rectangle[] rectangles = getRectangles();
        Text[] text = getText();

        for (int i = 0; i < 3; i++){
            gameArena.addRectangle(rectangles[i]);
            gameArena.addText(text[i]);
        }
        gameArena.addRectangle(rectangles[3]);
    }

    private void unfillGameArena(){
        Rectangle[] rectangles = getRectangles();
        Text[] text = getText();

        for (int i = 0; i < 3; i++){
            gameArena.removeRectangle(rectangles[i]);
            gameArena.removeText(text[i]);
        }
        gameArena.removeRectangle(rectangles[3]);
    }

    private void exit(){
        gameArena.exit();
    }

     public void update(){
        //frame = (JFrame) SwingUtilities.getWindowAncestor(gameArena);
    }

     public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_P){
            play();
        }
        
        if (key == KeyEvent.VK_E){
            exit();
        }

        if (key == KeyEventn.VK_N){
            isGameCheated = true;
        }
    }

     public void keyReleased(KeyEvent e){

    }

     public void keyTyped(KeyEvent e){
        
    }

    public void setNewMainMenu(){
        isGamePlaying = false;
        isGameCheated = false;
        fillGameArena();
    }

    public bool getIsGameCheated(){
        return isGameCheated;
    }
}