package Actors.Enemies.Bosses;

import Actors.Enemies.Bosses.Boss;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Items.Arrow;
import GUI.Inventory;
import javax.swing.ImageIcon;
import Obstacles.*;

public class Kelch extends Boss
{
	public static final int BASE_HEALTH = 300;
	public static final int BASE_ATTACK = 4;
	public static final String NAME = "Kelch";

	public Location prevLoc;

	public Kelch(Location room, Location square, World w)
	{
		super(Actor.DIRECTION_DOWN, BASE_HEALTH, BASE_ATTACK, 8, room, square, w, NAME, "46 46 48 46 46 46 46 47 47 46 46 46 46 48 46 46 46 48 46 46 29 29 29 29 29 29 29 29 46 46 48 46 48 46 46 29 29 29 29 29 29 29 29 29 29 46 46 48 46 46 46 29 29 47 47 29 29 47 47 29 29 46 46 46 48 46 29 29 29 47 47 29 29 47 47 29 29 29 46 48 46 46 29 29 47 47 29 29 29 29 47 47 29 29 46 46 48 46 29 29 47 29 29 29 29 29 29 47 29 29 46 48 48 46 29 29 47 29 29 29 29 29 29 47 29 29 46 46 48 46 46 29 29 29 29 29 29 29 29 29 29 46 46 48 48 46 46 29 29 46 29 29 29 29 46 29 29 46 46 48 46 48 46 46 46 46 46 46 55 46 46 46 46 46 48 46 46 46 48 46 48 46 46 46 55 46 46 46 48 46 46 46 ");
		ArrayList<String> inLines = new ArrayList<String>();
		ArrayList<String> outLines = new ArrayList<String>();

		inLines.add("I AM LIVING HELLFIRE!");
		inLines.add("I WILL MELT YOUR BONES AND FRY YOUR SKIN!");

		outLines.add("AAAGGGGGGGHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH!");
		outLines.add("...");
		outLines.add("...");
		outLines.add("... I'm... I'm free...");
		outLines.add("... You?  Freed me?");
		outLines.add("...");
		outLines.add("Thank you.");
		outLines.add("I was once a programmer for this game...");
		outLines.add("Covered with the burden of creating an engine, the programmer Scott charged me with creating a level editor.");
		outLines.add("I completed the editor, just as he asked.  Yet he betrayed me!");
		outLines.add("Once he no longer felt I was needed, he used a line of code to set my soul equal to null.");
		outLines.add("With me out of the way, he could delete my name from ever appearing on the credits...");
		outLines.add("But thanks to you, I have a chance!");
		outLines.add("Once you take my Code Snippet, there will only be one piece left.");
		outLines.add("But Scott will be guarding it personally.");
		outLines.add("He knows you will be able to defeat him if you complete the line of code.");
		outLines.add("The last Code Snippet can be found on the Lost Island.");
		outLines.add("The only way to get to the Lost Island is by boat, so I suggest you ask around in Pierport.");
		outLines.add("Hopefully you can bring an end to Scott's reign of terror in Loviant.");

		setIntroLines(inLines);
		setDeathLines(outLines);

		prevLoc = square;
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
                int r = (int) (Math.random() * 25);
                if (r <= 3)
                {    
                    int d = getDirection();
                    setDirection(0);shootBow(Arrow.FIRE);
                    setDirection(1);shootBow(Arrow.FIRE);
                    setDirection(2);shootBow(Arrow.FIRE);
                    setDirection(3);shootBow(Arrow.FIRE);
                    setDirection(d);
                }
                else if (r <= 10)
                    shootBow(Arrow.FIRE);
                else
                    doMove();
            }
	}

	public void causeDeathEffects()
	{

	}

	public int getEXPValue()
	{
		return 65;
	}

	public void doMove()
	{
		int r = (int) (Math.random() * 8);
		if (r < 4)
			setDirection(r);
		else if (canMove())
			move();
	}

	public void move()
	{
		prevLoc = new Location(getLocation().getX(),getLocation().getY());
		super.move();
	}

	public void doMovementActions()
	{
		if (!prevLoc.equals(getLocation()) && getRoom().getObjectAt(prevLoc) == null)
			getWorld().add(new Fire((int) Math.random()*5 + 10, false, getRoomLoc(), prevLoc, getWorld()), getRoomLoc());
	}

	public void hitByFire()
	{
		loseHealth(-1);
	}

	public ImageIcon getImage()
	{
		return getImage("Kelch");
	}
}
