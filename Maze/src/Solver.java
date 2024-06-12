import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Solver {
	
	private MazeCanvas MC;
	private Maze m;
	private Color fwdPathColor = Color.BLUE;
	private Color bktPathColor = Color.RED;
	
	public Solver(MazeCanvas MC, Maze m) {
		this.MC = MC;
		this.m = m;
	}
	
	
	public boolean run(Cell cell, Side side) {
		cell.setVisited(true, "sol");
		MC.drawPath(cell.getRow(), cell.getCol(), side, fwdPathColor);
		MC.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
		if(cell == m.getExitCell()) {
			return true;
		}
		List<Side> shufPath = shuffle(cell.getPaths());
		for(Side s: shufPath){
			Cell neigh = m.getNeighbor(cell, s);
			if(!neigh.getVisited()) {
				MC.drawCenter(cell.getRow(), cell.getCol(),fwdPathColor);
				MC.drawPath(cell.getRow(), cell.getCol(), s, fwdPathColor);
				if(run(neigh, getOpposite(s))) {
					return true;
				}
				
				MC.drawCenter(cell.getRow(), cell.getCol(),bktPathColor);
				MC.drawPath(cell.getRow(), cell.getCol(), s, bktPathColor);
				
			}
		}
		MC.drawCenter(cell.getRow(), cell.getCol(), bktPathColor);
		MC.drawPath(cell.getRow(), cell.getCol(), side, bktPathColor);
		
		
		return false;
	}
	
	
	public boolean run() {
		for(int row=0; row<MC.getRows(); row++) {
			for(int col=0; col<MC.getCols(); col++) {
				m.getCell(row, col).setVisited(false, "sol");
			}
		}
		

		
		return run(m.getEntryCell(), Side.Center);
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
}
