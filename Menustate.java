import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Class;
import java.lang.reflect.*;
import javax.sound.sampled.*;
import java.io.*;

public class Menustate{
    // private instance of the GameArena class:
    private GameArena gameArena;

    // Rectangles and Textboxes for different displays:
    private Rectangle background;
    private Textbox titleText;
    private Textbox playText;
    private Textbox exitText;
    private Textbox cheatBox;
    private Textbox muteBox;
    
    // Boolean flags for different states of the menu:
    private boolean isGamePlaying = false;
    private boolean isGameCheated = false;
    private boolean isGameMuted = false;

    // Path for the drumroll sfx:
    private String drumrollPath = "drumroll.wav"; 

    // Class constructor:
    public Menustate(GameArena gameArena){
        background = new Rectangle(0,0,1000,600,"CYAN", 1);
        titleText = new Textbox("Air Hockey", 400, 50, 420, 100, 200, 80, 30, "BLUE", "WHITE", 2);
        playText = new Textbox("PLAY - 'P'", 300, 200, 400, 260, 400, 80, 50, "GREEN", "WHITE", 2);
        exitText = new Textbox("EXIT - 'E'", 300, 300, 400, 360, 400, 80, 50, "RED", "WHITE", 2);
        cheatBox = new Textbox("No CheatiNg!", 300, 400, 360, 460, 400, 80, 50, "BLACK", "WHITE", 2);
        muteBox = new Textbox("Mute - 'M'", 300, 500, 380, 560, 400, 80, 50, "WHITE", "BLACK", 2);

        this.gameArena = gameArena;
        draw();
    }

    // Private method which removes all of the assets from the GameArena, and starts the Gamestate:
    private void play(){
        unDraw();
        isGamePlaying = true;
    }

    // Getter for the isGamePlaying flag:
    public boolean getIsGamePlaying(){
        return isGamePlaying;
    }

    // Private getter for all of the rectangles in this class:
    private Rectangle[] getRectangles(){
        Rectangle[] rectangles = new Rectangle[6];
        rectangles[0] = background;
        rectangles[1] = titleText.getRectangle();
        rectangles[2] = playText.getRectangle();
        rectangles[3] = exitText.getRectangle(); 
        rectangles[4] = cheatBox.getRectangle();
        rectangles[5] = muteBox.getRectangle();
        return rectangles;
    }

    // Private getter for all of the text in this class:
    private Text[] getText(){
        Text[] text = new Text[5];
        text[0] = titleText.getText();
        text[1] = playText.getText();
        text[2] = exitText.getText();
        text[3] = cheatBox.getText();
        text[4] = muteBox.getText();
        return text;
    }

    // Public method that adds all of the objects of this class to the Things array:
    public void draw(){
        Rectangle[] rectangles = getRectangles();
        Text[] text = getText();

        for (int i = 0; i < 5; i++){
            gameArena.addRectangle(rectangles[i]);
            gameArena.addText(text[i]);
        }
        gameArena.addRectangle(rectangles[5]);
    }

    // Private method of this class which removes all of the objects from the Things array:
    private void unDraw(){
        Rectangle[] rectangles = getRectangles();
        Text[] text = getText();

        for (int i = 0; i < 5; i++){
            gameArena.removeRectangle(rectangles[i]);
            gameArena.removeText(text[i]);
        }
        gameArena.removeRectangle(rectangles[5]);
    }

    // Private method called when the user presses 'E' which exits the game:
    private void exit(){
        gameArena.exit();
    }

    // Public update method which reads user input and listens for 'P', 'E', 'N', and 'M':
    public void update(){
        if (gameArena.letterPressed('P')){
            play();
            if (!isGameMuted){
                playSoundEffect(drumrollPath);
            }
        }
        if (gameArena.letterPressed('E')){
            exit();
        }
        if (gameArena.letterPressed('N')){
            isGameCheated = true;
            gameArena.removeRectangle(cheatBox.getRectangle());
            gameArena.removeText(cheatBox.getText());
        }
        if (gameArena.letterPressed('M')){
            isGameMuted = true;
            gameArena.removeRectangle(muteBox.getRectangle());
            gameArena.removeText(muteBox.getText());
        }
        
    }

    // Resetter for the flags and the objects:
    public void setNewMainMenu(){
        isGamePlaying = false;
        isGameCheated = false;
        draw();
    }

    // Getter for the isGameCheated flag:
    public boolean getIsGameCheated(){
        return isGameCheated;
    }

    // Getter for the isGameMuted flag:
    public boolean getIsGameMuted(){
        return isGameMuted;
    }

    // A public method to play a sound effect:
    public void playSoundEffect(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } 
        catch (Exception ex) {
        }
    }
}