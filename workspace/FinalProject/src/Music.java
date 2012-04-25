
import java.io.*;
import javax.sound.sampled.*;

/** Represents Music */
public class Music {
	Clip music;
	AudioInputStream stream;
	File file;
	String filename;


    public void begin(){
		if(!this.music.isRunning()) this.music.start();	
	}
	
	public void stop(){
		this.music.stop();
		this.music.close();
	}
	
	
	/** Sets the clip to s, for loop number of times
	 * 
	 * @param String the name of the clip
	 * @param int How many Times the clip plays 
	 */
	public void setClip(String s, int loop){
		try {
			this.filename = s;
			this.file = new File(s);
			this.stream = AudioSystem.getAudioInputStream(this.file);
			this.music = AudioSystem.getClip();
			this.music.open(this.stream);
			this.music.loop(loop);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/** plays the sound for when you die */
	public void playDie(int loop){
		this.setClip("sounds/explosion.wav",loop);
		this.begin();
	}
	
	/** plays the sound for when you shoot */
	public void playShoot(int loop){
		this.setClip("sounds/shoot.wav",loop);
		this.begin();
	}
	
	/** plays the sound for when an alien is killed */
	public void playInvaderKilled(int loop){
		this.setClip("sounds/invaderkilled.wav",loop);
		this.begin();
	}
	
	
	/** plays the sound for when you kill the mother ship*/
    public void playPapapa(int loop){
        this.setClip("sounds/Powerpapapapa.wav",loop);
        this.begin();
    }
    

    /** plays the sound for the background */
    public void playSandStorm(int loop){
        this.stop();
        this.setClip("sounds/Sandstormsong.wav",loop);
        this.begin();
    }
    
}




