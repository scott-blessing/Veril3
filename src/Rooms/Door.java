package Rooms;

import Rooms.*;
import Actors.Veril;
import Actors.VerilBaseObject;
import javax.swing.ImageIcon;

public class Door extends VerilBaseObject
{
	private Location destinationRoom;
	private Location destinationSquare;
	private World destinationWorld;
	private boolean oneTime;

	public Door(Location myRoom, Location myLoc, World myWorld, Location sendToRoom, Location sendToSquare, World sendToWorld, boolean oneTime)
	{
		super(myRoom, myLoc, myWorld);
		destinationRoom = sendToRoom;
		destinationSquare = sendToSquare;
		destinationWorld = sendToWorld;
		this.oneTime = oneTime;
	}

	public Location getDestinationRoom()
	{
		return destinationRoom;
	}
	public Location getDestinationSquare()
	{
		return destinationSquare;
	}
	public World getDestinationWorld()
	{
		return destinationWorld;
	}

	public void enter(Veril v)
	{
		v.setWorld(destinationWorld);
		v.setRoomLoc(destinationRoom);
		v.setLocation(destinationSquare);
		if (oneTime)
			removeSelf();
	}

	public ImageIcon getImage()
	{
		return null;
	}
}
