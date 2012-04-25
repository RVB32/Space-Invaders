
import java.util.*;

import tester.*;
import world.*;
import image.*;

class SpaceExamples{
    
    Ship USA = new Ship(10,24,false,0);
    Ship USSR = new Ship(1,1,false,0);
    Ship UK = new Ship(19,13,false,0);
    
    Alien A1 = new Alien(7,2,false); 
    Alien A2 = new Alien(19,2,false);
    Alien A3 = new Alien(18,2,false);
    Alien B1 = new Alien(7,3,false);
    Alien B2 = new Alien(19,3,false);
    Alien B3 = new Alien(18,3,false);
    Alien C2 = new Alien(18,1,false, true);
    
    Block L1 = new Block(3,22,false);
    Block L2 = new Block(3,21,false);
    Block L9 = new Block(9,11,true);
    
    Mother M1 = new Mother(2,1,false);
    Mother M2 = new Mother(3,1,false);
    
    Bullet white = new Bullet(10,23,false, new Rectangle(5,15,"solid","white"));
    Bullet blue = new Bullet(10,23,true, new Rectangle(5,4,"solid","blue"));
    Bullet red = new Bullet(10,23,false);
    
    ListOf<Alien> As = new ListOf<Alien>(new ArrayList<Alien>());
    ListOf<Alien> As2 = new ListOf<Alien>(new ArrayList<Alien>());
    
    ListOf<Bullet> Bf = new ListOf<Bullet>(new ArrayList<Bullet>());
    ListOf<Bullet> Bt = new ListOf<Bullet>(new ArrayList<Bullet>());

    ListOf<Block> Bl = new ListOf<Block>(new ArrayList<Block>());
    
    ListOf<Mother> Ms = new ListOf<Mother>(new ArrayList<Mother>());
    
    World w1 = new World(USA, As, Bf, Bt, Bl, Ms, 2, 150, 0);
    World w2 = new World(USSR, As2, Bf, Bt, Bl, Ms, 5, 15, 100);
    World w3 = new World(USA, As, Bf, Bt, Bl, Ms, 0, 150, 0);
    
    void resetAll() {
        this.As.lox.clear();
        this.Bf.lox.clear();
        this.Bt.lox.clear();
        this.Bl.lox.clear();
        this.Ms.lox.clear();
        
        this.As.lox.add(A1); this.As.lox.add(A2); this.As.lox.add(B1); this.As.lox.add(B2); 
        this.As.lox.add(A3); this.As.lox.add(B3); this.As.lox.add(C2);
        
        this.Bl.lox.add(L1); this.Bl.lox.add(L2);
    }
    
    // Tests the Grid class methods
    boolean testCell2Pixel(Tester t){
        return (t.checkExpect(USA.cell2Pixel(), new Posn(333,823)))&&
               (t.checkExpect(USSR.cell2Pixel(), new Posn(18,18)))&&
               (t.checkExpect(UK.cell2Pixel(), new Posn(648,438)));
    }
    
    
    public void moveAs(){
        this.As.move();
    }
  //here i am just testing the draw function
    boolean testAlienDraw(Tester t){
        resetAll();
        return (t.checkExpect(this.A1.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens2.jpeg"), this.A1.cell2Pixel()))&&
         (t.checkExpect(this.B2.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens2.jpeg"), this.B2.cell2Pixel()))&&
         (t.checkExpect(this.C2.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens3.jpeg"), this.C2.cell2Pixel()))&&
         (t.checkExpect(this.A3.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens2.jpeg"), this.A3.cell2Pixel()))))));
    }
 // here i am testing the moving function by moving the image then drawing it
    boolean testAlienMoveDraw(Tester t){
        resetAll();
        moveAs();
        return (t.checkExpect(this.A1.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens2.jpeg"), new Alien(6,2,false).cell2Pixel()))&&
         (t.checkExpect(this.B2.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens2.jpeg"), new Alien(18,3,false).cell2Pixel()))&&
         (t.checkExpect(this.C2.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens3.jpeg"), new Alien(17,1,false,true).cell2Pixel()))&&
         (t.checkExpect(this.A3.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/aliens2.jpeg"), new Alien(17,2,false).cell2Pixel()))))));
    }
    // the move function for bullets is not tested, because it is never used and is 
    //   only there because grid, which it extends needs it to exist
    
    // Tests the draw function for Blocks
    boolean testBlockDraw(Tester t){
        resetAll();
        return (t.checkExpect(this.L1.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/asteroids.jpeg"), this.L1.cell2Pixel()))&&
               (t.checkExpect(this.L9.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/asteroids.jpeg"), this.L9.cell2Pixel()))&&
               (t.checkExpect(this.L2.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/asteroids.jpeg"), this.L2.cell2Pixel())))));
    }
    // Testing the draw function of the Ship class
    boolean testShipDraw(Tester t){
        resetAll();
        return (t.checkExpect(this.USA.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/spacship3.jpeg"), this.USA.cell2Pixel()))&&
         (t.checkExpect(this.USSR.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/spacship3.jpeg"), this.USSR.cell2Pixel()))&&
         (t.checkExpect(this.UK.draw(new EmptyScene(200,200)), new EmptyScene(200,200).placeImage(new FromFile("images/spacship3.jpeg"), this.UK.cell2Pixel())))));
    }
    
    //Test the draw function of the ListOf class
    boolean testDrawListOf(Tester t){
        return (t.checkExpect(this.Bl.draw(new EmptyScene(9000,9000)), 
                L2.draw(L1.draw(new EmptyScene(9000,9000)))));
    }
    
    //Test the move function of the ListOf class
    boolean testMoveDrawListOf(Tester t){
        resetAll();
        this.As.move();
        return (t.checkExpect(this.As.draw(new EmptyScene(9000,9000)),
                this.C2.draw(this.B3.draw(this.A3.draw(this.B2.draw(
                             this.B1.draw(this.A2.draw(this.A1.draw(new EmptyScene(9000,9000))))))))));
    }
    
    //Test the draw function of the Bullet class
    boolean testDrawBullet(Tester t){
        Scene mt = new EmptyScene(9000,9000);
        return (t.checkExpect(this.blue.draw(mt),
                mt.placeImage(new Rectangle(5,15,"solid","blue"), blue.cell2Pixel())))&&
               (t.checkExpect(this.white.draw(mt),
                mt.placeImage(white.img, white.cell2Pixel())));
    }
    
    //Test the move function of the Bullet class
    boolean testMoveDrawBullet(Tester t){
        Scene mt = new EmptyScene(9000,9000);
        blue.move();
        white.move();
        return (t.checkExpect(this.blue.draw(mt),
                mt.placeImage(new Rectangle(5,15,"solid","blue"), blue.cell2Pixel())))&&
               (t.checkExpect(this.white.draw(mt),
                mt.placeImage(white.img, white.cell2Pixel())));
    }
    //Test the draw function of the Mother class
    boolean testDrawMother(Tester t){
        Scene mt = new EmptyScene(900, 900);
        return ((t.checkExpect(M1.draw(mt), mt.placeImage(M1.img, M1.cell2Pixel())))&&
                (t.checkExpect(M2.draw(mt), mt.placeImage(M2.img, M2.cell2Pixel()))));
    }
    
    //Test the move function of the Mother class
    boolean testMoveMother(Tester t){
        Scene mt = new EmptyScene(900, 900);
        M1.move();
        M2.move();
        return ((t.checkExpect(M1.draw(mt), mt.placeImage(M1.img, M1.cell2Pixel())))&&
                (t.checkExpect(M2.draw(mt), mt.placeImage(M2.img, M2.cell2Pixel()))));
    }
/**
    //Test the onDraw function
    boolean testOnDraw(Tester t){
        Scene scn = new EmptyScene(USA.WIDTH,USA.HEIGHT).placeImage
        (new Rectangle(9999,9999,"solid", "black"), new Posn (0,0));
        scn = USA.draw(scn);
        scn = As.draw(scn);
        scn = Bt.draw(scn); 
        scn = Bf.draw(scn);
        scn = Bl.draw(scn);
        scn = Ms.draw(scn);
        scn = scn.placeImage(new Text("Score: "+((32-As.lox.size())*125)+"",25,"blue"), new Posn(332, 870));
        scn = scn.placeImage(new Text("Lives Remaning: " + 2+"", 25,"blue"),new Posn(100,870));
        scn = scn.placeImage(new Text("Aliens Remaning: " +As.lox.size()+"",25,"blue"), new Posn(765, 870));

        return (t.checkExpect(w1.onDraw(), scn));
    }
   */
    
    //Test the win function
    boolean testWin(Tester t){
        return((t.checkExpect(w1.win(), false))&&
               (t.checkExpect(w2.win(), true)));
    }
    //Test the stopWhen function
    boolean testStopWhen(Tester t){
        return((t.checkExpect(w1.stopWhen(), false))&&
                (t.checkExpect(w2.stopWhen(), true))&&
                (t.checkExpect(w3.stopWhen(),true)));
    }
    
   /** //Test the lastScene function
    boolean testLastScene(Tester t){
        return((t.checkExpect(w2.lastScene(),
                (new Text("You Win! Score = "+((32-As2.lox.size())*125)
                        +"", 69, "chartreuse")).toScene())) &&
                (t.checkExpect(w3.lastScene(),
                 (new Text("Forever Alone!!! Score = "+
                         ((32-As.lox.size())*125)+"", 69, "red")).toScene())));
    }
    */
    boolean testmakeAlien(Tester t){
        reset1();
        reset2();
        reset3();
        return((t.checkExpect(w1.makeAliens(1,1,new ListOf<Alien>(new ArrayList<Alien>())), this.Ll1))&&
                (t.checkExpect(w1.makeAliens(2,2,new ListOf<Alien>(new ArrayList<Alien>())), this.Ll2))&&
                (t.checkExpect(w1.makeAliens(13,2,new ListOf<Alien>(new ArrayList<Alien>())), this.Ll3)));
    }
    
    
    Alien Aa1 = new Alien(19,2,false); Alien Bb1 = new Alien(19,3,false);
    Alien Aa2 = new Alien(18,2,false); Alien Bb2 = new Alien(18,3,false);
    Alien Aa3 = new Alien(17,2,false); Alien Bb3 = new Alien(17,3,false);
    Alien Aa4 = new Alien(16,2,false); Alien Bb4 = new Alien(16,3,false);
    Alien Aa5 = new Alien(15,2,false); Alien Bb5 = new Alien(15,3,false);
    Alien Aa6 = new Alien(14,2,false); Alien Bb6 = new Alien(14,3,false);
    Alien Aa7 = new Alien(13,2,false); Alien Bb7 = new Alien(13,3,false);
    Alien Aa8 = new Alien(12,2,false); Alien Bb8 = new Alien(12,3,false);
    Alien Aa9 = new Alien(11,2,false); Alien Bb9 = new Alien(11,3,false);
    Alien Aa10 = new Alien(10,2,false); Alien Bb10 = new Alien(10,3,false);
    Alien Aa11 = new Alien(9,2,false); Alien Bb11 = new Alien(9,3,false);
    Alien Aa12 = new Alien(8,2,false); Alien Bb12 = new Alien(8,3,false);
    Alien Aa13 = new Alien(7,2,false); Alien Bb13 = new Alien(7,3,false);
    
    ListOf<Alien> Ll1 = new ListOf<Alien>(new ArrayList<Alien>());
    ListOf<Alien> Ll2 = new ListOf<Alien>(new ArrayList<Alien>());
    ListOf<Alien> Ll3 = new ListOf<Alien>(new ArrayList<Alien>());
    
    public void reset1(){
        this.Ll1.lox.add(Aa13);
    }
    
    public void reset2(){
        this.Ll2.lox.add(Bb12);
        this.Ll2.lox.add(Bb13);
        this.Ll2.lox.add(Aa12);
        this.Ll2.lox.add(Aa13);
    }
    
     public void reset3(){
         this.Ll3.lox.add(Aa1);
         this.Ll3.lox.add(Aa2);
         this.Ll3.lox.add(Aa3);
         this.Ll3.lox.add(Aa4);
         this.Ll3.lox.add(Aa5);
         this.Ll3.lox.add(Aa6);
         this.Ll3.lox.add(Aa7);
         this.Ll3.lox.add(Aa8);
         this.Ll3.lox.add(Aa9);
         this.Ll3.lox.add(Aa10);
         this.Ll3.lox.add(Aa11);
         this.Ll3.lox.add(Aa12);
         this.Ll3.lox.add(Aa13);
         this.Ll3.lox.add(Bb1);
         this.Ll3.lox.add(Bb2);
         this.Ll3.lox.add(Bb3);
         this.Ll3.lox.add(Bb4);
         this.Ll3.lox.add(Bb5);
         this.Ll3.lox.add(Bb6);
         this.Ll3.lox.add(Bb7);
         this.Ll3.lox.add(Bb8);
         this.Ll3.lox.add(Bb9);
         this.Ll3.lox.add(Bb10);
         this.Ll3.lox.add(Bb11);
         this.Ll3.lox.add(Bb12);
         this.Ll3.lox.add(Bb13);
    }
    
}




