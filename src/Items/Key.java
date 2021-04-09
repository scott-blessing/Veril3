package Items;

import Items.Item;
import Actors.Veril;
import Rooms.*;

public class Key extends Item
{
	public static final int CIRCLE = 0;
	public static final int SQUARE = 1;
	public static final int TRIANGLE = 2;
	public static final int GATE = 3;
	public static final int SYMBOL = 4;

	public char gateValue = ' ';

	public Key(int type, Location room, Location square, World w)
	{
		super(Item.TYPE_KEY, type, room, square, w);
	}

	public Key(int type, char val, Location room, Location square, World w)
	{
		super(Item.TYPE_KEY, type, room, square, w);
		gateValue = val;
	}

	public Key(int type, char val)
	{
		super(Item.TYPE_KEY, type);
		gateValue = val;
	}

	public Key(int type)
	{
		super(Item.TYPE_KEY, type);
	}

	public char getGateValue()
	{
		return gateValue;
	}

	public void pickUp(Veril v)
	{
		v.getInventory().addKey(this);
	}

	public String getMessage()
	{
		String s = "";
		if (getVal() == CIRCLE)
			s = "Circle";
		else if (getVal() == SQUARE)
			s = "Square";
		else if (getVal() == TRIANGLE)
			s = "Triangle";
		else if (getVal() == GATE)
			s = "Gate";
		else if (getVal() == SYMBOL)
			s = "Symbol";
		return "You got a " + s + " key!";
	}
}
