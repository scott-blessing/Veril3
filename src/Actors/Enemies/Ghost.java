package Actors.Enemies;

import Actors.*;
import Rooms.*;
import Obstacles.*;
import Items.Arrow;
import javax.swing.ImageIcon;


public class Ghost extends Enemy
{
	public static final int BASE_HEALTH = 8;
	public static final int BASE_ATTACK = 3;

	public Ghost(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 5, roomLoc, false, w);
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
		int r = (int) (Math.random() * 8);
		if (r < 4)
			setDirection(r);
		else if (canMove())
			move();
	}

	public boolean canMove()
	{
		Location locInFront = getLocation().nextSquare(getDirection());
		if (locInFront == null)
			return false;

		Square sqInFront = getWorld().getRoom(getRoomLoc()).getSquare(locInFront);
		if (sqInFront == null)
			return false;

		VerilBaseObject obj = getWorld().getRoom(getRoomLoc()).getObjectAt(getLocation().nextSquare(getDirection()));

		if (obj instanceof Obstacle)
		{
			return false;
		}
		if (obj instanceof Actor)
		{
			return false;
		}
		return true;
	}

	public ImageIcon getImage()
	{
		return getImage("Ghost");
	}
}