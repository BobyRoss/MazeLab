import java.awt.color.*;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

import java.util.ArrayList;

public class Cell {
	
	int row;
	int col;
	MazeCanvas mc;
    private ArrayList<Side> listOfWalls;
    private boolean visited = false;
    
    
	public Cell(MazeCanvas mc, int row, int col){
		this.mc = mc;
		this.row = row;
		this.col = col;
		this.listOfWalls = new ArrayList<Side>();
		mc.drawCell(row, col);
		this.listOfWalls.add(Side.Left);
		this.listOfWalls.add(Side.Right);
		this.listOfWalls.add(Side.Top);
		this.listOfWalls.add(Side.Bottom);
	}
	

	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public ArrayList<Side> getWalls(){
		ArrayList<Side> copy = new ArrayList<Side>();
		for(Side a: listOfWalls) {
			copy.add(a);
		}
		return copy;
	}
	
	public boolean getVisited(){
		return visited;
	}
	
	public void setVisited(boolean visited, String s) {
		//System.out.println("called by " + s);
		this.visited = visited;
	}
	
	public void removeWall(Side s) {
		listOfWalls.remove(s);
		mc.eraseWall(row, col, s);
	}
	
	public ArrayList<Side> getPaths(){
		ArrayList<Side> listOfPaths = new ArrayList<Side>();
		listOfPaths.add(Side.Left);
		listOfPaths.add(Side.Right);
		listOfPaths.add(Side.Top);
		listOfPaths.add(Side.Bottom);
		
		for(Side s: listOfWalls) {
			listOfPaths.remove(s);
		}
		return listOfPaths;
		
	}
	
}
