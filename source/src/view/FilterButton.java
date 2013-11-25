package view;

import javax.swing.JButton;

import model.Model;
import framework.INotifyable;

public class FilterButton extends JButton implements INotifyable{
	
	
	
	Model model = Model.getInstance();
	
	public FilterButton(){
		model.addModelChangeListener(this);
	}
	

	@Override
	public void update() {
		
		this.setEnabled(model.isFilterActive("red"));

	}
	
	
	
	


}
