import java.awt.Color;
import graphics.MazeCanvas;

public class ShadedCell extends Cell {
	
	private Color shadeColor;
	
	public ShadedCell(MazeCanvas mazeCanvas, int row, int col, Color shadeColor){
		super(mazeCanvas, row, col);
		this.shadeColor = shadeColor;
		mc.drawShade(row, col, this.shadeColor);
	}
}
