
import image.*;

/** represents a bullet */
public class Bullet extends Grid{
  Image img;
 public Bullet(int x, int y, boolean bool, Image img){
     super(x, y, bool);
     this.x = x;
     this.y= y;
     this.bool = bool;
     this.img = img;
 }
 public Bullet(int x, int y, boolean bool){
     super(x, y, bool);
     this.x = x;
     this.y= y;
     this.bool = bool;
     this.img = new Rectangle(5,15,"solid","red");
}
     
 
     /** draws a bullet 
      * 
      *  @param Scene scn
      * */
 public Scene draw(Scene scn){
     if(this.bool){
     return scn.placeImage(new Rectangle(5,15,"solid","blue"), this.cell2Pixel());
 }else{
     return scn.placeImage(this.img, this.cell2Pixel());
 }
 }
 
 /** moves the bullet */
 public void move(){
     if(this.bool){
     this.y = this.y+1;
     }else{
         this.y = this.y-1;
     }
 }
}