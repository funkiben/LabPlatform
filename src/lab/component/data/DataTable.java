package lab.component.data;

import java.awt.Font;
import java.awt.Graphics;

import lab.component.LabComponent;

public class DataTable<E> extends LabComponent {
	
	public static final int NO_TITLES = 0;
	public static final int ROW_TITLES_ONLY = 1;
	public static final int COLUMN_TITLES_ONLY = 2;
	public static final int ROW_AND_COLUMN_TITLES = 3;
	
	private final int columns;
	private final int rows;
	private final int type;
	private final E[][] data;
	private final String[] rowTitles;
	private final String[] columnTitles;
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	
	public DataTable(int width, int height, int columns, int rows, int type) {
		super(width, height);
		
		this.columns = columns;
		this.rows = rows;
		
		this.type = type;
		
		data = (E[][]) new Comparable[rows][columns];
		
		rowTitles = new String[rows];
		columnTitles = new String[columns];
		
	}
	
	public E[][] getData() {
		return data;
	}
	
	public void setCell(int x, int y, E e) {
		data[x][y] = e;
	}
	
	public E getCell(int x, int y) {
		return data[y][x];
	}

	public void setAll(E e) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				data[x][y] = e;
			}
		}
	}
	
	public void setRow(int row, E e) {
		for (int i = 0; i < columns; i++) {
			data[row][i] = e;
		}
	}
	
	public void setColumn(int column, E e) {
		for (int i = 0; i < rows; i++) {
			data[i][column] = e;
		}
	}
	
	public String display(E e) {
		return e.toString();
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public Font getFont() {
		return font;
	}
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		double cellWidth = 1.0 / rows * width;
		double cellHeight = 1.0 / rows * width;
		
		if (type == NO_TITLES) {
			
			for (int yi = 0; yi < columns; yi++) {
				for (int xi = 0; xi < rows; xi++) {
					
					g.drawRect((int) (xi * cellWidth) + x, (int) (yi * cellHeight) + y, (int) cellWidth, (int) cellHeight);
					
					
					
				}
			}
			
		} else if (type == ROW_TITLES_ONLY) {
			
		} else if (type == COLUMN_TITLES_ONLY) {
			
		} else if (type == ROW_AND_COLUMN_TITLES) {
			
		}
	}

}
