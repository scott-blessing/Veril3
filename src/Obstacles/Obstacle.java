package Obstacles;

import Rooms.*;
import Actors.*;
import GUI.*;
import Items.Equipment;
import Items.Key;
import javax.swing.ImageIcon;

public class Obstacle extends VerilBaseObject
{
	public static final int TYPE_SWITCH = 0;
	public static final int TYPE_BUSH = 1;
	public static final int TYPE_BOULDER = 2;
	public static final int TYPE_ROCK = 3;
	public static final int TYPE_CHEST_CLOSED = 4;
	public static final int TYPE_CHEST_OPENED = 5;
	public static final int TYPE_BURIED_TREASURE = 6;
	public static final int TYPE_GATE = 7;
	public static final int TYPE_KEYBLOCK = 8;
	public static final int TYPE_DUG_SPOT = 9;
	public static final int TYPE_STEPPING_STONE = 10;
	public static final int TYPE_SAVETILE = 11;
	public static final int TYPE_FIRE = 12;
	public static final int TYPE_FURNITURE = 13;
	public static final int TYPE_DISPENSER = 14;
	public static final int TYPE_ICE_BLOCK = 15;
	public static final int TYPE_ICE_HOLE_OPEN = 16;
	public static final int TYPE_ICE_HOLE_CLOSED = 17;
	public static final int TYPE_WHIRLPOOL = 18;
	public static final int TYPE_RUBBLE = 19;

	private int type;

	public Obstacle(int type, Location room, Location square, World w)
	{
		super(room, square, w);
		this.type = type;
	}

	public void setType(int newType)
	{
		type = newType;
	}
	public int getType()
	{
		return type;
	}


	public ActionResult interact(Equipment e, Veril v)
	{
		if (type == TYPE_BOULDER && e.getType() == Equipment.TYPE_POKINGSTICK)
		{
			Location newLoc = getLocation().nextSquare(v.getDirection());
			if (newLoc != null)
			{
				boolean w = true;
				if (getRoom().getObjectAt(newLoc) != null)
					w = false;

				Square s = getRoom().getSquare(newLoc);

				if (s.isWalkable() && w)
					setLocation(newLoc);
				else if ((s.isWater() || s.isLava()) && w)
				{
					setLocation(newLoc);
					setType(TYPE_STEPPING_STONE);
				}
			}

		}
		else if (type == TYPE_ROCK && e.getType() == Equipment.TYPE_HAMMER)
			removeSelf();
		else if (type == TYPE_DUG_SPOT && e.getType() == Equipment.TYPE_SHOVEL)
			return new ActionResult("You've already dug here");
		else if (type == TYPE_DISPENSER)
			v.getInventory().addArrows(10);
		return null;
	}

	public boolean isWalkable()
	{
		if (type == TYPE_BURIED_TREASURE || type == TYPE_DUG_SPOT || type == TYPE_STEPPING_STONE || type == TYPE_SAVETILE || type == TYPE_ICE_HOLE_CLOSED)
			return true;
		return false;
	}

	public void removeSelf()
	{
		getRoom().removeObject(this);
	}

	public ImageIcon getImage()
	{
		String fp = "images/Obstacle/";
		if (type == TYPE_SWITCH)
			fp += "Switch";
		else if (type == TYPE_BUSH)
			fp += "Bush";
		else if (type == TYPE_BOULDER)
			fp += "Boulder";
		else if (type == TYPE_ROCK)
			fp += "Rock";
		else if (type == TYPE_CHEST_CLOSED)
			fp += "Chest";
		else if (type == TYPE_CHEST_OPENED)
			fp += "OpenChest";
		else if (type == TYPE_BURIED_TREASURE)
			fp += "DigSpot";
		else if (type == TYPE_GATE)
			fp += "Gate";
		else if (type == TYPE_DUG_SPOT)
			fp += "DugSpot";
		else if (type == TYPE_STEPPING_STONE)
			fp += "Stone";
		else if (type == TYPE_DISPENSER)
			fp += "Dispenser";
		else if (type == TYPE_ICE_BLOCK)
			fp += "IceBlock";
		else if (type == TYPE_ICE_HOLE_OPEN)
			fp += "IceHoleOpen";
		else if (type == TYPE_ICE_HOLE_CLOSED)
			fp += "IceHoleClosed";
		else if (type == TYPE_RUBBLE)
			fp += "Rubble";

		if (type == TYPE_SAVETILE)
			fp += "SaveTile.gif";
		else
			fp += ".png";

		return new ImageIcon(fp);
	}
}
