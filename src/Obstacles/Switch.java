package Obstacles;

import Obstacles.Obstacle;
import Items.Equipment;
import Rooms.*;
import Actors.Veril;
import GUI.ActionResult;

public class Switch extends Obstacle
{
	private String newMapString = "";


	public Switch(Location room, Location square, World w)
	{
		super(Obstacle.TYPE_SWITCH, room, square, w);
		for (int i = 0; i < 192; i++)
			newMapString += "00 ";
	}

	public void setNewRoomString(String newRoom)
	{
		newMapString = newRoom;
	}

	public void hit()
	{
		getWorld().getRoom(getRoomLoc()).changeTo(newMapString);
	}

	public ActionResult interact()
	{
		return interact(new Equipment(Equipment.TYPE_NONE), getRoom().getVeril());
	}
	public ActionResult interact(Equipment e, Veril v)
	{
		hit();
		return null;
	}
}
