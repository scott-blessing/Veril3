package GUI;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class Sprite
{
	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	public static BufferedImage sprite = getSpriteSheet();
	public static BufferedImage[] images = getImageArray();

	public static BufferedImage getImage(int index)
	{
		JPanel imageObserver = new JPanel();
		BufferedImage img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		int y = (index/10)*HEIGHT;
		int x = (index%10)*WIDTH;
		img.getGraphics().drawImage(sprite,0,0,WIDTH,HEIGHT,x,y,x+WIDTH,y+HEIGHT,imageObserver);
		//(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
		return img;

	}
	public static BufferedImage getRenderedImage(int index)
	{
		return images[index];
	}
	public static BufferedImage getSpriteSheet()
	{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images/MapTiles/Tiles.png"));
		} catch (IOException e)
		{
		}
		return img;

	}
	public static BufferedImage[] getImageArray()
	{
		BufferedImage[] imageArray = new BufferedImage[100];
		for(int i=0;i<imageArray.length;i++)
			imageArray[i] = getImage(i);
		return imageArray;
	}
}

