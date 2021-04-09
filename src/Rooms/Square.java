package Rooms;

import javax.swing.*;
import java.io.Serializable;
import GUI.Sprite;

public class Square implements Serializable
{
	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;

    private int num;

    public Square(int val)
    {
		num = val;
	}

	public void setNum(int newNum)
	{
		num = newNum;
	}
	public int getNum()
	{
		return num;
	}

	public boolean isWalkable()
	{
		if (num == 0)
			return true;
		if (num >= 2 && num <= 3)
			return true;
		if (num >= 5 && num <= 7)
			return true;
		if (num == 9)
			return true;
		if (num == 21)
			return true;
		if (num == 29)
			return true;
		if (num == 34)
			return true;
		if (num >= 38 && num <= 41)
			return true;
		if (num == 47)
			return true;
		if (num == 55)
			return true;
		if (num == 58)
			return true;
		if (num >= 61 && num <= 63)
			return true;
		if (num >= 66 && num <= 72)
			return true;
		if (num == 82)
			return true;
		if (num == 88)
			return true;
		if (num == 96)
			return true;
		if (num == 98)
			return true;

		return false;
	}
	public boolean isWater()
	{
		if (num >= 11 && num <= 19)
			return true;

		if (num == 35 || num == 36)
			return true;

		if (num == 95)
			return true;

		return false;
	}
	public boolean isDoor()
	{
		if (num == 6)
			return true;

		if (num == 34 || num == 38 || num == 39)
			return true;

		if (num == 71 || num == 72)
			return true;

		if (num == 82)
			return true;

		if (num == 95)
			return true;

		if (num == 98)
			return true;

		return false;
	}
	public boolean isInDoor()
	{
		if (num == 6)
			return true;
		if (num == 34)
			return true;
		if (num == 39)
			return true;
		if (num == 82)
			return true;
		return false;
	}
	public boolean isOutDoor()
	{
		if (num == 38)
			return true;
		if (num == 71)
			return true;
		if (num == 72)
			return true;
		if (num == 95)
			return true;
		if (num == 98)
			return true;
		return false;
	}
	public boolean isIce()
	{
		return num == 61;
	}
	public boolean isLava()
	{
		return num == 46;
	}
	public boolean isDiggable()
	{
		if (num == 0)
			return true;
		else if (num == 21)
			return true;
		else if (num == 40 || num == 41)
			return true;
		else if (num == 58)
			return true;
		else if (num >= 66 && num <= 68)
			return true;

		return false;
	}
	public boolean isFlyable()
	{
		if (num <= 21)
			return true;
		if (num >= 29 && num <= 38)
			return true;
		if (num >= 40)
			return true;
		return false;
	}
	private boolean isAnimated()
	{
		//Water
		if (num >= 11 && num <=20)
			return true;
		//Waterfalls
		if (num==35 || num == 36)
			return true;
		//Flowers
		if (num==40)
			return true;
		//Boat
		if (num==56||num==57)
			return true;
		//Lava
		if (num >= 46 && num <= 48)
			return true;
		//Lavafalls
		if (num==90 || num==91)
			return true;
		//01
		if (num == 96 || num == 97)
			return true;

		return false;
	}

	public ImageIcon getImage()
	{
/*		ImageIcon img;
		String fp = "images/MapTiles/PNGs/"+num;
//		if (isAnimated())
//			fp += ".gif";
//		else
			fp += ".png";
		img = new ImageIcon(fp);
		return img;*/
		return new ImageIcon(Sprite.getRenderedImage(num));
	}
}
