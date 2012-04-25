
import image.*;

/** represents a Ship */
public class Ship extends Grid{
    Image img;
    boolean explode;
    int time;
    public Ship(int x, int y, boolean bool,Image img, int time) {
        super(x , y, bool);
        this.bool = bool;
        this.img = img;
        this.explode = false;
        this.time = time;
    }
    
    public Ship(int x, int y, boolean bool,int time) {
        super(x , y, bool);
        this.bool = bool;
        this.img = new FromFile("images/spacship3.jpeg");
        this.time = time;
    }
    
    /** draws a ship or an explofing ship
     * 
     *  @param Scene scn
     *  */
    public Scene draw(Scene scn){
        return scn.placeImage(img, this.cell2Pixel());
    }    
    
    /** moves the ship */
    void move(){
    }
    
}

