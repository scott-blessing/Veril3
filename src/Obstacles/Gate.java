package Obstacles;

import Obstacles.Obstacle;
import Rooms.*;
import Actors.Veril;
import Items.Item;
import Items.Equipment;
import GUI.ActionResult;

public class Gate extends Obstacle
{
	public char	gateValue = ' ';

	public Gate(char val, Location room, Location square, World w)
	{
		super(Obstacle.TYPE_GATE, room, square, w);
		gateValue = val;
	}

	public ActionResult interact(Equipment e, Veril v)
	{
		if (v.getInventory().hasGateKey(gateValue))
		{
			removeSelf();
			v.getInventory().useGateKey(gateValue);
		}
		else
			return new ActionResult("You don't have a gate key that fits");
		return null;
	}

}
