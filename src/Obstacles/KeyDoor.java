package Obstacles;

import Obstacles.Obstacle;
import Rooms.*;
import Actors.*;
import GUI.*;
import Items.Equipment;
import Items.Key;
import javax.swing.ImageIcon;

public class KeyDoor extends Obstacle
{
	public static int CIRCLE = 0;
	public static int SQUARE = 1;
	public static int TRIANGLE = 2;
	public static int SYMBOL = 4;

	private int shape;

	public KeyDoor(int shape, Location room, Location square, World w)
	{
		super(Obstacle.TYPE_KEYBLOCK, room, square, w);
		this.shape = shape;
	}

	public int getShape()
	{
		return shape;
	}


	public ActionResult interact(Equipment e, Veril v)
	{
		if (v.getInventory().hasAKey(shape))
		{
			removeSelf();
			v.getInventory().useKey(shape);
		}
		return null;
	}

	public ImageIcon getImage()
	{
		String fp = "images/Obstacle/";
		if (shape == CIRCLE)
			fp += "CircleBlock";
		else if (shape == TRIANGLE)
			fp += "TriangleBlock";
		else if (shape == SQUARE)
			fp += "SquareBlock";
		else if (shape == SYMBOL)
			fp += "SymbolBlock";
		fp += ".png";
		return new ImageIcon(fp);
	}
}
