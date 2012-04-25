
import image.*;
import world.*;

/** represents one point in a Grid */
abstract class Grid extends Posn{
int Cell_Size = 35;
int Cell_Height = 25;
int Cell_Width = 25;

int WIDTH = this.Cell_Width * this.Cell_Size;
int HEIGHT = this.Cell_Height * this.Cell_Size;

    boolean bool;
    public Grid(int x, int y, boolean bool) {
        super(x, y);
        this.bool = bool;
        
    }
    
    /** turns a Posn into a Posn in the Grid */
    Posn cell2Pixel(){
        return new Posn(this.Cell_Size * this.x - this.Cell_Size/2,
                          this.Cell_Size * this.y - this.Cell_Size/2);
    }
    
    /** draws a scean for items that Extend Grid
     * 
     *   @param Scene scn
     *  */
   abstract Scene draw(Scene scn);
    
   /** moves items that extend Grid */
   abstract void move();
    }