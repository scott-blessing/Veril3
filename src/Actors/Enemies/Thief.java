package Actors.Enemies;


import Actors.*;
import Rooms.*;
import GUI.Inventory;
import javax.swing.ImageIcon;


public class Thief extends Enemy
{
	public static final int BASE_HEALTH = 10;
	public static final int BASE_ATTACK = 3;

	public Thief(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 4, roomLoc, false, w);
	}

	public void act()
	{
		if (canAttack())
		{
			Veril v = getWorld().getRoom(getRoomLoc()).getVeril();
			if (v.getDirection() == getDirection())
				v.getInventory().loseMoney(5);
			else
				attack(v);
		}

		else
			doMove();
	}

	public void doMove()
	{
		if (within(7))
		{
			int d = directionTowardsVeril();
			if (getDirection() == d && canMove())
				move();
			else
				setDirection(d);
		}
		else
		{
			int r = (int) (Math.random() * 8);
			if (r < 4)
				setDirection(r);
			else if (canMove())
				move();
		}
	}


	public ImageIcon getImage()
	{
		return getImage("Thief");
	}
}
