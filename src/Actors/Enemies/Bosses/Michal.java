package Actors.Enemies.Bosses;

import Actors.Enemies.Bosses.Boss;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Items.Arrow;
import GUI.Inventory;
import javax.swing.ImageIcon;
import Shortcuts.EnemyAdder;
import Shortcuts.Finder;

public class Michal extends Boss
{
	public static final int BASE_HEALTH = 120;
	public static final int BASE_ATTACK = 3;
	public static final String NAME = "Jake";

	public Michal(Location room, Location square, World w)
	{
		super(Actor.DIRECTION_DOWN, BASE_HEALTH, BASE_ATTACK, 4, room, square, w, NAME, "49 49 49 49 49 49 49 88 88 49 49 49 49 49 49 49 49 49 88 88 88 88 88 88 88 88 88 88 88 88 49 49 49 88 88 88 88 88 88 88 88 88 88 88 88 88 88 49 49 88 88 88 88 88 88 88 88 88 88 88 88 88 88 49 49 88 88 88 88 81 88 88 88 88 81 88 88 88 88 49 49 88 88 88 88 88 88 88 88 88 88 88 88 88 88 49 49 88 88 88 88 88 88 88 88 88 88 88 88 88 88 49 49 88 88 88 88 81 88 88 88 88 81 88 88 88 88 49 49 88 88 88 88 88 88 88 88 88 88 88 88 88 88 49 49 88 88 88 88 88 88 88 88 88 88 88 88 88 88 49 49 49 88 88 88 88 88 88 88 88 88 88 88 88 49 49 49 49 49 49 49 49 49 49 88 49 49 49 49 49 49 49 ");
		ArrayList<String> inLines = new ArrayList<String>();
		ArrayList<String> outLines = new ArrayList<String>();
		inLines.add("So, another piece of code has come to challenge my authority!");
		inLines.add("You are foolish!  The programmer himself gave me these magical powers, and I will use them as I see fit!");
		outLines.add("Impossible!");
		outLines.add("Inconsieveable!");
		outLines.add("I am the King of Hidden Valley!");
		outLines.add("This land cannot exist without my magic!");
		outLines.add("I curse you Veril!");
		outLines.add("May your cold heart reflect itself on the world above.");
		setIntroLines(inLines);
		setDeathLines(outLines);
	}

	public void act()
	{
		if (canSeeVeril())
			shootBow(Arrow.FIRE);
		else if (within(4))
		{
			int dir = directionTowardsVeril();
			if (getDirection() == dir && canMove())
				move();
			else
				setDirection(dir);
		}
		else
		{
			int r = (int) (Math.random() * 30);
			if (r <= 5)
				shootBow(Arrow.FIRE);
			else
				doMove();
		}
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
		return 40;
	}

	public void causeDeathEffects()
	{
            //Block entrance to Hidden Valley
            Finder.findPerson("Trent").getWorld().getRoom(new Location(0,2)).changeTo("22 22 22 22 22 22 63 22 22 22 22 22 22 89 43 43 22 22 22 22 22 22 63 22 22 22 22 22 43 43 43 43 22 22 22 22 22 22 63 22 22 22 22 43 43 43 43 89 22 22 22 22 22 22 63 22 22 22 43 43 89 43 43 43 22 22 22 22 22 22 63 22 22 43 21 43 43 43 89 43 22 22 22 22 22 22 63 22 21 21 43 21 89 43 43 43 22 22 22 43 43 21 21 89 43 21 21 89 21 43 43 89 22 43 43 43 89 89 21 21 89 21 89 43 89 43 43 43 43 43 89 21 89 21 21 89 43 21 89 21 21 21 89 43 43 89 43 21 43 89 89 21 21 43 21 21 89 89 43 43 43 43 43 89 21 89 21 89 89 21 89 89 21 43 89 43 89 43 89 43 21 89 89 21 89 89 43 89 89 21 43 43 ");
	}

	public ImageIcon getImage()
	{
		return getImage("Michal");
	}
}
