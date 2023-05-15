public class Textbox{
    private Rectangle rectangle; // The box
    private Text text; // The text

    public Textbox(String text, int x, int y, int textX, int textY, int width, int height,
     int fontSize, String colourOfBox, String colourOfText, int layerOfBox){
        this.rectangle = new Rectangle(x,y,width,height,colourOfBox, layerOfBox);
        this.text = new Text(text, fontSize, textX, textY, colourOfText, layerOfBox+1);
    }

    // Getter for the Rectangle:
    public Rectangle getRectangle(){
        return rectangle;
    }
    
    // Getter for the Text:
    public Text getText(){
        return text;
    }

    // Setter for the Text:
    public void setText(String newText){
        text.setText(newText);
    }
}