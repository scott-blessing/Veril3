package Shortcuts;

import Rooms.*;
import Actors.*;
import Actors.Enemies.*;
import Actors.Enemies.Bosses.*;
import Items.*;
import Obstacles.*;
import Quests.*;
import java.util.ArrayList;

public class Finder
{
	private static ArrayList<Person> people;

	public Finder()
	{
		people = new ArrayList<Person>();
	}

	public static void clearList()
	{
		people = null;
		people = new ArrayList<Person>();
	}

	public static void addPerson(Person p)
	{
		people.add(p);
	}


	public static Person findPerson(String name)
	{
		for (Person p : people)
		{
			if (p.getName().equals(name))
				return p;
		}
		return null;
	}

	public static String getFullList()
	{
		String s = "";
		for (Person p : people)
		{
			s += "\tW:"+p.getWorld().getWorldNum()+" ";
			s += "\t\tR:"+p.getRoomLoc().toString();
			s += "\tS:"+p.getLocation().toString();

			if (p.getShop() != null)
				s += "\tS";
			else
				s += "\t";

			if (p.getQuest() != null)
				s += "\tQ";
			else
				s += "\t";

			s += "\t"+p.getName();

			s += "\n";
		}

		return s;
	}
}
