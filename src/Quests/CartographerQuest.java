package Quests;

import Quests.Quest;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Shortcuts.Finder;

/*
 *  Given by man (Benny) in Map Shop
 *
 *  Veril needs to visit 101 map squares
 *     That's about it...
 *
 */


public class CartographerQuest extends Quest
{
	private int roomsExplored;



	public CartographerQuest()
	{
		super("Cartography 101", 50, 20);

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Explore 101 New Locations");
		tasks.add("Return to the Map Shop");
		tasks.add("ENDQUEST");
		setTasks(tasks);

		roomsExplored = 0;
	}




	public void activate()
	{
		Person b = Finder.findPerson("Benny");
		b.clearLines();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("Have fun exploring!");
		b.addLines(lines);
	}

	public void event(VerilBaseObject vbo)
	{
		if (curTask() == 1)
		{
			if (vbo instanceof Person)
			{
				if (((Person) vbo).getName().equals("Benny"))
				{
					setUpdated(true);
					completeTask();
				}

			}
		}
	}

	public void roomChange(Room r)
	{
		if (curTask() == 0)
		{
			if (!r.isExplored())
				roomsExplored++;

			if (roomsExplored == 101)
			{
				completeTask();
				setUpdated(true);
				Person b = Finder.findPerson("Benny");
				b.clearLines();
				ArrayList<String> lines = new ArrayList<String>();
				lines.add("Thank you laddie");
				lines.add("Now I can remember all of the amazing places I've visited");
				lines.add("Here's 50 Lovi's for your troubles");
				b.addLines(lines);
			}

		}
	}
}
