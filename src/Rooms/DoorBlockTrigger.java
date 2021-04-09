package Rooms;

import Rooms.*;
import Actors.*;
import javax.swing.ImageIcon;

public class DoorBlockTrigger extends VerilBaseObject
{
	private int tileNum;

	public DoorBlockTrigger(int tile, Location room, Location square, World w)
	{
		super(room, square, w);
		tileNum = tile;
	}

	public void trigger(Veril v)
	{
		int dir = v.getDirection();
		if (dir < 2)
			dir += 2;
		else
			dir -= 2;
		Location L = getLocation().nextSquare(dir);
		getRoom().getSquare(L).setNum(tileNum);
		getRoom().setChanged(true);
		removeSelf();
	}

	public ImageIcon getImage()
	{
		return null;
	}
}