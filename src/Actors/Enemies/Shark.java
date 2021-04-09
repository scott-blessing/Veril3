package Actors.Enemies;

import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;
import Obstacles.Obstacle;

public class Shark extends Enemy
{
	public static final int BASE_HEALTH = 6;
	public static final int BASE_ATTACK = 4;

	public Shark(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 7, roomLoc, true, w);
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

		Square sqInFront = getRoom().getSquare(locInFront);
		if (sqInFront == null)
			return false;

		VerilBaseObject obj = getRoom().getObjectAt(getLocation().nextSquare(getDirection()));

		if (obj == null)
			return sqInFront.isWater();

		if (obj instanceof Obstacle)
		{
			Obstacle o = (Obstacle) obj;
			if (o.isWalkable() == false)
				return false;
		}
		if (obj instanceof Actor)
		{
			return false;
		}
		return sqInFront.isWater();
	}

	public ImageIcon getImage()
	{
		return getImage("Shark");
	}
}
