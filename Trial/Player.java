package sc08_TextAdventure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Player 
{
	private ArrayList<String> inventory;
	private Room currentRoom;
	
	private Map<String, Action> simpleCommands;
	
	public Player(Room startRoom)
	{
		this.currentRoom = startRoom;
		this.inventory = new ArrayList<String>();
		this.simpleCommands = new HashMap<String, Action>();
	}
	
	public void printInventory()
	{
		System.out.println("You are carrying:");
		Iterator<String> it = inventory.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	
	public boolean checkCommand(String cmd)
	{
		return simpleCommands.containsKey(cmd);
	}
	
	public void addCommand(String cmd, Action a)
	{
		simpleCommands.put(cmd, a);
	}
	
	public Action getCommand(String cmd)
	{
		return simpleCommands.get(cmd);
	}
	
	public void switchRoom(Room rm)
	{
		currentRoom = rm;
	}
	
	public void checkRoom()
	{
		currentRoom.checkRoom();
	}
	
	public void move(String direction)
	{
		currentRoom = currentRoom.move(direction);
	}
	
	public void tryAction(String cmd)
	{
		if (simpleCommands.containsKey(cmd))
		{
			simpleCommands.get(cmd).act();
		}
		else
		{
			currentRoom.tryAction(cmd);
		}
	}
	
	public boolean hasItem(String item)
	{
		if(inventory.contains(item))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addItem(String item)
	{
		inventory.add(item);
	}

	public void printItems()
	{
		System.out.println("I am carrying:");
		for(String s : inventory)
		{
			System.out.println(s);
		}
	}
}
