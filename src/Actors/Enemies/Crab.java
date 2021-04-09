package Actors.Enemies;

import Actors.Enemy;
import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;

public class Crab extends Enemy
{
	public static final int BASE_HEALTH = 10;
	public static final int BASE_ATTACK = 2;

	public Crab(int strength, Location roomLoc, World w)
	{
		super(Actor.DIRECTION_DOWN, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 12, roomLoc, false, w);
	}

	public void act()
	{
		if (canAttack())
			attack(getWorld().getRoom(getRoomLoc()).getVeril());
		else
			doMove();
	}

	public boolean canAttack()
	{
		Location loc1 = getLocation().nextSquare(Actor.DIRECTION_LEFT);
		Location loc2 = getLocation().nextSquare(Actor.DIRECTION_RIGHT);
		Location loc3 = getLocation().nextSquare(Actor.DIRECTION_DOWN);
		Veril v = getWorld().getRoom(getRoomLoc()).getVeril();
		return (v.getLocation().equals(loc1) || v.getLocation().equals(loc2) || v.getLocation().equals(loc3));
	}

	public void doMove()
	{
		int r = (int) (Math.random() * 2);
		if (r == 1)
		{
			setDirection(Actor.DIRECTION_LEFT);
			if (canMove())
				move();
		}
		else
		{
			setDirection(Actor.DIRECTION_RIGHT);
			if (canMove())
				move();
		}
		setDirection(Actor.DIRECTION_DOWN);
	}


	public ImageIcon getImage()
	{
		return getImage("Crab");
	}

}
