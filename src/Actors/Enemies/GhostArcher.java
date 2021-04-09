package Actors.Enemies;

import Actors.*;
import Rooms.*;
import Obstacles.*;
import Items.Arrow;
import javax.swing.ImageIcon;


public class GhostArcher extends Enemy
{
	public static final int BASE_HEALTH = 7;
	public static final int BASE_ATTACK = 1;

	public GhostArcher(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 9, roomLoc, false, w);
	}

	public void act()
	{
		if (canAttack())
			attack(getWorld().getRoom(getRoomLoc()).getVeril());
		else if (canSeeVeril())
			shootBow(Arrow.ARROW);
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
		return true;
	}

	public ImageIcon getImage()
	{
		return getImage("GhostArcher");
	}
}
