import image.*;
import java.util.*;

/** Represents an ArrayList of X that extends Grid */
public class ListOf<X extends Grid>{
    ArrayList<X> lox;
    public ListOf(ArrayList<X> lox) {
        this.lox = lox;
    }
    
    /** draws every X in a ListOf 
     * 
     * @param Scene scn
     * 
     * */
    public Scene draw(Scene scn){
        try{
        for(X x: lox){
            scn = x.draw(scn);
        }
    }catch(java.util.ConcurrentModificationException e) {
        System.out.println(e.getMessage());        
    }
    return scn;
    }

    /** moves every X in the ListOf */
    public void move() {
        for(X x: lox){
            x.move();
        }
    }
    
    /** did a X in the ListOf hit a boundry? */
    public boolean hitWall(){
        boolean acc = false;
        int i = 0;
        for(; i <= this.lox.size()-1 && !acc; i++){
            if(this.lox.get(i).x <= 1 && !this.lox.get(i).bool || this.lox.get(i).x > 24 && this.lox.get(i).bool){
                acc = true;
            }
        }
        return acc;
    }
    
    /** did a X in the ListOf hit a boundry? */
    public boolean hitFloor(){
        boolean acc = false;
        int i = 0;
        for(; i <= this.lox.size()-1 && !acc; i++){
            if(this.lox.get(i).y == 24){
                acc = true;
            }
        }
        return acc;
    }
    
    /** did an X in the ListOf hit the blocks? */
    public boolean hitBlocks(){
        boolean acc = false;
        int i = 0;
        for(; i <= this.lox.size()-1 && !acc; i++){
            if(this.lox.get(i).y == 21){
                acc = true;
            }
        }
        return acc;
    }
    
    /** moves the aliens down a row */
    public void moveDown(){
        for(X x : lox){
        x.bool = !x.bool;
        x.y = x.y+1;
        }
    }
    
    
    /** is one of X in the ListOf off screen in the Y axis */
    public void offScreenY(){
        int i = 0;
        for(;i<=this.lox.size()-1;i++)
        if(this.lox.get(i).y <= 0||this.lox.get(i).y >= 24){
            this.lox.remove(i);
        }
    }
    
    /** is one of X in the ListOf off screen in the x axis */
    public void offScreenX(){
    int i = 0;
    for(;i<=this.lox.size()-1;i++)
    if(this.lox.get(i).x <= 0||this.lox.get(i).x >= 26){
        this.lox.remove(i);
    }
    }

    /** checks to see if an X is anywhere off Screen */
    public void offScreen(){
        this.offScreenX();
        this.offScreenY();
            
    }
    
    /** checks to see if a Bullet hit an X 
     * 
     * @param ArrayList<Bullet> Al
     * */
    public void hit(ArrayList<Bullet> Al) {
        for(int i = 0;i<Al.size();i++){
            boolean didRemove = false;
            for(int a = 0; a < this.lox.size();a++){
                
                if(!didRemove && Al.get(i).x == this.lox.get(a).x && Al.get(i).y == this.lox.get(a).y){
                    this.lox.remove(a);
                    Al.remove(i);
                    didRemove = true;
                }else{}
            }
        }
    }
    
}

