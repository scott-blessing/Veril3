package Quests;

import Quests.Quest;
import java.util.ArrayList;
import Actors.*;
import Actors.Enemies.Bosses.Boss;
import Rooms.*;
import Obstacles.*;
import Items.*;
import Shortcuts.*;
import GUI.Veril3GameScreenFrame;

/*
 *  Given By: Alexandra (Snowman Lady)
 *
 *  Task: Kill the White Witch
 *
 *  Reward: 0 Lovis, 50 exp, L2 Sword
 *
 */


public class NarniaQuest extends Quest
{

	public NarniaQuest()
	{
		super("Winter Magic", 0, 50);

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Find a magical hat"); //      0
		tasks.add("Bring the hat the Alexandra");//    1
		tasks.add("Give the hat to a unique snowman");//  2
		tasks.add("Find out where you are");//3
		tasks.add("Kill the White Witch");//4
		tasks.add("Be praised by the people of Varnia");//5
		tasks.add("ENDQUEST");
		setTasks(tasks);
	}


	public void activate()
	{
		Veril3GameScreenFrame.world[0].add(new Item(Item.TYPE_HAT, new Location(1,2), new Location(12,3), Veril3GameScreenFrame.world[0]), new Location(1,2));
	}

	public void event(VerilBaseObject vbo)
	{
		////////////////////////////////////////////////////////////////
		if (curTask() == 0)//Find the hat
		{
			if (vbo instanceof Item)
			{
				if (((Item) vbo).getType() == Item.TYPE_HAT)
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Oh my!  Where did you get that hat from?");
					l.add("That's exactly the type of hat that brings snowmen to life!");
					l.add("Can I please put it on his head?");
					l.add("Oh thank you! Let's see what happens!");
					l.add("...");
					l.add("...");
					l.add("Oh...");
					l.add("Oh well, I guess this snowman just isn't unique enough");
					l.add("You know what, you take this hat.");
					l.add("If you come across a snowman that's truely unique, please try the hat again.");
					Finder.findPerson("Alexandra").setLines(l);
				}
			}
		}
		////////////////////////////////////////////////////////////////
		if (curTask() == 1)//return to Alexandra
		{
			if (vbo instanceof Person)
			{
				Person s = (Person) vbo;
				if (s.getName().equals("Alexandra"))
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Don't worry, I'm sure I'll find magic some day...");
					s.setLines(l);
					l = new ArrayList<String>();
					l.add("...");
					l.add("Why hello my good lad!");
					l.add("I'm sure glad you returned my hat to me!");
					l.add("As thanks, I'll bring you to a land of magic and happiness!");
					Finder.findPerson("Snowman").setLines(l);
				}
			}
		}
		////////////////////////////////////////////////////////////////
		if (curTask() == 2)//return to Alexandra
		{
			if (vbo instanceof Person)
			{
				Person s = (Person) vbo;
				if (s.getName().equals("Snowman"))
				{
					completeTask();
					s.getRoom().getVeril().setRoomLoc(new Location(0,2));
					s.getRoom().getVeril().setLocation(new Location(13,4));
					s.getRoom().getVeril().setWorld(Veril3GameScreenFrame.world[12]);
					ArrayList<String> l = new ArrayList<String>();
					l.add("Did you enjoy your time in Varnia?");
					s.setLines(l);
					s.setCurLine(0);
				}
			}
		}
		////////////////////////////////////////////////////////////////
		if (curTask() == 3)//find out where you are
			if (vbo instanceof Person)
				if (((Person) vbo).getName().equals("Honey the Badger"))
					completeTask();
		////////////////////////////////////////////////////////////////
		if (curTask() == 4)//Kill the White Witch
			if (vbo instanceof Boss)
				completeTask();
		////////////////////////////////////////////////////////////////
		if (curTask() == 5)//Recieve praise
		{
			if (vbo instanceof Person)
				if (((Person) vbo).getName().equals("Vaslan"))
					completeTask();
			if (vbo instanceof Chest)
				completeTask();
		}
	}

	public void roomChange(Room r)
	{

	}
}
