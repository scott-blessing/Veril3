package Quests;

import Actors.Veril;
import java.util.ArrayList;
import Actors.VerilBaseObject;
import Rooms.Room;
import java.io.Serializable;

public abstract class Quest implements Serializable
{
	private Veril veril;

	private String name;
	private ArrayList<String> tasks;
	private int curTask;
	private boolean updated;

	private int moneyEarned;
	private int expEarned;

	private boolean completed;


	public Quest(String namey, int rewardMoney, int rewardEXP)
	{
		name = namey;
		tasks = new ArrayList<String>();
		moneyEarned = rewardMoney;
		expEarned = rewardEXP;
		completed = false;
		curTask = 0;
		updated = true;
		veril = null;
	}

	public void giveMeAVerilInstance(Veril v)
	{
		veril = v;
	}
	public void setTasks(ArrayList<String> tasks)
	{
		this.tasks = tasks;
	}

	public String getName()
	{
		return name;
	}
	public String getTask(int num)
	{
		return tasks.get(num);
	}
	public String getCurTask()
	{
		return tasks.get(curTask);
	}

	public abstract void activate();

	public int curTask()
	{
		return curTask;
	}
	public abstract void event(VerilBaseObject vbo);
	public abstract void roomChange(Room r);
	public void completeTask()
	{
		curTask++;
		setUpdated(true);
		if (getCurTask().equals("ENDQUEST"))
			complete();
	}

	public boolean isUpdated()
	{
		if (updated)
		{
			updated = false;
			return true;
		}
		return false;
	}
	public void setUpdated(boolean u)
	{
		updated = u;
	}

	public void complete()
	{
		veril.getInventory().addMoney(moneyEarned);
		veril.getInventory().addEXP(expEarned);
		completed = true;
	}
	public boolean isCompleted()
	{
		return completed;
	}


}

