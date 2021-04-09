package Actors.Enemies.Bosses;

import Actors.Enemies.Bosses.Boss;
import Actors.Enemy;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Items.Arrow;
import GUI.Inventory;
import javax.swing.ImageIcon;

public class Nick extends Boss
{
	public static final int BASE_HEALTH = 200;
	public static final int BASE_ATTACK = 4;
	public static final String NAME = "NegaVeril";

	public Nick(Location roomLoc, Location square, World w)
	{
		super(Actor.DIRECTION_DOWN, BASE_HEALTH, BASE_ATTACK, 3, roomLoc, square, w, NAME, "04 04 04 04 04 04 04 61 61 04 04 04 04 04 04 04 04 04 61 61 61 61 61 61 61 61 61 61 61 61 04 04 04 61 61 61 61 61 61 61 61 61 61 61 61 61 61 04 04 61 61 61 58 58 58 58 58 58 58 58 61 61 61 04 04 61 61 58 58 58 58 58 58 58 58 58 58 61 61 04 04 61 61 58 58 58 58 58 58 58 58 58 58 61 61 04 04 61 61 58 58 58 58 58 58 58 58 58 58 61 61 04 04 61 61 58 58 58 58 58 58 58 58 58 58 61 61 04 04 61 61 61 58 58 58 58 58 58 58 58 61 61 61 04 04 61 61 61 61 61 61 61 61 61 61 61 61 61 61 04 04 04 61 61 61 61 61 61 61 61 61 61 61 61 04 04 04 04 04 04 04 04 04 61 04 04 04 04 04 04 04 04 ");
		ArrayList<String> intro = new ArrayList<String>();
		ArrayList<String> death = new ArrayList<String>();
		intro.add("...");
		intro.add("..");
		intro.add(".");
		death.add("...");
		death.add("..");
		death.add(".");
		death.add("...If you wish to rekindle your revenge...");
		death.add("...Seek the rogue coder in the volcano...");
		death.add("...The burning man will know...");
		death.add("...Where you can find The Programmer...");
		setIntroLines(intro);
		setDeathLines(death);
	}

	public void act()
	{
		Veril v = getWorld().getRoom(getRoomLoc()).getVeril();
		setMoveLimit(5);
		if (canAttack())
			attack(v);
		else if (Math.random() < .10 && !v.isFrozen())
		{
			for (int i = 0; i < 4; i++)
			{
				setDirection(i);
				shootBow(Arrow.ICE);
			}
		}
		else if (within(12))
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
		int r = (int) (Math.random() * 10);
		if (r < 4)
			setDirection(r);
		else if (canMove())
			move();
	}

	public int getEXPValue()
	{
		return 50;
	}

	public void hitByFire(){/*0 damage*/}

	public void causeDeathEffects()
	{

	}

	public ImageIcon getImage()
	{
		return getImage("Nick");
	}
}
