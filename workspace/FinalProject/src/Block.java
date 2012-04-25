
import image.*;

/** Represents a Block */
public class Block extends Grid{
    Image img;
    public Block(int x, int y, boolean bool, Image img) {
        super(x,y,bool);
        this.x = x;
        this.y = y;
        this.bool = false;
        this.img = img;
    }
    
    public Block(int x, int y, boolean bool) {
        super(x,y,bool);
        this.x = x;
        this.y = y;
        this.bool = false;
        this.img = new FromFile("images/asteroids.jpeg");
}
    
    /** Moves a block, but blocks are never moved */
    public void move(){
    }
    
    /** Draws a Block onto a Scene
     * 
     *   @param Scene scn
     *  */
    public Scene draw(Scene scn){
        return scn.placeImage(img, this.cell2Pixel());
    }
    
}
