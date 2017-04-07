package lab.component.swing.input.list;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import lab.component.swing.input.field.TextField;

public abstract class SingleFieldMutableList<E> extends MutableList<E> {

	protected final TextField entryField;
	
	public SingleFieldMutableList(int width, int height) {
		super(width, height);
		
		entryField = createEntryField();
		
		entryField.setWidth(width - 60);
		entryField.getJComponent().setText("");
		entryField.getJComponent().addKeyListener(new EntryFieldKeyListener());
		
		addChild(entryField);
	}
	
	public abstract TextField createEntryField();

	@SuppressWarnings("unchecked")
	@Override
	public E getEntry() {
		if (entryField.hasInput()) {
			return (E) entryField.getValue();
		}
		
		return null;
	}
	
	public void clearEntry() {
		entryField.setText("");
	}
	
	public boolean entryHasFocus() {
		return entryField.hasFocus();
	}
	
	private class EntryFieldKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER && getAddButton().isEnabled()) {
				getAddButton().doSomething();
			}
		}
	}
	
	
}
