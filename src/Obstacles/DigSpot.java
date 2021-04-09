package Obstacles;

import Obstacles.Obstacle;
import Rooms.*;
import Actors.Veril;
import Items.Item;
import Items.Equipment;
import GUI.ActionResult;


public class DigSpot extends Obstacle
{
	private Item contents;


	public DigSpot(Location room, Location square, World w, Item whatsInside)
	{
		super(Obstacle.TYPE_BURIED_TREASURE, room, square, w);
		contents = whatsInside;
	}

	public Item getContents()
	{
		return contents;
	}

	public ActionResult dig(Veril v)
	{
            if (contents==null) return null;
            contents.pickUp(v);
            setType(Obstacle.TYPE_DUG_SPOT);
            ActionResult a = new ActionResult(contents.getMessage(), contents.getImage());
            contents = null;
            return a;
       }

	public ActionResult interact(Equipment e, Veril v)
	{
		if (e.getType() == Equipment.TYPE_SHOVEL)
			return dig(v);
		return null;
	}
}