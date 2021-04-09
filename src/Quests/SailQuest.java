package Quests;

import GUI.Veril3GameScreenFrame;
import Quests.Quest;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import Obstacles.*;
import Items.*;
import Shortcuts.*;
import GUI.Shop;

/*
 *  Given By: Cap'n
 *
 *  Task: Find a new Sail for his boat
 *
 *  Reward: 25 Lovis, 50 exp
 *
 */


public class SailQuest extends Quest
{

	public SailQuest()
	{
		super("Shipwrecked", 25, 50);

		ArrayList<String> tasks = new ArrayList<String>();
		tasks.add("Find someone in Pierport with a sail"); //      0
		tasks.add("Visit the Archaeology Society");//    1
		tasks.add("Find an ancient artifact");//  2
		tasks.add("Return the artifact to the archaeologists");// 3
		tasks.add("Visit the excavation in Summerville");// 4
		tasks.add("Find a sail");// 5
		tasks.add("Return to Cap'n");// 6
		tasks.add("ENDQUEST");// 7
		setTasks(tasks);
	}


	public void activate()
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("Me tour price ain't be cheap til yers find me a new sail!");
		l.add("SHOP");
		Finder.findPerson("Cap'n").setLines(l);
		l = new ArrayList<String>();
		l.add("You're looking for a new sail?");
		l.add("Unfortunately, I'm completely out of sails.  I've had too many boats this year already attacked by the Victory Manatee.");
		l.add("Hmmm...");
		l.add("Actually, I know where you could look!");
		l.add("Have you been to the Archaeology Society Headquarters here in Pierport?");
		l.add("I've heard they've been secretly excavating a boat.");
		l.add("Perhaps you could find a usable sail there.");
		Finder.findPerson("Scot").setLines(l);
	}

	public void event(VerilBaseObject vbo)
	{
		//////////////////////////////////////////////////////////////
		if (curTask() == 0)//TALK TO SCOT
		{
			if (vbo instanceof Person)
			{
				if (((Person) vbo).getName().equals("Scot"))
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Good luck with those archaeologists!");
					Finder.findPerson("Scot").setLines(l);
					l = new ArrayList<String>();
					l.add("You wish to visit our excavation in Summerville?");
					l.add("That dig was supposed to be a secret...");
					l.add("The short answer: No.");
					l.add("Only members of the Archaeology Society are allowed on the site.");
					l.add("...You want to join?");
					l.add("...hmm...we haven't had a new member in ages...");
					l.add("Ah! Now I remember!");
					l.add("\"In order to join the Archaeology Society, a potential member must first find and present something of historical worth.\"");
					l.add("That's what our rulebook says.");
					l.add("If you wish to join us, go find some ancient artifact.");
					l.add("I suggest you begin your search inside of ruins.");
					l.add("Those places are generally loaded with old treasures.");
					Finder.findPerson("Indy").setLines(l);
				}
			}
		}
		//////////////////////////////////////////////////////////////
		if (curTask() == 1)//VISIT THE ASHQ
		{
			if (vbo instanceof Person)
			{
				if (((Person) vbo).getName().equals("Indy"))
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Stop wasting my time and bring me something valuable!");
					Finder.findPerson("Indy").setLines(l);
					Veril3GameScreenFrame.world[0].add(new Item(Item.TYPE_ARTIFACT, new Location(7,3), new Location(7,3), Veril3GameScreenFrame.world[0]), new Location(7,3));
				}
			}
		}
		//////////////////////////////////////////////////////////////
		if (curTask() == 2)//FIND AN ARTIFACT
		{
			if (vbo instanceof Item)
			{
				if (((Item) vbo).getType() == Item.TYPE_ARTIFACT)
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Stop wasting my time and bring me something valuable!");
					l.add("...");
					l.add("You actually found something?");
					l.add("Let me see... Oh my...");
					l.add("This is indeed a rare artifact from the old kingdom");
					l.add("Where did you find it?");
					l.add("The Loviant Castle Ruins?  Of course!");
					l.add("We tried excavating there a while back but had to give up because of the scorching desert heat.");
					l.add("A find like this shows me that you are indeed a true archaeologist!");
					l.add("You are now officially a member of the Archaeology Society!");
					l.add("I'll send word to the Summerville excavation site to let them know you're a new member");
					Finder.findPerson("Indy").setLines(l);
				}
			}
		}
		//////////////////////////////////////////////////////////////
		if (curTask() == 3)//BRING THE ARTIFACT TO THE ASHQ
		{
			if (vbo instanceof Person)
			{
				if (((Person) vbo).getName().equals("Indy"))
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Congratulations new member!");
					Finder.findPerson("Indy").setLines(l);
					l = new ArrayList<String>();
					l.add("So, are you the new member of the Archaeology Society?");
					l.add("Indy told me you were able to find a priceless artifact in the Loviant Castle Ruins.");
					l.add("But you showed the true mark of an archaeologist; instead of selling it for thousands of Lovis, you donated it!");
					l.add("Ohhh, we archaeologists are weird...");
					l.add("Anyways, this cave leads up to an old shipwreck on the mountaintop.");
					l.add("But how did it get there?");
					l.add("The only explaination we can think of is the myth of \"The Great Veril 2 Flood.\"");
					l.add("According to the legends, the programmer got fed up with using VB6 and took out his wrath by flooding all of Loviant.");
					l.add("Everyone thought it was just a story, but this discovery could prove it true!");
					Finder.findPerson("Zachary").setLines(l);
					Finder.findPerson("Zachary").setLocation(new Location(9,9));
				}
			}
		}
		//////////////////////////////////////////////////////////////
		if (curTask() == 5)//FIND A SAIL
		{
			if (vbo instanceof Item)
			{
				if (((Item) vbo).getType() == Item.TYPE_SAIL)
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("So, did you find anything interesting?");
					l.add("A sail!  I saw that yesterday... Wasn't it in the middle of the rubble?");
					l.add("How did you get to it?");
					l.add("...");
					l.add("...");
					l.add("YOU DID WHAT!!!!!");
					l.add("YOU FOUND AN ANCIENT AND HISTORICAL BOAT AND YOU BURNED IT!");
					l.add("HOW IN THE NAME OF ARCHAEOLOGISTS EVERYWHERE COuLD YOU DO THAT!?!?");
					Finder.findPerson("Zachary").setLines(l);
					l = new ArrayList<String>();
					l.add("Ahoy, me harty!");
					l.add("That looks ter'be a sail yoose got there!");
					l.add("Thank 'ye, m'boy!  Now I can again sail these Seven Seas!");
					l.add("And I can lower me price fer the tour!");
					l.add("As a thanks for finding me a sail, here's 25 Lovis!");
					l.add("I reccomend you get ye'self some rum, it's always tasty!");
					Finder.findPerson("Cap'n").setLines(l);
					Shop s = new Shop();
					s.add(new ShopItem(new Item(Item.SHOP_LOSTISLANDTOUR), 25, "Lost Island Tour", "Visit a mysterious land", false));
					Finder.findPerson("Cap'n").giveShop(s);
				}
			}
		}
		////////////////////////////////////////////////////////////////
		if (curTask() == 6)//RETURN TO CAP'N
		{
			if (vbo instanceof Person)
			{
				if (((Person) vbo).getName().equals("Cap'n"))
				{
					completeTask();
					ArrayList<String> l = new ArrayList<String>();
					l.add("Thank'ye, me'boy!");
					l.add("SHOP");
					Finder.findPerson("Cap'n").setLines(l);
				}
			}
		}
	}

	public void roomChange(Room r)
	{
		if (curTask() == 4)//VISIT THE EXCAVATION
		{
			if (r.getWorld().getWorldNum() == 9)//caves
			{
				if (r.getWorld().getLocOfRoom(r).equals(new Location(0,4)))
				{
					completeTask();
					ObstacleAdder o = new ObstacleAdder(Veril3GameScreenFrame.world[0]);
				}
			}
		}
	}
}
