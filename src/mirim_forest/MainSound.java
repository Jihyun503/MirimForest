package mirim_forest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MainSound{
	protected static String name = null;
	Clip clip;
	MainSound(){
		
	}
	
	public void mainsound(File file) {
		
		try {
			System.out.println("play");
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);

			 clip = AudioSystem.getClip();

			 clip.open(ais);
			 
			 //clip.stop();

			 clip.start();
			 name = "d";
			 
			 //clip.loop(0);
			 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void Stop_Sound() {
		System.out.println("stop");
		clip.stop();
		clip.close();
	}
}

