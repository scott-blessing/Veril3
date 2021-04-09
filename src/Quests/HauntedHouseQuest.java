package Quests;

import Quests.Quest;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Obstacles.*;
import Items.*;
import Shortcuts.*;

/*
 *  Given By:
 *
 *  Task: Investigate the Haunted House
 *
 *  Reward: 50 Lovis, 50 exp, Heart Piece?
 *
 */


public class HauntedHouseQuest extends Quest
{

	public HauntedHouseQuest()
	{
		super("Ghostbuster", 50, 50);

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Find the key buried in the park"); //      0
		tasks.add("Enter the Mansion");//    1
		tasks.add("Discover the Mansion's secret");//  2
		tasks.add("ENDQUEST");
		setTasks(tasks);
	}


	public void activate()
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("I can never go back to that house...");
		l.add("You're thinking about going?");
		l.add("Well, I guess I can't stop you...");
		l.add("The gate key is buried in the center of Pierport Park");
		l.add("Don't blame me though if you don't come back out of that house.");
		Finder.findPerson("Lisa").setLines(l);
	}

	public void event(VerilBaseObject vbo)
	{
		if (curTask() == 0)//FIND THE KEY TO THE MANSION
		{
			if (vbo instanceof DigSpot)
			{
				Item i = ((DigSpot) vbo).getContents();
				if (i instanceof Key)
				{
					Key k = (Key) i;
					if (k.getGateValue() == 'a')
					{
						completeTask();
					}
				}
			}
		}

		if (curTask() == 2)///DISCOVER THE MANSION'S SECRET
			if (vbo instanceof Chest)
				if (((Chest) vbo).getContents().getType() == Item.TYPE_MIRROR)
					completeTask();
	}

	public void roomChange(Room r)
	{
		if (curTask() == 1)//ENTER THE MANSION
			if (r.getWorld().getWorldNum() == 8) //House World
				if (r.getWorld().getLocOfRoom(r).equals(new Location(10,3))) //Mansion Entrance
					completeTask();
	}
}
