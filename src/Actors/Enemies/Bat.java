package Actors.Enemies;

import Actors.*;
import Rooms.*;
import Obstacles.*;
import javax.swing.ImageIcon;


public class Bat extends Enemy
{
	public static final int BASE_HEALTH = 4;
	public static final int BASE_ATTACK = 1;

	public Bat(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 8, roomLoc, false, w);
	}

	public void act()
	{
		if (canAttack())
			attack(getWorld().getRoom(getRoomLoc()).getVeril());
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
			Obstacle o = (Obstacle) obj;
			return o.isWalkable();
		}
		if (obj instanceof Actor)
		{
			return false;
		}
		return sqInFront.isFlyable();
	}

	public ImageIcon getImage()
	{
		return getImage("Bat");
	}
}
