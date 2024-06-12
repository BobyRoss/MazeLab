/**
 * This creates a maze
 * @author s-stojima
 *
 */



import java.awt.Color;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Maze{
	
	MazeCanvas mc;
	static int COLS;
	static int ROWS;
	private Cell[][] gridOfCells; 
	static Cell ENTRY;
	static Cell EXIT;
	
	public Maze(MazeCanvas mazeCanvas){
		mc = mazeCanvas;
		COLS = mazeCanvas.getCols();
		ROWS = mazeCanvas.getRows();
		gridOfCells = new Cell[ROWS][COLS];
	}
	
	public void genSnake() {
		
		for(int i=0; i<COLS; i+=2) {
			mc.drawPath(0, i, Side.Left, Color.RED);
			mc.drawCenter(0, i, Color.RED);
			mc.drawPath(0, i, Side.Bottom, Color.RED);
			mc.drawWall(0, i, Side.Right);
			
			for(int a=1; a<ROWS-1; a++) {
				mc.drawPath(a, i, Side.Top, Color.RED);
				mc.drawPath(a, i, Side.Bottom, Color.RED);
				mc.drawCenter(a, i, Color.RED);
				mc.drawWall(a, i, Side.Right);
				
			}
			

			mc.drawPath(ROWS-1, i, Side.Top, Color.RED);
			mc.drawPath(ROWS-1, i, Side.Right, Color.RED);
			mc.drawCenter(ROWS-1, i, Color.RED);
			mc.drawPath(ROWS-1, i+1, Side.Left, Color.RED);
			mc.drawCenter(ROWS-1, i+1, Color.RED);
			mc.drawPath(ROWS-1, i+1, Side.Top, Color.RED);
			mc.drawWall(ROWS-1, i+1, Side.Right);
			
			for(int a=ROWS-2; a>0; a--) {
				mc.drawPath(a, i+1, Side.Top, Color.RED);
				mc.drawPath(a, i+1, Side.Bottom, Color.RED);
				mc.drawCenter(a, i+1, Color.RED);
				mc.drawWall(a, i+1, Side.Right);
			}
			
			mc.drawPath(0, i+1, Side.Bottom, Color.RED);
			mc.drawPath(0, i+1, Side.Right, Color.RED);
			mc.drawCenter(0, i+1, Color.RED);
			
		}
	}
	
	public void initialize() {
		int count = (COLS-2)*(ROWS-2) / 20;
		int entryN = (int)((COLS * 2) + ((ROWS-2) * 2) * Math.random());
		int exitN =  (int)((COLS * 2) + ((ROWS-2) * 2) * Math.random());
		int edgeN = 0;
		
		for(int row=0; row<gridOfCells.length; row++) {
			for(int col=0; col<gridOfCells[0].length; col++) {
				if(row == 0 || col == 0 || row == ROWS-1 || col == COLS-1) {
					if(edgeN == entryN) {
						gridOfCells[row][col] = new EntryCell(mc, row, col);
						ENTRY = gridOfCells[row][col];
					}else if(edgeN == exitN) {
						gridOfCells[row][col] = new ExitCell(mc, row, col);
						EXIT = gridOfCells[row][col];
					}else {
						gridOfCells[row][col] = new EdgeCell(mc, row, col);
					}
					edgeN ++;
				}else if(Math.random() < 0.05 && count > 0){
					gridOfCells[row][col] = new BlockCell(mc, row, col);
					count --;
				}else {
					gridOfCells[row][col] = new Cell(mc, row, col);
				}
			}
		}
	}
	
	
	public Cell getCell(int row, int col) {
		return gridOfCells[row][col];
	}
	
	
	public Cell getEntryCell() {
		return ENTRY;
	}
	
	
	public Cell getExitCell() {
		return EXIT;
	}
	
	public Cell getNeighbor(Cell cell, Side side) {
		int col = cell.getCol();
		int row = cell.getRow();
		if(side.equals(Side.Top) && row > 0) {
			row--;
		}else if(side.equals(Side.Left) && col > 0) {
			col--;
		}else if(side.equals(Side.Right) && col < gridOfCells[0].length) {
			col++;
		}else if (side.equals(Side.Bottom) && row < gridOfCells.length) {
			row++;
		}else {
			return null;
		}
		return gridOfCells[row][col];
	}
}
