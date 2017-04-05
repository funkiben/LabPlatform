package test;

import java.awt.Color;

import lab.LabFrame;
import lab.component.data.DataTable;
import lab.util.SigFig;

public class DataTableTestLab extends LabFrame {
	
	private static final long serialVersionUID = 8292698528486548658L;

	public static void main(String[] args) {
		new DataTableTestLab();
	}
	
	
	private final DataTable<Double> dataTable;
	
	public DataTableTestLab() {
		super("Data Table Test Lab", 800, 800);
		
		dataTable = new DataTable<Double>(400, 400, 10, 5, DataTable.ROW_AND_COLUMN_TITLES) {
			@Override
			public String getString(Double d) {
				return SigFig.sigfigalize(d, 3);
			}
		};
		
		//dataTable.setAll(new Double(342543));
		//dataTable.setCell(0, 4, new Double(232));
		//dataTable.setColumn(2, new Double(234));
		dataTable.setRow(2, 4435345.0D);
		dataTable.setRow(1, 43.0D);
		dataTable.setRow(0, 564.0D);
		
		dataTable.setRowColor(2, Color.blue);
		dataTable.setColumnColor(2, Color.green);
		dataTable.setRowColor(0, Color.red);
		dataTable.setColumnColor(0, Color.yellow);
		
		for (int i = 0; i < 5; i++) {
			dataTable.setColumnTitle(i, "COLUMNs");
		}
		
		for (int i = 0; i < 10; i++) {
			dataTable.setRowTitle(i, "ROWs");
		}
		
		dataTable.setOffsetX(200);
		dataTable.setOffsetY(200);
		
		addComponent(dataTable);
		
		start(60);
		
	}

	@Override
	public void update() {
		
	}

}
