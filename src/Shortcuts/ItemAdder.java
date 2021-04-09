package Shortcuts;

import Rooms.*;
import Items.*;

public class ItemAdder
{
	private World world;

	public ItemAdder(World w)
	{
		world = w;
	}

	public void addLovi(int value, Location room, Location square)
	{
		world.add(new Item(Item.TYPE_LOVI, value, room, square, world), room);
	}
	public void addArrow(int value, Location room, Location square)
	{
		world.add(new Item(Item.TYPE_ARROW, value, room, square, world), room);
	}

	public void addKey(int shape, Location room, Location square)
	{
		world.add(new Key(shape, room, square, world), room);
	}

	public void addSmallHeart(Location room, Location square)
	{
		world.add(new Item(Item.TYPE_HEART_SMALL, room, square, world), room);
	}
	public void addHeartPiece(Location room, Location square)
	{
		world.add(new Item(Item.TYPE_HEART_PIECE, room, square, world), room);
	}
	public void addFullHeart (Location room, Location square)
	{
		world.add(new Item(Item.TYPE_HEART_FULL, room, square, world), room);
	}

	public void addSword(Location room, Location square)
	{
		world.add(new Item(Item.TYPE_SWORD, room, square, world), room);
	}

	public void addCodeSnippet (Location room, Location square, Location dRoom, Location dLoc, World dWorld, int num)
	{
		world.add(new CodeSnippet(room, square, world, dRoom, dLoc, dWorld, num), room);
	}

	public void addCMD(Location room, Location square)
	{
		world.add(new Item(Item.TYPE_CMD, room, square, world), room);
	}

}
