import graphics.MazeCanvas;
import java.awt.Color;



public class ExitCell extends EdgeCell {
	
	private static Color exCol = Color.RED;
	
	public ExitCell(MazeCanvas mc, int row, int col) {
		super(mc, row, col);
		mc.drawShade(row, col, exCol);
	}
}
