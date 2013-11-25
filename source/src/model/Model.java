package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

import strategies.IImageAnalyzer;



public class Model implements PropertyChangeListener{
	

	PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	private static Model instance = null;
	
	private int[][] matrix;
	private int sampleCount;
	private int loopLength;
	private IImageAnalyzer imageAnalyzer;
	
	
	/*
	private LinkedList<ModelListener> listeners = new LinkedList<>();
	
	public void addModelChangeListener(ModelListener listener) {
		listeners.add(listener);
	}
	
	*/
	
	public void setMatrix(int sampleCount, int loopLength){
		this.sampleCount = sampleCount;
		this.loopLength = loopLength;
		
		matrix = new int[loopLength][sampleCount];
	}
	

	//defining the listener-methods
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		changeSupport.firePropertyChange(event);
	}
	
	//method which preserves access to this class
	public static Model getInstance(){
		if(instance == null){
			instance = new Model();
		}
		return instance;
	}
	
	
	
}
