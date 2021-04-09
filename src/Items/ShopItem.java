package Items;

import Items.Item;
import javax.swing.ImageIcon;
import java.io.Serializable;
import GUI.ActionResult;

public class ShopItem implements Serializable
{
	private int price;
	private Item item;
	private String name;
	private String desc;
	private boolean oneTime;

	public ShopItem(Item i, int cost, String name, String desc, boolean oneTime)
	{
		item = i;
		price = cost;
		this.name = name;
		this.desc = desc;
		this.oneTime = oneTime;
	}

	public Item getItem()
	{
		return item;
	}
	public int getPrice()
	{
		return price;
	}
	public ImageIcon getImage()
	{
		return item.getImage();
	}
	public String getName()
	{
		return name;
	}
	public String getDesc()
	{
		return desc;
	}
	public boolean isOneTime()
	{
		return oneTime;
	}
}
