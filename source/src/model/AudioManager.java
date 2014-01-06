package model;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class AudioManager {
	
	Sequencer sequencer = null;
	public AudioManager(){
		
		// Get default sequencer.
		try {
			sequencer = MidiSystem.getSequencer();
			
			
			if (sequencer == null) {
			    // Error -- sequencer device is not supported.
			    // Inform user and return...
			} else {
			    // Acquire resources and make operational.
			    sequencer.open();
			}
			
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			File myMidiFile = new File("res/heybrother.mid");
			File myMidiFile2 = new File("res/test.mid");
		    //File myMidiFile = new File("res/samples/snare1.midi");
		    // Construct a Sequence object, and
		    // load it into my sequencer.
		    Sequence mySeq = MidiSystem.getSequence(myMidiFile);
		    Sequence mySeq2 = MidiSystem.getSequence(myMidiFile2);
		    sequencer.setSequence(mySeq);
		    sequencer.setSequence(mySeq2);
		} catch (Exception e) {
		   // Handle error and/or return
		}
		
	}
	
	
	public void start(){
		sequencer.start();
	}
	

}
