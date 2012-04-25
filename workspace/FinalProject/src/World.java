
import java.util.*;
import world.*;
import image.*;
import world.sound.SoundWorld;

/** represents a world
 * 
* @author Richard M Van Buren 
*         
* @since April 20, 2011 
 *  */

/** Represents the main class in the game */
public class World extends SoundWorld{
    public static void main(String[] args){

        new World().bigBang();
    }

    Random randy = new Random();

    Ship ship;
    ListOf<Alien> ListOfAlien;
    ListOf<Bullet> aliensBullets;
    ListOf<Bullet> yourBullets;
    ListOf<Block> listOfBlocks;
    ListOf<Mother> ListOfMothers;
    Music m;
    int lives;
    int time;
    int score;
    public World(Ship ship, ListOf<Alien> ala, ListOf<Bullet> AlB,ListOf<Bullet> AlB2,
            ListOf<Block> LoB,ListOf<Mother> alm, int lives, int time, int score) {
        this.ship = ship;
        this.ListOfAlien = ala;
        this.aliensBullets = AlB;
        this.listOfBlocks = LoB;
        this.lives = lives;
        this.time = time;
        this.ListOfMothers = alm;
        this.yourBullets = AlB2;
        this.score = score;
        this.m = new Music();
        this.m.setClip("sounds/Sandstormsong.wav", 0);
    }
    
    public World() {
        this.ship = new Ship(10,24,false,0);
        this.ListOfAlien = makeAliens(13,3,new ListOf<Alien>(new ArrayList<Alien>()));
        this.aliensBullets = new ListOf<Bullet>(new ArrayList<Bullet>());
        this.listOfBlocks = makeBlocks(10,2,new ListOf<Block>(new ArrayList<Block>()));
        this.lives = 2;
        this.time = 0;
        this.ListOfMothers = new ListOf<Mother>(new ArrayList<Mother>());
        this.yourBullets = new ListOf<Bullet>(new ArrayList<Bullet>());
        this.score = 0;
        this.m = new Music();
        this.m.setClip("sounds/Sandstormsong.wav", 0);
    }
    
    /** makes a list of Aliens
     * 
     *  @param inrow how many rows you want
     *  @param incol How many columns you want
     *  @param As the ListOf you want the Aliens added to
     *  */
    public ListOf<Alien> makeAliens(int inrow,int incol,ListOf<Alien> As ){
        for(int a = incol;a>=1;a=a-1){
            for(int i = inrow;i>=1;i=i-1){
                As.lox.add(new Alien(i+6,a+1,false));
            }
        }
        
        for(int w = inrow;w>=1;w=w-2){
            As.lox.add(new Alien(w+6,1,false, true));
        }
        return As; 
    }
    
    /** makes a list of Blocks
     * 
     *  @param inrow how many rows you want
     *  @param incol How many columns you want
     *  @param As the ListOf you want the Blocks added to
     *  */
    public ListOf<Block> makeBlocks(int inrow,int incol,ListOf<Block> As ){
        for(int a = incol;a>=1;a=a-1){
            for(int i = inrow;i>=1;i=i-1){
                As.lox.add(new Block(i+6,a+19,false));
            }
        }
        return As; 
    }
    
    
   
    
    /** what happens on a tick */
    public void onTick(){
        this.vieraHelper();
    }

    /** draws the world */
    public Scene onDraw(){
        Scene scn = new EmptyScene(this.ship.WIDTH,this.ship.HEIGHT).placeImage
        (new Rectangle(9999,9999,"solid", "black"), new Posn (0,0));
        scn = this.ship.draw(scn);
        scn = this.ListOfAlien.draw(scn);
        scn = this.aliensBullets.draw(scn); 
        scn = this.yourBullets.draw(scn);
        scn = this.listOfBlocks.draw(scn);
        scn = this.ListOfMothers.draw(scn);
        scn = scn.placeImage(new Text("Score: "+this.score+"",25,"blue"), new Posn(332, 870));
        scn = scn.placeImage(new Text("Lives Remaning: " + this.lives+"", 25,"blue"),new Posn(100,870));
        scn = scn.placeImage(new Text("Aliens Remaning: " +this.ListOfAlien.lox.size()+"",25,"blue"), new Posn(765, 870));

        return scn;
    }

    /** what happens when you hit a key 
     * 
     * @param ke the key that one presses on the keyboard
     * */
    public void onRelease(String ke){
        if(ke.equals("left") && (this.ship.x > 1)){
            this.ship.x = this.ship.x-1;
        }else if(ke.equals("right") && (this.ship.x <= this.ship.Cell_Width-1)){
            this.ship.x = this.ship.x+1;
        }else if(ke.equals(" ") && this.ship.bool){
            this.yourBullets.lox.add(new Bullet(this.ship.x, this.ship.y-1,false, new Rectangle(5,15,"solid","white")));
            this.yourBullets.lox.add(new Bullet(this.ship.x, this.ship.y-2,false, new Rectangle(5,15,"solid","white")));
            this.tickTunes.addNote(1, 85);
        }else if(ke.equals(" ")&& this.yourBullets.lox.size()<6){
            this.yourBullets.lox.add(new Bullet(this.ship.x, this.ship.y-1,false));
            this.tickTunes.addNote(1, 85);
        }else{
        }
    }
    
    /** checks to see if the ship is hit */
    public void shipHit(){
        int i = 0;
        for(;i<this.aliensBullets.lox.size()-1; i++){
            if(aliensBullets.lox.get(i).x == ship.x && aliensBullets.lox.get(i).y == ship.y){
                this.lives = this.lives-1;
                this.aliensBullets.lox.remove(i);
                this.ship.x = 10;
                this.score = this.score-100;
                this.m.playDie(0);
            }else{}
        }

    }

    /** adds a mother to the list of mothers */
    public void addMotherShip(){
        if (this.time%150 == 0){
            this.ListOfMothers.lox.add(new Mother(2,1,false));
        }else{
        }
    }

    /** increases the time in the world */
    public void timeup(){
        if(this.time==700000){
            this.time = 0;
        }else{
            this.time=this.time+1;
        }
    }
    

    /** adds Bullets to AlB from the alien to the attack the ship */
    public void alienShoot(){
        try{
        if(this.time%5==0){
            this.aliensBullets.lox.add(new Bullet(this.ListOfAlien.lox.get(this.randy.nextInt(this.ListOfAlien.lox.size()/2+1)).x, 
                    this.ListOfAlien.lox.get(this.ListOfAlien.lox.size()-1).y+1,
                    true));
        }else{
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /** checks if a bullet hits a Mother
     * 
     *  @param Al ArrayList<Bullet> to see if a mother has been hit
     *  */
    public void hits(ArrayList<Bullet> Al) {

        for(int i = 0;i<Al.size();i++){
            boolean didRemove = false;
            for(int a = 0; a < this.ListOfMothers.lox.size();a++){

                if(!didRemove && Al.get(i).x == this.ListOfMothers.lox.get(a).x && Al.get(i).y == this.ListOfMothers.lox.get(a).y){
                    this.ListOfMothers.lox.remove(a);
                    Al.remove(i);
                    didRemove = true;
                    this.score=this.score+250;
                    this.ship.bool = true;
                    this.m.playPapapa(0);
                }else{}
            }
        }
    }
    
    /** checks if an Alien was hit by a player's Bullet and adds points if they were
     * 
     *  @param Al ArrayList<Bullet> that is to be checked
     *  */
    public void hitPoint(ArrayList<Bullet> Al) {
        for(int i = 0;i<Al.size();i++){
            boolean didRemove = false;
            for(int a = 0; a < this.ListOfAlien.lox.size();a++){
                
                if(!didRemove && Al.get(i).x == this.ListOfAlien.lox.get(a).x && Al.get(i).y == this.ListOfAlien.lox.get(a).y){
                    this.ListOfAlien.lox.remove(a);
                    Al.remove(i);
                    didRemove = true;
                    this.score = this.score+125;
                    this.tickTunes.addNote(12, 65);
                }else{}
            }
        }
    }

    /** How long the power up lasts */
    public void shipPower(){
        if (this.ship.time==90){
            this.ship.bool = false;
            this.ship.time = 0;
        }else{
            this.ship.time = this.ship.time+1;
        }
    }
    
    /** set the tick rate */
    public double tickRate(){
        return .06;
    }

    /** did i win? */
    public boolean win(){
        if(this.ListOfAlien.lox.size() == 0){
            this.score = this.score+100;
            if(this.time>150){
                this.score = this.score+100;
            } 
            return true;
        }else{
            return false;
        }
    }

    /** When the game stops */
    public boolean stopWhen(){
        if (this.lives <= 0){
            this.m.stop();
            return true;
        }else if (this.win()){
            this.m.stop();
            return true;
        }else if(this.ListOfAlien.hitFloor()){
            this.m.stop();
            return true;
        }          
        return false;
    }

    /** displays the last two scenes */ 
    public Scene lastScene(){
        if(this.win())
        {return (new Text("You Win! Score = "+this.score+"", 69, "chartreuse")).toScene();
        }else{
            this.tickTunes.addNote(13, 65);
            return (new Text("Forever Alone!!! Score = "+this.score+"", 69, "red")).toScene();}
    }
    
    /** To help Viera can understand my code */
    public void vieraHelper(){
        this.timeup(); // increases time
        this.bulletHelper(); // calls the helper for bullets
        this.hitHelper(); // calls the helper for the Aliens
        this.addMotherShip(); // adds a Mother to the game
        this.alienMover(); // moves the aliens and shoots and checks to see if they hit something
        this.alienShoot(); // makes the aliens shoot
        if(this.ship.bool){
            // checks to see if the ship has a power up
            this.shipPower();
        }
        if(this.time%335==0){
             //plays the background music
       //    this.m.playSandStorm(0);
        }
        
    }
    
    /** Moves the bulets */
    public void bulletHelper(){
        this.aliensBullets.offScreen();
        this.yourBullets.offScreen();
        if(this.time%2==0){
            this.yourBullets.move();
        }else{
            this.aliensBullets.move();
        }
    }
    
    /** Do the Bullets hit anything? */
    public void hitHelper(){
        this.aliensBullets.hit(this.yourBullets.lox);
        this.listOfBlocks.hit(this.aliensBullets.lox);
        this.listOfBlocks.hit(this.yourBullets.lox);
        this.shipHit();
        this.hitPoint(this.yourBullets.lox);
        this.hits(this.yourBullets.lox);
    }
    
    /** Moves the Aliens and deals with hitting anything */
    public void alienMover(){
        if(this.time%4==0){
            this.ListOfMothers.move();
            if(this.ListOfAlien.hitWall()){
                this.ListOfAlien.moveDown();
            }else{
                this.ListOfAlien.move();   
            }
        }else{}
        if(this.ListOfAlien.hitBlocks()){
            this.listOfBlocks.lox.clear();
        }
    }
}



