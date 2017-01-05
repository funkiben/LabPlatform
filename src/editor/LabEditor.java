package editor;

import lab.LabFrame;

public class LabEditor extends LabFrame {
	
	private static final long serialVersionUID = 1L;

	public LabEditor() {
		super("Lab Editor", 1500, 800);
		
		addComponent(new ComponentPicker());
		
		start(30);
		
	}

	@Override
	public void update() {
		
	}

}
