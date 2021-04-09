package Quests;

import Quests.Quest;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Obstacles.*;
import Items.*;
import Shortcuts.*;

/*
 *  Given By: Smith (Gear Shop)
 *
 *  Task: Retrieve stolen goods from thieves
 *
 *  Reward: 50 Lovis, 10 exp, Shovel
 *
 */


public class ShovelQuest extends Quest
{

	public ShovelQuest()
	{
		super("Stolen Goods", 50, 10);

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Find the thieves camp"); //      0
		tasks.add("Recover the stolen goods");//    1
		tasks.add("Return the shovel to Smith");//  2
		tasks.add("ENDQUEST");
		setTasks(tasks);
	}


	public void activate()
	{
		Person s = Finder.findPerson("Smith");
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("I hope someone finds those thieves...");
		lines.add("Oh yeah! Here's what's for sale:");
		lines.add("SHOP");
		s.setLines(lines);

	}

	public void event(VerilBaseObject vbo)
	{
		if (curTask() == 1)
		{
			if (vbo instanceof Chest)
			{
				if (((Chest) vbo).getContents().getType() == Item.TYPE_SHOVEL)
				{
					setUpdated(true);
					completeTask();
					Person s = Finder.findPerson("Smith");
					ArrayList<String> lines = new ArrayList<String>();
					lines.add("You found one of my shovels!");
					lines.add("...");
					lines.add("It's the only thing you found?");
					lines.add("*sigh*");
					lines.add("Oh well, I guess my gear's gone for good.");
					lines.add("You can keep the shovel.");
					lines.add("In fact, here's 50 Lovi's for looking for me.");
					s.setLines(lines);
				}
			}
		}
		else if (curTask() == 2)
		{
			if (vbo instanceof Person)
			{
				Person s = (Person) vbo;
				if (s.getName().equals("Smith"))
				{
					setUpdated(true);
					completeTask();
					ArrayList<String> lines = new ArrayList<String>();
					lines.add("Welcome to the Gear Shop!");
					lines.add("SHOP");
					s.setLines(lines);
				}
			}
		}
	}

	public void roomChange(Room r)
	{
		if (curTask() == 0)
		{
			if (r.getWorld().getWorldNum() == 0)
			{
				if (r.getWorld().getLocOfRoom(r).equals(new Location(3,4)))
				{
					completeTask();
					setUpdated(true);
					new ObstacleAdder(r.getWorld()).addChest(new Location(4,5), new Location(5,2), new Item(Item.TYPE_SHOVEL));
				}
			}

		}
	}
}
