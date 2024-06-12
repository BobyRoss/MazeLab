import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Generator {
	private MazeCanvas MC;
	public Maze CURMAZE;
	public Color generatePathColor = Color.GREEN;
	
	public Generator(MazeCanvas mc, Maze m) {
		MC = mc;
		CURMAZE = m;
	}
	
	public List<Side> shuffle(List<Side> sides){
		List<Side> ba = new ArrayList<Side>();
		
		
		for(int b=0; b<sides.size(); ) {
			int a = (int)(Math.random() * sides.size());
			if(!ba.contains(sides.get(a))) {
				ba.add(sides.get(a));
				b++;
			}
		}
		return ba;
	}
	
	public Side getOpposite(Side side) {
		if(side.equals(Side.Left)) {
			return Side.Right;
		}else if(side.equals(Side.Right)) {
			return Side.Left;
		}else if(side.equals(Side.Top)) {
			return Side.Bottom;
		}else if(side.equals(Side.Bottom)){
			return Side.Top;
		}else {
			return Side.Center;
		}
	}
	
	public boolean run(Cell cell, Side side) {
		System.out.println(side);
		
		if(cell == CURMAZE.getExitCell()) {
			System.out.println("goal");
		}
		
		cell.setVisited(true, "gen");
		MC.drawPath(cell.getRow(), cell.getCol(), side, generatePathColor);
		MC.drawCenter(cell.getRow(), cell.getCol(), generatePathColor);
		cell.removeWall(side);
		List<Side> walls = shuffle(cell.getWalls());
		for(Side s: walls) {
			Cell neighbor = CURMAZE.getNeighbor(cell, s);
			if(neighbor.getVisited() == false) {
				MC.drawPath(cell.getRow(), cell.getCol(), s, generatePathColor);
				cell.removeWall(s);
				run(neighbor, getOpposite(s));
				MC.erasePath(cell.getRow(), cell.getCol(), s);
			}
		}
		MC.eraseCenter(cell.getRow(), cell.getCol());
		MC.erasePath(cell.getRow(), cell.getCol(), side);
		return false;
	}
	
	public boolean run() {
		return run(CURMAZE.getEntryCell(), Side.Center);
	}
}
