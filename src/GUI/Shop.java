package GUI;

import Items.ShopItem;
import java.util.ArrayList;
import Actors.Veril;
import java.io.Serializable;

public class Shop implements Serializable
{
	private ArrayList<ShopItem> stock;

	public Shop(ArrayList<ShopItem> myStock)
	{
		stock = myStock;
	}
	public Shop()
	{
		stock = new ArrayList<ShopItem>();
	}

	public void add(ShopItem s)
	{
		stock.add(s);
	}

	public ArrayList<ShopItem> getAllItems()
	{
		return stock;
	}

	public void buyItem(Veril v, int itemNum)
	{
		v.getInventory().loseMoney(stock.get(itemNum).getPrice());
		stock.get(itemNum).getItem().pickUp(v);
		if (stock.get(itemNum).isOneTime())
			removeItem(itemNum);
	}
	public void buyItem(Veril v, ShopItem item)
	{
		v.getInventory().loseMoney(item.getPrice());
		item.getItem().pickUp(v);
		if (item.isOneTime())
			removeItem(item);
	}

	public ShopItem getItem(int num)
	{
		if (num >= stock.size() || num < 0)
			return null;
		else
			return stock.get(num);
	}

	public void removeItem(int num)
	{
		stock.remove(num);
	}
	public void removeItem(ShopItem item)
	{
		stock.remove(item);
	}

	public int getStockSize()
	{
		return stock.size();
	}
}
