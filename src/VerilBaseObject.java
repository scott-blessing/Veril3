package Actors;

import Rooms.*;
import javax.swing.ImageIcon;
import java.io.Serializable;

public abstract class VerilBaseObject implements Serializable
{
	private Location curRoom;
	private Location loc;
	private World world;

	public VerilBaseObject()
	{
		curRoom = null;
		loc = null;
		world = null;
	}
	public VerilBaseObject(Location room, Location square, World wor)
	{
		curRoom = room;
		loc = square;
		world = wor;
	}

	public void setLocation(Location newLoc)
	{
		loc = newLoc;
	}
	public void setLocation(int X, int Y)
	{
		loc = new Location(X,Y);
	}
	public void setRoomLoc(Location newLoc)
	{
		curRoom = newLoc;
	}
	public void setRoomLoc(int X, int Y)
	{
		curRoom = new Location(X,Y);
	}
	public void setWorld(World w)
	{
		world = w;
	}

	public World getWorld()
	{
		return world;
	}
	public Location getLocation()
	{
		return loc;
	}
	public Location getRoomLoc()
	{
		return curRoom;
	}
	public Room getRoom()
	{
		return world.getRoom(curRoom);
	}

	public int getActualX()
	{
		return loc.getX()*50;
	}
	public int getActualY()
	{
		return loc.getY()*50;
	}

	public void removeSelf()
	{
		getRoom().removeObject(this);
	}

	public abstract ImageIcon getImage();
}
