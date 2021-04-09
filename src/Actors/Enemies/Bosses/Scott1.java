package Actors.Enemies.Bosses;

import Actors.Enemies.Bosses.Boss;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Items.Arrow;
import GUI.Inventory;
import javax.swing.ImageIcon;
import Obstacles.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Scott1 extends Boss
{
	public static final int BASE_HEALTH = 450;
	public static final int BASE_ATTACK = 4;
	public static final String NAME = "Scott";

	public Timer waveTimer;
	public int wavePattern = 0;
	public Location waveStart;
	public int waveDir = 0;
	public int waveLength = 0;


	public Scott1(Location room, Location square, World w)
	{
            super(Actor.DIRECTION_DOWN, BASE_HEALTH, BASE_ATTACK, 6, room, square, w, NAME, "22 22 22 22 22 22 35 35 35 35 22 22 22 22 22 22 22 22 11 11 11 11 35 35 35 35 11 11 11 11 22 22 22 11 11 11 11 11 36 36 36 36 11 11 11 11 11 22 22 11 11 11 11 11 11 11 11 11 11 11 11 11 11 22 22 11 11 11 11 11 11 11 11 11 11 11 11 11 11 22 22 11 11 11 11 20 11 11 11 11 20 11 11 11 11 22 22 11 11 11 11 11 11 11 11 11 11 11 11 11 11 22 22 11 11 11 11 11 11 11 11 11 11 11 11 11 11 22 22 11 11 11 11 11 11 11 11 11 11 11 11 11 11 22 22 11 11 11 11 11 11 11 11 11 11 11 11 11 11 22 22 22 11 11 11 11 11 11 11 11 11 11 11 11 22 22 22 22 22 22 22 22 22 22 11 22 22 22 22 22 22 22 ");
            setWaterBased(true);

            ArrayList<String> inLines = new ArrayList<String>();
            ArrayList<String> outLines = new ArrayList<String>();

            inLines.add("Welcome Veril.");
            inLines.add("I'm glad you made it this far.");
            inLines.add("Thanks to my expert level design, I've led you straight into my trap.");
            inLines.add("Take this!");
            inLines.add("world[7].room[6][3].actors_ver.veril_death_animation.start();");
            inLines.add("...");
            inLines.add("ERROR - CONCURRENT MODIFICATION EXCEPTION (death.java::372)");
            inLines.add("Shit.");
            inLines.add("It seems I may have to deal with you personally.");

            outLines.add("How could this happen?");
            outLines.add("I gave myself more HP than anything in this game!");
            outLines.add("You can have this code snippet.  I have no use for it.");
            outLines.add("Besides, the only way to execute that code is in the command prompt.");
            outLines.add("Which I keep with me at the top of my tower!");
            outLines.add("I'll see you again Veril.  But next time, I will personally toss you off my balcony!");

            setIntroLines(inLines);
            setDeathLines(outLines);

            waveTimer = new Timer(300, new waveListener());
            wavePattern = 0;
	}

	public void act()
	{
		int r = (int) (Math.random() * 7);
			if (r <= 3 && !waveTimer.isRunning())
				startWaveAttack(r%2);

		if (canAttack())
			attack(getRoom().getVeril());
		else if (within(5))
		{
			int dir = directionTowardsVeril();
			if (getDirection() == dir && canMove())
				move();
			else
				setDirection(dir);
		}
		else
		{
				doMove();
		}
	}

	private void startWaveAttack(int n)
	{
		wavePattern = n;
		waveLength = 0;
		waveStart = new Location(getLocation().getX(), getLocation().getY()).nextSquare(getDirection());
		waveDir = getDirection();
		waveTimer.start();
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
		return 75;
	}

	public ImageIcon getImage()
	{
		return getImage("Scott1");
	}

	public void causeDeathEffects()
	{

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


	class waveListener implements ActionListener
	{
		private int buildDirA, buildDirB;

		public void actionPerformed(ActionEvent e)
		{
			buildDirA = waveDir+1;
			if (buildDirA == 4) buildDirA = 0;
			buildDirB = waveDir-1;
			if (buildDirB == -1) buildDirB = 3;

			waveLength++;

			if (wavePattern == 0)
				doWavePattern0();
			else
				doWavePattern1();
		}

		public void doWavePattern0()
		{
			int n = waveLength - 1;
			if (n == 0)  n=1;

			Location l = new Location(waveStart.getX(), waveStart.getY());

			for (int i = 0; i < waveLength*2-1; i++)
			{
				if (l!=null)
				{
					if (i%n==0)
					{
						if (getRoom().getSquare(l).isWater())
								getWorld().add(new Whirlpool((int) (Math.random()*5 + 5), getRoomLoc(), l, getWorld()), getRoomLoc());
					}
				}
				else
					break;

				l = l.nextSquare(buildDirA);
			}

			waveStart = waveStart.nextSquare(waveDir);
			if (waveStart == null)
			{
				waveTimer.stop();
			}
			else
			{
				waveStart = waveStart.nextSquare(buildDirB);
				if (waveStart == null)
					waveTimer.stop();
			}
		}

		public void doWavePattern1()
		{
			if (waveStart!=null)
				if (getRoom().getSquare(waveStart).isWater())
					getWorld().add(new Whirlpool((int) (Math.random()*5 + 5), getRoomLoc(), waveStart, getWorld()), getRoomLoc());

			waveStart = waveStart.nextSquare(waveDir);
			if (waveStart == null)
				waveTimer.stop();
		}
	}
}
