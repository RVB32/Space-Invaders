
import image.*;

/** represents an Alien */
public class Alien extends Grid{
    Image img;
    boolean cool;
    public Alien(int x, int y, boolean bool, Image img,boolean cool) {
        super(x , y, bool);
        this.x = x;
        this.y = y;
        this.bool = bool;
        this.img = img;
        this.bool = cool;
    }
    public Alien(int x, int y, boolean bool) {
        super(x , y, bool);
        this.x = x;
        this.y = y;
        this.bool = false;
        this.img = new FromFile("images/aliens2.jpeg");
        this.cool = false;
    }
    
    public Alien(int x, int y, boolean bool,boolean cool) {
        super(x , y, bool);
        this.x = x;
        this.y = y;
        this.bool = false;
        this.img = new FromFile("images/aliens3.jpeg");
        this.cool = cool;
    }
    
    /** moves the Alien */
    void move(){
        if(this.bool){
            this.x = this.x+1;
        }else{
            this.x = this.x-1;
        }   
    }
    
    /** draws the Alien
     * 
     *   @param Scene scn
     *  */
    public Scene draw(Scene scn){
        if(this.cool){
        return scn.placeImage(this.img, this.cell2Pixel());
    }else{
        return scn.placeImage(new FromFile("images/aliens2.jpeg"), this.cell2Pixel());
    }
    
    }
}