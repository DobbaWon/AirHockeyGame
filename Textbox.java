public class Textbox{
    private Rectangle rectangle;
    private Text text;

    public Textbox(String text, int x, int y, int textX, int textY, int width, int height,
     int fontSize, String colourOfBox, String colourOfText, int layerOfBox){
        this.rectangle = new Rectangle(x,y,width,height,colourOfBox, layerOfBox);
        this.text = new Text(text, fontSize, textX, textY, colourOfText, layerOfBox+1);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
    
    public Text getText(){
        return text;
    }

    public void setText(String newText){
        text.setText(newText);
    }
}