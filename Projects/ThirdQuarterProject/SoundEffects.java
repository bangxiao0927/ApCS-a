import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class SoundEffects {
	private static final String DRAW_SOUND = "card_draw.wav";
	private static final String PLAY_SOUND = "card_play.wav";

	private SoundEffects() {
	}

	public static void playDrawSound() {
		playWav(DRAW_SOUND);
	}

	public static void playPlaySound() {
		playWav(PLAY_SOUND);
	}

	private static void playWav(String fileName) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					AudioInputStream stream = AudioSystem.getAudioInputStream(new File(fileName));
					Clip clip = AudioSystem.getClip();
					clip.open(stream);
					clip.start();
				} catch (Exception e) {
					// Audio can fail on some systems; ignore to avoid breaking gameplay.
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
}
