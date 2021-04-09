package Actors.Enemies.Bosses;

import Actors.Enemy;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import GUI.Inventory;
import javax.swing.ImageIcon;

public abstract class Boss extends Enemy
{
	private String name;
	private ArrayList<String> introLines;
	private ArrayList<String> deathLines;
	private int lineCnt;
	private String mapDeathChange;

	public Boss(int dir, int heal, int att, int moveLim, Location roomLoc, Location square, World w, String name, String newMap)
	{
		super(dir, heal, att, 1, moveLim, square, roomLoc, false, w);
		this.name = name;
		lineCnt = 0;
		mapDeathChange = newMap;
	}

	public void setIntroLines(ArrayList<String> in)
	{
		introLines = in;
	}
	public void setDeathLines(ArrayList<String> dea)
	{
		deathLines = dea;
	}

	public String getInLine()
	{
		String line = null;
		if (lineCnt < introLines.size())
		{
			line = name + ": " + introLines.get(lineCnt);
			lineCnt++;
		}
		else if (lineCnt != 100)
		{
			lineCnt = 50;
		}
		return line;
	}
	public String getOutLine()
	{
		String line = null;
		if ((lineCnt-50) < deathLines.size() && (lineCnt-50) >= 0)
		{
			line = name + ": " + deathLines.get(lineCnt-50);
			lineCnt++;
		}
		else
			lineCnt = 100;
		return line;
	}

	public String getName()
	{
		return name;
	}

	public String getMapChange()
	{
		return mapDeathChange;
	}

	public void causeDeathEffects(){}
	public void doMovementActions(){}
}
