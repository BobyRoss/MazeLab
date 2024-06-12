import java.awt.Color;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;


public class EdgeCell extends ShadedCell{

	private static Color edgeColor = Color.BLACK;
	private ArrayList<Side> listOfEdges = new ArrayList<Side>();
	private ArrayList<Side> walls = new ArrayList<Side>();
	
	public EdgeCell(MazeCanvas mc, int row, int col){
		super(mc, row, col, edgeColor);
		if(row == 0) {
			listOfEdges.add(Side.Top);
		}else if(row == mc.getRows()-1) {
			listOfEdges.add(Side.Bottom);
		}
		
		
		if(col == 0) {
			listOfEdges.add(Side.Left);
		}else if(col == mc.getCols()-1) {
			listOfEdges.add(Side.Right);
		}
	}
	
	public ArrayList<Side> getWalls(){
		
		walls = super.getWalls();
		
		for(Side a: listOfEdges) {
			walls.remove(a);
		}
		
		
		return walls;
	}
	
	
	public ArrayList<Side> getPaths(){
		ArrayList<Side> listOfWalls = super.getPaths();
		for(Side s: listOfEdges) {
			listOfWalls.remove(s);
		}
		return listOfWalls;
	}

}
