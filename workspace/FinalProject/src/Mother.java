
import image.*;
import java.util.*;

/** Represent a Class */
public class Mother extends Grid{
  Image img;
  Mother(int x, int y, boolean bool,Image img){
      super(x,y,bool);
      this.img = img;
  }
  Mother(int x, int y, boolean bool){
      super(x,y,bool);
      this.img = new FromFile("images/sc2mothership.jpeg");
  }
  
  /** Draws a Mother onto a Scene
   * 
   * @param Scene scn
   *  */
  public Scene draw(Scene scn){
      return scn.placeImage(img, this.cell2Pixel());
  }
  
  /** Moves a Mother */
  public void move(){
      this.x = this.x+1;
  }
  
}