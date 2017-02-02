package test;

import lab.LabFrame;
import lab.SigFig;
import lab.component.data.DataTable;

public class DataTableTestLab extends LabFrame {
	
	private static final long serialVersionUID = 8292698528486548658L;

	public static void main(String[] args) {
		new DataTableTestLab();
	}
	
	public DataTableTestLab() {
		super("Data Table Test Lab", 800, 800);
		
		DataTable<Double> dataTable = new DataTable<Double>(400, 400, 10, 10, DataTable.ROW_AND_COLUMN_TITLES) {
			@Override
			public String display(Double d) {
				return SigFig.sigfigalize(d, 3);
			}
		};
		
		addComponent(dataTable);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
