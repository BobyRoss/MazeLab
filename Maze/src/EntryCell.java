import graphics.MazeCanvas;
import java.awt.Color;

public class EntryCell extends EdgeCell{
	
	private static Color enCol = Color.GREEN;

	public EntryCell(MazeCanvas mc, int row, int col) {
		super(mc, row, col);
		mc.drawShade(row, col, enCol);
	}

	
}
