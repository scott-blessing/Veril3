package Obstacles;

import Obstacles.Obstacle;
import Rooms.*;
import Actors.Veril;
import Items.Item;
import Items.Equipment;
import GUI.ActionResult;

public class Chest extends Obstacle
{
	private Item contents;

	public Chest(Location room, Location square, World w, Item whatsInside)
	{
		super(Obstacle.TYPE_CHEST_CLOSED, room, square, w);
		contents = whatsInside;
	}

	public ActionResult open(Veril v)
	{
		if (getType() == Obstacle.TYPE_CHEST_CLOSED)
		{
			contents.pickUp(v);
			setType(Obstacle.TYPE_CHEST_OPENED);
			return new ActionResult(contents.getMessage(), contents.getImage());
		}
		return null;
	}

	public ActionResult interact(Equipment e, Veril v)
	{
		if (e.getType() == Equipment.TYPE_NONE)
			return open(v);
		return null;
	}

	public Item getContents()
	{
		return contents;
	}

}
