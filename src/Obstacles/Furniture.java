package Obstacles;

import Obstacles.Obstacle;
import Rooms.*;
import Actors.*;
import GUI.*;
import Items.*;
import javax.swing.ImageIcon;

public class Furniture extends Obstacle
{
	public static final int DRAWER = 0;
	public static final int DRESSER_L = 1;
	public static final int DRESSER_R = 2;
	public static final int BED_U = 3;
	public static final int BED_D = 4;
	public static final int BOOKSHELF = 5;
	public static final int MAGICSHELF = 6;
	public static final int ARMOR = 7;
	public static final int SHOPCOUNTER = 8;
	public static final int PLANT = 9;
	public static final int FIREPLACE = 10;
	public static final int CAMPFIRE = 11;

	private int FType;
	private Item contents = null;
	private int drawerType;

	public Furniture(int FType, Location room, Location square, World w)
	{
		super(Obstacle.TYPE_FURNITURE, room, square, w);
		this.FType = FType;
		setDrawer();
	}
	public Furniture(int FType, Item pickUp, Location room, Location square, World w)
	{
		super(Obstacle.TYPE_FURNITURE, room, square, w);
		this.FType = FType;
		contents = pickUp;
		setDrawer();
	}

	public int getFType()
	{
		return FType;
	}
	public Item getContents()
	{
		return contents;
	}

	private void setDrawer()
	{
		if (FType == DRAWER)
		{
			drawerType = (int)(Math.random() * 3);
		}
	}

	public ActionResult open(Veril v)
	{
		if (FType == DRAWER)
		{
			contents.pickUp(v);
			ActionResult a = new ActionResult(contents.getMessage(), contents.getImage());
			contents = null;
			return a;
		}
		return null;
	}
	public ActionResult interact(Equipment e, Veril v)
	{
		if (e.getType() == Equipment.TYPE_NONE)
		{
			if (FType == DRAWER && contents != null)
				return open(v);
			else if (FType == SHOPCOUNTER)
			{
				Location L = getLocation().nextSquare(v.getDirection());
				VerilBaseObject P = getRoom().getObjectAt(L);
				if (P instanceof Person)
					return new ActionResult(((Person) P).getNextLine(v));
			}
		}

		return null;
	}

	public ImageIcon getImage()
	{
		String fp = "images/Furniture/";
		if (FType == DRAWER)
			fp += "Drawer"+drawerType;
		else if (FType == DRESSER_L)
			fp += "DresserL";
		else if (FType == DRESSER_R)
			fp += "DresserR";
		else if (FType == BED_U)
			fp += "BedU";
		else if (FType == BED_D)
			fp += "BedD";
		else if (FType == BOOKSHELF)
			fp += "Bookshelf";
		else if (FType == MAGICSHELF)
			fp += "Magicshelf";
		else if (FType == ARMOR)
			fp += "SuitOfArmor";
		else if (FType == SHOPCOUNTER)
			fp += "ShopCounter";
		else if (FType == PLANT)
			fp += "Plant";
		else if (FType == FIREPLACE)
			fp += "Fireplace";
		else if (FType == CAMPFIRE)
			fp += "Campfire";

		fp += ".png";
		return new ImageIcon(fp);
	}

}
