package Game.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {

	Clip clip;

	public void setFile(String soundFile) {
		try {
			File file = new File(soundFile);
			AudioInputStream Au = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(Au);
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void play() {
		// clip.setFramePosition(0);
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void Stopsound() {
		clip.stop();
	}
	
}
