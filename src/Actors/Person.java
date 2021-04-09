package Actors;

import Actors.*;
import Rooms.*;
import Quests.*;
import GUI.Shop;
import GUI.AePlayWave;
import Shortcuts.Finder;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Person extends NPC
{
	public static final int TYPE_VILLAGER = 0;
	public static final int TYPE_TOWN = 1;
	public static final int TYPE_FOREST = 2;
	public static final int TYPE_DESERT = 3;
	public static final int TYPE_SNOW = 4;

	public static final int TYPE_SIGN = 5;

	public static final int TYPE_GUARD = 6;
	public static final int TYPE_MAYOR = 7;
	public static final int TYPE_OLDMAN = 8;
	public static final int TYPE_GARY = 9;
	public static final int TYPE_HB = 10;
	public static final int TYPE_LION = 11;
	public static final int TYPE_SNOWMAN = 12;

	public static final int MOVEMENT_STILL  = 0;
	public static final int MOVEMENT_CIRCLE = 1;
	public static final int MOVEMENT_LINEAR = 2;

	private int moveType;
	private int moveLength;
	private int moveDisTraveled;

	private String name;
	private boolean isMale;
	private int type;
	private int appearNum;
	private int tempDir;

	private int currentLine;
	private ArrayList<String> lines;
	private boolean talking;

	private Shop myShop;	//may be null
	private Quest myQuest;	//may be null

	public Person(int dir, int moveType, int moveLen, String myName, boolean male, int pType, Location roomLoc, Location square, World w)
	{
		super(dir,Actor.INVULNERABLE,0,roomLoc,square,w);
		this.moveType = moveType;
		tempDir = dir;
		moveLength = moveLen;
		moveDisTraveled = 0;
		name = myName;
		isMale = male;
		type = pType;
		currentLine = 0;
		lines = new ArrayList<String>();
		appearNum = (int) (Math.random() * 3) + 1;
		myShop = null;
		myQuest = null;
		if (pType != TYPE_SIGN)
			Finder.addPerson(this);
		talking = false;
	}


	public void setMoveType(int mT)
	{
		moveType = mT;
	}
	public int getMoveType()
	{
		return moveType;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public String getName()
	{
		return name;
	}

	public int getType()
	{
		return type;
	}


	public void setMoveLength(int newLen)
	{
		moveLength = newLen;
	}

//MOVEMENT
	public void act()
	{
		if (!talking)
		{
			if (getMoveType() == MOVEMENT_CIRCLE)
			{
				if (moveDisTraveled < moveLength)
				{
					if (canMove())
					{
						move();
						moveDisTraveled++;
					}
				}
				else
				{
					turn(Actor.DIRECTION_CCWISE);
					moveDisTraveled = 0;
				}
			}
			else if (getMoveType() == MOVEMENT_LINEAR)
			{
				if (moveDisTraveled < moveLength)
				{
					if (canMove())
					{
						move();
						moveDisTraveled++;
					}
				}
				else
				{
					turn(Actor.DIRECTION_CWISE);
					turn(Actor.DIRECTION_CWISE);
					moveDisTraveled = 0;
				}
			}
		}
	}

//TALKING
	public void addLine(String newLine)
	{
		lines.add(newLine);
	}
	public void addLines(ArrayList<String> newLines)
	{
		for (String s : newLines)
			lines.add(s);
	}
	public void setLines(ArrayList<String> newLines)
	{
		lines = newLines;
	}
	public String getNextLine(Veril v)
	{
		if (!talking)
			tempDir = getDirection();
		setDirection((v.getDirection()+2)%4);
		talking = true;
		String myLine = "";

		if ((lines != null && ((currentLine < lines.size() && currentLine != -1)) || (type == TYPE_SIGN)))
		{
			myLine = lines.get(currentLine);
			if (type != TYPE_SIGN)
			{
				v.startTalking();
				currentLine++;
			}
		}
		else
			currentLine = -1;


		if (myLine.equals("SHOP"))
		{
			if (myQuest != null)
			{
				v.getInventory().addQuest(myQuest);
				myQuest = null;
			}

			v.setShop(myShop);
			v.stopTalking();
			talking = false;
			setDirection(tempDir);
			currentLine--;
			return "";
		}


		if (type != TYPE_SIGN && currentLine != -1)
			return name + ": " + myLine;
		else if (currentLine == -1)
		{
			if (myQuest != null)
			{
				v.getInventory().addQuest(myQuest);
				myQuest = null;
			}
			v.stopTalking();
			talking = false;
			setDirection(tempDir);
			v.getInventory().checkQuests(this);
		}
		return myLine;
	}
	public void clearLines()
	{
		lines = new ArrayList<String>();
		currentLine = 0;
	}
	public void setCurLine(int line)
	{
		currentLine = line;
	}

//QUESTS & SHOPS
	public void giveShop(Shop s)
	{
		myShop = s;
	}
	public void giveQuest(Quest q)
	{
		myQuest = q;
	}

	public Quest getQuest()
	{
		return myQuest;
	}
	public Shop getShop()
	{
		return myShop;
	}

//TOSTRING
	public String toString()
	{
		return (name + " - A Person at " + getLocation());
	}

//GETIMAGE
	public ImageIcon getImage()
	{
		if (type == TYPE_SIGN)
		{
			if (getRoom().getSquare(getLocation()).isWalkable())
				return new ImageIcon("images/Person/Sign.png");
			else
				return new ImageIcon("images/Person/WallSign.png");
		}
		else if (type == TYPE_MAYOR)
			return new ImageIcon("images/Person/Mayor.png");
		else if (type == TYPE_OLDMAN)
			return new ImageIcon("images/Person/OldMan.png");
		else if (type == TYPE_GARY)
			return new ImageIcon("images/Person/Gary.png");
		else if (type == TYPE_HB)
			return new ImageIcon("images/Person/HB.png");
		else if (type == TYPE_LION)
			return new ImageIcon("images/Person/Lion.png");
		else if (type == TYPE_SNOWMAN)
			return new ImageIcon("images/Person/Snowman.png");

		String fp = "images/Person/";

		if (type == TYPE_VILLAGER)
			fp += "Jangbert/";
		else if (type == TYPE_TOWN)
			fp += "Pierport/";
		else if (type == TYPE_FOREST)
			fp += "Forest/";
		else if (type == TYPE_DESERT)
			fp += "Desert/";
		else if (type == TYPE_SNOW)
			fp += "Snow/";
		else if (type == TYPE_GUARD)
			fp += "Guard/";

		if (type != TYPE_GUARD)
		{
			if (isMale)
				fp += "Male/";
			else
				fp += "Female/";

			fp += appearNum + "/";
		}

		if (getDirection() == Actor.DIRECTION_DOWN)
			fp += "Down";
		else if (getDirection() == Actor.DIRECTION_UP)
			fp += "Up";
		else if (getDirection() == Actor.DIRECTION_LEFT)
			fp += "Left";
		else
			fp += "Right";

		fp += ".png";

		return new ImageIcon(fp);
	}
}
