package Shortcuts;

import Rooms.*;

public class DoorAdder
{
	private World world;

	public DoorAdder(World w)
	{
		world = w;
	}

	public void addOneDoor(Location room, Location square, Location dRoom, Location dSquare, World dWorld)
	{
		world.add(new Door(room, square, world, dRoom, dSquare, dWorld, false), room);
	}
	public void addTwoDoor(Location room, Location square, Location dRoom, Location dSquare, World dWorld)
	{
		world.add(new Door(room, square, world, dRoom, dSquare, dWorld, false), room);
		dWorld.add(new Door(dRoom, dSquare, dWorld, room, square, world, false), dRoom);
	}

	public void addOneTimeDoor(Location room, Location square, Location dRoom, Location dSquare, World dWorld)
	{
		world.add(new Door(room, square, world, dRoom, dSquare, dWorld, true), room);
	}
}
