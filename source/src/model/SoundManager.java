package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Provides methods to add, load and play sound clips.
 */
public class SoundManager {
	private javax.sound.sampled.Line.Info lineInfo;

	private Vector afs;
	private Vector sizes;
	private Vector infos;
	private Vector audios;
	private int num = 0;

	public SoundManager() {
		afs = new Vector();
		sizes = new Vector();
		infos = new Vector();
		audios = new Vector();
	}

	/**
	 * Reads a sound clip and stores it into the internal vectors.
	 * 
	 * @param s
	 *            Path to audio file
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public void addClip(String s) throws IOException,
			UnsupportedAudioFileException, LineUnavailableException {
		URL url = getClass().getResource(s);
		// InputStream inputstream = url.openStream();
		AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(loadStream(url.openStream()));
		AudioFormat af = audioInputStream.getFormat();
		int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
		byte[] audio = new byte[size];
		DataLine.Info info = new DataLine.Info(Clip.class, af, size);
		audioInputStream.read(audio, 0, size);

		afs.add(af);
		sizes.add(new Integer(size));
		infos.add(info);
		audios.add(audio);

		num++;
	}

	private ByteArrayInputStream loadStream(InputStream inputstream)
			throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		byte data[] = new byte[1024];
		for (int i = inputstream.read(data); i != -1; i = inputstream
				.read(data))
			bytearrayoutputstream.write(data, 0, i);

		inputstream.close();
		bytearrayoutputstream.close();
		data = bytearrayoutputstream.toByteArray();
		return new ByteArrayInputStream(data);
	}

	/**
	 * Plays the specified sound clip.
	 * 
	 * @param x
	 *            Index on the vector
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public void playSound(int x) throws UnsupportedAudioFileException,
			LineUnavailableException {
		if (x > num) {
			System.out.println("playSound: sample nr[" + x
					+ "] is not available");
		} else {
			Clip clip = (Clip) AudioSystem.getLine((DataLine.Info) infos
					.elementAt(x));
			clip.open((AudioFormat) afs.elementAt(x),
					(byte[]) audios.elementAt(x), 0,
					((Integer) sizes.elementAt(x)).intValue());
			clip.start();
		}
	}

	/**
	 * Clears the SoundManager.
	 */
	public void clear() {
		afs.clear();
		sizes.clear();
		infos.clear();
		audios.clear();
	}
}