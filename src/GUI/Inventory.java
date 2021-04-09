package GUI;

import GUI.ActionResult;
import GUI.AePlayWave;
import Rooms.*;
import Quests.Quest;
import Items.*;
import Obstacles.*;
import java.util.ArrayList;
import Actors.*;
import javax.swing.JOptionPane;
import java.io.Serializable;


public class Inventory implements Serializable
{
	public static final int LEVEL_CAP = 25;
	public static final int SKILL_CAP = 10;

	//a veril!
	private Veril veril;
	//leveling and experience
	private int level, attackBonus, defenseBonus, speedBonus;
	private int levelUpVal;
	private int prevLevelUpVal;
	private int exp;
	private boolean leveledUp;
	//health
	private int maxHealth;
	//money
	private int money;
	//keys
	private ArrayList<Key> keys;
	//arrows
	private int numArrows;
	private int quiverSize;
	public int arrowDamage;
	public boolean hasFireArrows;
	//torch
	private int fireLength;
	//code snippets
	private int codeSnippets;
	//arraylists
	private ArrayList<Quest> quests;
	private ArrayList<Equipment> equipment;
	//Equipment Animations
	private int equipLastUsed;
	//Maps
	public boolean hasMap;
	public boolean hasEpicMap;



	public Inventory(Veril v)
	{
		veril = v;
		level = 1;
		levelUpVal = 15;
		prevLevelUpVal = 0;
		exp = 0;
		money = 0;
		attackBonus = 0;
		defenseBonus = 0;
		speedBonus = 0;
		maxHealth = 15;
		keys =  new ArrayList<Key>();
		numArrows = 0;
		quiverSize = 50;
		fireLength = 4;
		equipment = new ArrayList<Equipment>();
		for (int i = 0; i < 11; i++)
			equipment.add(new Equipment(Equipment.TYPE_NONE));
		quests = new ArrayList<Quest>();
		codeSnippets = 0;
		leveledUp = false;
		equipLastUsed = -1;
		arrowDamage = 1;
		hasFireArrows = false;
		hasMap = false;
		hasEpicMap = false;
	}


//Money
	public void addMoney(int amt)
	{
		money += amt;
	}

	public boolean canAfford(int amt)
	{
		return (money >= amt);
	}

	public void loseMoney(int amt)
	{
		money -= amt;
		if (money < 0)
			money = 0;
	}


//Equipment
	public void addEquipment(Equipment newEquip)
	{
		int spot = 0;
		for (int i = 0; i < 8; i++)
		{
			if (equipment.get(i).getType() == Equipment.TYPE_NONE)
			{
				spot = i;
				break;
			}
		}
		equipment.set(spot,newEquip);
	}

	public void loseEquipment(Equipment loss)
	{
		for (int i = 0; i < equipment.size(); i++)
		{
			if (equipment.get(i).equals(loss))
			{
				equipment.remove(i);
				equipment.add(i,new Equipment(Equipment.TYPE_NONE));
				break;
			}
		}
	}

	public Equipment getEquipment(int slot)
	{
		if (slot == -1)
			return new Equipment(Equipment.TYPE_NONE);

		if (slot < equipment.size())
			return equipment.get(slot);
		else
			return new Equipment(Equipment.TYPE_NONE);
	}

	public ActionResult useEquipment(int slot)
	{
		if (veril.isFrozen())
		{
			veril.unfreeze();
			return null;
		}

		Equipment e = getEquipment(slot);
		VerilBaseObject obj = veril.getRoom().getObjectAt( veril.getLocation().nextSquare( veril.getDirection() ) );
		if (!(obj instanceof Enemy) && !(obj instanceof Person))
			checkQuests(obj);
		//obj = the object directly in front of veril
		ActionResult result = new ActionResult(e);

		if (obj instanceof Enemy)
		{
			if (e.getType() == Equipment.TYPE_SWORD)
			{
				((Enemy) obj).hit(veril.getAttack());
				new AePlayWave("Music/Clips/Sword-Miss.wav").start();
			}
			else if (e.getType() == Equipment.TYPE_HAMMER)
			{
				((Enemy) obj).hit(5);
				new AePlayWave("Music/Clips/Hammer-Ground.wav").start();
			}
			else if (e.getType() == Equipment.TYPE_TORCH)
			{
				Location L = veril.getLocation().nextSquare(veril.getDirection());
				if (L != null)
					veril.getWorld().add(new Fire(fireLength, true, veril.getRoomLoc(), L, veril.getWorld()), veril.getRoomLoc());
				new AePlayWave("Music/Clips/Torch-Miss.wav").start();
			}

			if (((Enemy) obj).getHealth() <= 0)
				checkQuests(obj);
		}
		else if (obj instanceof Person)
		{
			Person p = (Person) obj;
			String line = p.getNextLine(veril);
			result = new ActionResult(line);
			if (result.hasText() && p.getType() != Person.TYPE_SIGN)
				new AePlayWave("Music/Clips/Person.wav").start();
		}
		else if (obj instanceof Obstacle)
		{
			if (e.getType() == Equipment.TYPE_BOW)
			{
				shootArrow();
				new AePlayWave("Music/Clips/Bow-Shoot.wav").start();
			}
			else if (e.getType() == Equipment.TYPE_TORCH)
			{
				Location L = veril.getLocation().nextSquare(veril.getDirection());
				if (L != null)
					veril.getWorld().add(new Fire(fireLength, true, veril.getRoomLoc(), L, veril.getWorld()), veril.getRoomLoc());
				new AePlayWave("Music/Clips/Torch-Miss.wav").start();
			}
			else
			{
				Obstacle o = (Obstacle) obj;
				ActionResult a = o.interact(e, veril);
				if (a != null)
					result = a;
				if (veril.isTalking())
					new AePlayWave("Music/Clips/Person.wav").start();
				playEquipNoise(e.getType());
			}
		}
		else
		{
			if (e.getType() == Equipment.TYPE_BOW)
			{
				//shootArrow(); <-- GSF does this now
				new AePlayWave("Music/Clips/Bow-Shoot.wav").start();
			}
			else if (e.getType() == Equipment.TYPE_TORCH)
			{
				Location L = veril.getLocation().nextSquare(veril.getDirection());
				if (L != null)
					veril.getWorld().add(new Fire(fireLength, true, veril.getRoomLoc(), L, veril.getWorld()), veril.getRoomLoc());
				new AePlayWave("Music/Clips/Torch-Miss.wav").start();
			}
			else if (e.getType() == Equipment.TYPE_HAMMER)
			{
				ArrayList<Location> adjLocs = new ArrayList<Location>();
				for (int i = 0; i < 4; i++)
					adjLocs.add(veril.getLocation().nextSquare(i));
				Room r = veril.getRoom();
				ArrayList<VerilBaseObject> objs = new ArrayList<VerilBaseObject>();
				for (Location l : adjLocs)
				{
					if (l != null)
						objs.add(r.getObjectAt(l));
				}
				for (VerilBaseObject vbo : objs)
				{
					if (vbo != null)
						if (vbo instanceof Enemy)
							((Enemy) vbo).loseHealth(3);
				}
				new AePlayWave("Music/Clips/Hammer-Ground.wav").start();
			}
			else if (e.getType() == Equipment.TYPE_SHOVEL)
			{
				Location l = veril.getLocation().nextSquare(veril.getDirection());
				if (l != null && veril.getRoom().getSquare(l).isDiggable())
				{
					veril.getRoom().add(new Obstacle(Obstacle.TYPE_DUG_SPOT, veril.getRoomLoc(), l, veril.getWorld()));
					Item I = null;
					int i = (int)(Math.random()*50);
					if (i < 3)
						I = new Item(Item.TYPE_HEART_SMALL);
					else if (i < 5)
						I = new Item(Item.TYPE_LOVI, 1);
					else if (i < 7)
						I = new Item(Item.TYPE_ARROW, 5);
					else if (i < 8)
						I = new Item(Item.TYPE_LOVI, 5);
					if (I != null)
					{
						I.pickUp(veril);
						result = new ActionResult(I.getMessage(), I.getImage());
					}
					new AePlayWave("Music/Clips/Shovel-Dig.wav").start();
				}
			}
			else
				playEquipNoise(e.getType());
		}

		equipLastUsed = e.getType();

		return result;
	}

	public void swapEquipment(int slot1, int slot2)
	{
		Equipment e1, e2;
		e1 = getEquipment(slot1);
		e2 = getEquipment(slot2);

		equipment.remove(e1);
		equipment.add(slot1,e2);
		equipment.set(slot2,e1);
	}

	public int getNumEquipment()
	{
		int i = 0;
		for (Equipment e : equipment)
			if (e.getType() != -1)
				i++;
		return i;
	}

	public boolean hasEquipment(int e)
	{
		for (Equipment E : equipment)
		{
			if (e == E.getType())
				return true;
		}
		return false;
	}

	private void playEquipNoise(int type)
	{
		if (type == Equipment.TYPE_SWORD)
			new AePlayWave("Music/Clips/Sword-Miss.wav").start();
		if (type == Equipment.TYPE_POKINGSTICK)
			new AePlayWave("Music/Clips/Stick-Miss.wav").start();
		if (type == Equipment.TYPE_HAMMER)
			new AePlayWave("Music/Clips/Hammer-Ground.wav").start();
		//if (type == Equipment.TYPE_MIRROR)
		//	new AePlayWave("Music/Clips/Mirror.wav").start();
	}

	public int getEquipLastUsed()
	{
		return equipLastUsed;
	}

//Arrows
	public void addArrows(int amt)
	{
		numArrows += amt;
		if (numArrows > quiverSize)
			numArrows = quiverSize;
	}

	public void shootArrow()
	{
		if (numArrows > 0)
		{
			numArrows--;
			String type = Arrow.ARROW;
			if (hasFireArrows()) type = Arrow.FIREARROW;
			veril.getRoom().addArrow(veril.getLocation(), veril.getDirection(), arrowDamage, type);
		}
	}

	public int getNumArrows()
	{
		return numArrows;
	}

	public void increaseQuiverSize(int amt)
	{
		quiverSize += amt;
	}

	public void setArrowDamage(int amt)
	{
		arrowDamage = amt;
	}

	public void giveFireArrows()
	{
		hasFireArrows = true;
	}

	public boolean hasFireArrows()
	{
		return hasFireArrows;
	}

//Keys
	public void addKey(Key key)
	{
		keys.add(key);
	}

	public void useKey(int shape)
	{
		for (Key k : keys)
		{
			if (k.getVal() == shape)
			{
				keys.remove(k);
				return;
			}
		}
	}

	public void useGateKey(char gateVal)
	{
		for (Key k : keys)
		{
			if (k.getVal() == Key.GATE)
			{
				if (k.getGateValue() == gateVal)
				{
					keys.remove(k);
					return;
				}
			}
		}
	}

	public boolean hasAKey(int shape)
	{
		for (Key k : keys)
		{
			if (k.getVal() == shape)
				return true;
		}
		return false;
	}

	public boolean hasGateKey(char gateVal)
	{
		for (Key k : keys)
		{
			if (k.getVal() == Key.GATE)
				if (k.getGateValue() == gateVal)
					return true;
		}
		return false;
	}

	public int getKeyAmount(int shape)
	{
		int cnt = 0;
		for (Key k : keys)
		{
			if (k.getVal() == shape)
				cnt++;
		}
		return cnt;
	}



//Leveling and Experience
	public boolean addToAttack()
	{
		if (attackBonus < SKILL_CAP)
			attackBonus++;
		else
			return false;
		return true;
	}
	public boolean addToDefense()
	{
		if (defenseBonus < SKILL_CAP)
			defenseBonus++;
		else
			return false;
		return true;
	}
	public boolean addToSpeed()
	{
		if (speedBonus < SKILL_CAP)
			speedBonus++;
		else
			return false;
		return true;
	}

	public void addEXP(int EXP)
	{
		if (level < LEVEL_CAP)
		{
			exp += EXP;
			if (exp >= levelUpVal)
				levelUp();
		}
	}

	private void levelUp()
	{
		level++;
		leveledUp = true;

		if (level < LEVEL_CAP)
		{
			prevLevelUpVal = levelUpVal;
			levelUpVal += (level * 15);
		}

	}

	public boolean isLeveledUp()
	{
		if (leveledUp)
		{
			leveledUp = false;
			return true;
		}
		return false;
	}

	public void setLevel(int newLevel)
	{
		level = newLevel;
	}


//Quests
	public void addQuest(Quest q)
	{
		quests.add(q);
		q.giveMeAVerilInstance(veril);
		q.activate();
	}

	public Quest getQuest(String name)
	{
		for (Quest q : quests)
		{
			if (q.getName().equals(name))
				return q;
		}
		System.out.println("Couldn't find Quest: " + name);
		return null;
	}

	public void completeQuest(String name)
	{
		getQuest(name).complete();
	}

	public ArrayList<Quest> getAllQuests()
	{
		return quests;
	}
	public Quest getUpdatedQuest()
	{
		for (Quest q : quests)
		{
			if (q.isUpdated())
				return q;
		}
		return null;
	}
	public ArrayList<Quest> getNonCompletedQuests()
	{
		ArrayList<Quest> q = new ArrayList<Quest>();
		for (Quest Q : quests)
		{
			if (!Q.isCompleted())
				q.add(Q);
		}
		return q;
	}
	public ArrayList<Quest> getCompletedQuests()
	{
		ArrayList<Quest> q = new ArrayList<Quest>();
		for (Quest Q : quests)
		{
			if (Q.isCompleted())
				q.add(Q);
		}
		return q;
	}

	public void checkQuests(VerilBaseObject vbo)
	{
		for (Quest q : quests)
			q.event(vbo);
	}
	public void checkQuests(Room r)
	{
		for (Quest q : quests)
			q.roomChange(r);
	}

//Generic Get Methods
	public int getMoney()
	{
		return money;
	}
	public int getMaxHealth()
	{
		return maxHealth;
	}
	public int getEXP()
	{
		return exp;
	}
	public int getLevel()
	{
		return level;
	}
	public int getLevelUpVal()
	{
		return levelUpVal;
	}
	public int getPrevLevelUpVal()
	{
		return prevLevelUpVal;
	}
	public int getAttackBonus()
	{
		return attackBonus;
	}
	public int getDefenseBonus()
	{
		return defenseBonus;
	}
	public int getSpeedBonus()
	{
		return speedBonus;
	}

//Generic set methods
	public void setAttackBonus(int newAB)
	{
		attackBonus = newAB;
	}
	public void setDefenseBonus(int newDB)
	{
		defenseBonus = newDB;
	}
	public void setSpeedBonus(int newSB)
	{
		speedBonus = newSB;
	}
	public void setMaxHealth(int newMax)
	{
		maxHealth = newMax;
		veril.setHealth(newMax);
	}

//Max Health + Heart Pieces
	public void increaseMaxHealth(int amt)
	{
		setMaxHealth(maxHealth + amt);
	}

//Item Upgrades
	public void giveMap()
	{
		hasMap = true;
	}
	public boolean hasMap()
	{
		return hasMap;
	}
	public void giveEpicMap()
	{
		hasEpicMap = true;
	}
	public boolean hasEpicMap()
	{
		return hasEpicMap;
	}
	public void setTorchTime(int newTime)
	{
		fireLength = newTime;
	}

//Code Snippets
	public void addCodeSnippet()
	{
		codeSnippets++;
	}

	public String getCodeSnippetMessage()
	{
		switch (codeSnippets)
		{
			case 0:
				return "";
			case 1:
				return "L     t      a          P       ";
			case 2:
				return "L     t   t  a     o    P     T ";
			case 3:
				return "L  i  t   t  a u   o    P  F  T ";
			case 4:
				return "L vi  t s t ta u ( o  d P  FE T ";
			case 5:
				return "L vi nt s t ta us( or d.P RFE T)";
			case 6:
				return "Loviant.setStatus(World.PERFECT)";
			case 7:
				return "Loviant.setStatus(World.PERFECT);";
		}
		return "";
	}
}
