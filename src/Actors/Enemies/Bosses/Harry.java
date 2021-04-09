package Actors.Enemies.Bosses;

import Actors.Enemies.Bosses.Boss;
import Actors.Enemy;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Items.Arrow;
import GUI.Inventory;
import javax.swing.ImageIcon;

public class Harry extends Boss
{
	public static final int BASE_HEALTH = 75;
	public static final int BASE_ATTACK = 2;
	public static final String NAME = "Harry";

	public Harry(Location roomLoc, Location square, World w)
	{
		super(Actor.DIRECTION_DOWN, BASE_HEALTH, BASE_ATTACK, 5, roomLoc, square, w, NAME, "22 22 22 22 22 22 22 29 29 22 22 22 22 22 22 22 22 22 22 29 29 29 29 29 29 29 29 29 29 22 22 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 29 29 22 22 29 29 29 29 22 22 29 29 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 ");
		ArrayList<String> intro = new ArrayList<String>();
		ArrayList<String> death = new ArrayList<String>();
		intro.add("Are you here to take my sticks?");
		intro.add("I've been searching my entire life for beating sticks as good as these...");
		intro.add("And I won't let you take them!");
		death.add("How?");
		death.add("How could such a wimp like you defeat me and my beating sticks?");
		death.add("I AM THE KING OF THIS FOREST!");
		death.add("I was given these lands by the programmer himself!");
		death.add("Why would he give you such power?");
		setIntroLines(intro);
		setDeathLines(death);
	}

	public void act()
	{
		Veril v = getWorld().getRoom(getRoomLoc()).getVeril();
		setMoveLimit(5);
		if (canAttack())
			attack(v);
		else if (canSeeVeril())
		{
			setMoveLimit(0);
			if (canMove())
				move();
		}
		else if (within(2))
		{
			int d = directionTowardsVeril();
			if (getDirection() == d && canMove())
				move();
			else
				setDirection(d);
		}
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

	public int getEXPValue()
	{
		return 30;
	}

	public void hitByFire()
	{
		loseHealth(2);
	}

	public void causeDeathEffects()
	{

	}

	public ImageIcon getImage()
	{
		return getImage("Harry");
	}
}
