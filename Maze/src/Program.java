/**
 * This creates and solves a maze
 * @author s-stojima
 *
 */

import java.awt.Color;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
import helpers.Cell;
public class Program {
	
	public static final int ROWS = 10;
	public static final int COLS = 10;
	public static final int SIZE = 32;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazeCanvas mc = new MazeCanvas(ROWS, COLS, SIZE);
		Maze maze = new Maze(mc);
		mc.open();
		maze.initialize();
		
		Generator create = new Generator(mc, maze);
		create.run();
		
		Solver solve = new Solver(mc, maze);
		solve.run();
		mc.pause();
		
		
		//Cell ran = new Cell(mc, 3, 4);
		//maze.genSnake();
		mc.pause();
		//mc.close();
	}
	

	
	public void Maze(MazeCanvas mazeCanvas) {
		
	}

}
