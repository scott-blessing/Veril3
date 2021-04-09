package Actors.Enemies.Bosses;

import Actors.Enemies.Bosses.Boss;
import Actors.Enemy;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Items.Arrow;
import GUI.Inventory;
import javax.swing.ImageIcon;
import Shortcuts.Finder;

public class Haskell1 extends Boss
{
	public static final int BASE_HEALTH = 20;
	public static final int BASE_ATTACK = 2;
	public static final String NAME = "Haskell";

	public Haskell1(Location roomLoc, Location square, World w)
	{
		super(Actor.DIRECTION_DOWN, BASE_HEALTH, BASE_ATTACK, 12, roomLoc, square, w, NAME, "22 22 22 22 22 22 22 63 63 22 22 22 22 22 22 22 22 22 29 29 29 22 22 29 29 22 22 22 29 29 22 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 99 99 29 29 29 29 29 29 29 29 99 99 99 99 22 22 99 99 99 99 99 99 55 55 99 99 99 99 99 99 22 22 99 99 99 99 99 99 55 55 99 99 99 99 99 99 22 22 99 99 99 99 99 99 55 55 99 99 99 99 99 99 22 22 99 99 99 99 99 99 55 55 99 99 99 99 99 99 22 22 99 99 99 99 99 99 55 55 99 99 99 99 99 99 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 ");
		ArrayList<String> intro = new ArrayList<String>();
		ArrayList<String> death = new ArrayList<String>();
		intro.add("Ah. Hello Veril.");
		intro.add("Have you enjoyed all of the beautiful environments I've drawn for you?");
		intro.add("And the vivid enemies as well?");
		intro.add("It's too bad the only graphics you have yet to see...");
		intro.add("Is the DEATH ANIMATION!");
		death.add("You haven't seen the last of me!");
		death.add("I will draw newer, more powerful enemies to kill you at every turn!");
		death.add("And I will awaken all of the other programmers to crush you like a bug!");
		setIntroLines(intro);
		setDeathLines(death);
	}

	public void act()
	{
		if (canSeeVeril())
			shootBow(Arrow.PENCIL);
		else
		{
			int r = (int) (Math.random() * 5);
			if (r == 0)
				shootBow(Arrow.PENCIL);
			else if (r < 3)
				doMove();
		}

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

	public int getEXPValue()
	{
		return 25;
	}

	public void causeDeathEffects()
	{
		Person p = Finder.findPerson("Village Elder");
		ArrayList<String> l = new ArrayList<String>();
		l.add("So, did you kill the graphics artist?");
		l.add("...so it seems he has escaped.");
		l.add("He must be planning to gather his friends!");
		l.add("The more of the he alerts, the more powerful the programmer will become!");
		l.add("Veril, you must defeat the graphic artist and all of his friends if you ever hope to bring down the programmer's tyranny!");
		l.add("Oh my... What is that you have?");
		l.add("In all my years I've never seen such a thing!");
		l.add("You managed to find a Code Snippet?");
		l.add("Code Snippets are leftover pieces of code that the programmer accidentally forgot to comment out.");
		l.add("There have been rumors of fragmented pieces lying about all over Loviant, but that one you found is large.");
		l.add("Perhaps, because Haskell was guarding it, it may be part of an entire line of code!");
		l.add("If you find all of the pieces, you may be able to use it against the programmer!");
		l.add("It looks like there's 5 missing segments to this line of code.");
		l.add("Once you find the remaining five Code Snippets, you will truly be ready to face the programmer!");
		l.add("Go now Veril!  The fate of Loviant is in your hands!");
		l.add("I suggest you first go to the forest, there is a small village there where the people may be willing to help.");
		p.setLines(l);
	}

	public ImageIcon getImage()
	{
		return getImage("Haskell1");
	}
}
