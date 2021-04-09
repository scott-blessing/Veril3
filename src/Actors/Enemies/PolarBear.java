package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;
import Items.Arrow;


public class PolarBear extends Enemy
{
	public static final int BASE_HEALTH = 10;
	public static final int BASE_ATTACK = 3;

	public PolarBear(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 9, roomLoc, false, w);
	}

	public void act()
	{
		if (canAttack())
			attack(getRoom().getVeril());
		else
			doMove();
	}

	public void doMove()
	{
		if (within(2))
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

	public int directionTowardsVeril()
	{
		Location L = getRoom().getVeril().getLocation();
		int x = L.getX() - getLocation().getX();
		int y = L.getY() - getLocation().getY();
		if (x < 0)
			return Actor.DIRECTION_LEFT;
		else if (x > 0)
			return Actor.DIRECTION_RIGHT;
		else if (y < 0)
			return Actor.DIRECTION_UP;
		else
			return Actor.DIRECTION_DOWN;
	}


	public ImageIcon getImage()
	{
		return getImage("PolarBear");
	}
}