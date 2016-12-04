package sc08_TextAdventure;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room {

	private String name;
	private String desc;
	private Map<String, Room> exits;
	private Map<String, String> directions;
	private Map<String, String> descript;
	private Map<String, Action> actions;
	private ArrayList<String> items;
	
	public Room(String name, String description)
	{
		this.name = name;
		this.desc = description;
		this.exits = new HashMap<String, Room>();
		this.directions = new HashMap<String, String>();
		this.descript = new HashMap<String, String>();
		this.actions = new HashMap<String, Action>();
		this.items = new ArrayList<String>();
		
		directions.put("up", "down");
		directions.put("down", "up");
		directions.put("north", "south");
		directions.put("south", "north");
		directions.put("east", "west");
		directions.put("west", "east");
	}
	
	public void addSingleExit(String direction, Room room)
	{
		exits.put(direction, room);
	}
	
	/*
	 * Adds a two way exit
	 */
	public void addExit(String direction, Room room)
	{
		addSingleExit(direction, room);
		room.addSingleExit(directions.get(direction), this);
	}
	
	public void checkRoom()
	{
		System.out.println(name);
		System.out.println();
		System.out.println(desc);
		System.out.println();
		printItems();
	}
	
	public Room move(String direction)
	{
		if(exits.containsKey(direction))
		{
			Room rm = exits.get(direction);
			rm.checkRoom();
			return rm;
		}
		else
		{
			return this;
		}
	}
	
	public void addDescriptor(String item, String description)
	{
		descript.put(item, description);
		//The anonymous class method for looking at things.
		actions.put("x " + item, new Action()
				{
					public void act()
					{
						System.out.println(description);
					}
				});
	}
	
	public void printDescriptor(String item)
	{
		System.out.println(descript.get(item));
	}
	
	public void addItem(String item)
	{
		items.add(item);
	}
	
	public void removeItem(String item)
	{
		items.remove(item);
	}
	
	public void printItems()
	{
		if(items.size() > 0)
		{
			System.out.println("The room contains:");
			for(String s : items)
			{
				System.out.printf("%s, ", s);
			}
			System.out.println();
		}
	}
	
	public void tryAction(String cmd)
	{
		if(actions.containsKey(cmd))
		{
			actions.get(cmd).act();
		}
		else
		{
			System.out.println("You can't do that!");
		}
	}
	
	public void addAction(String cmd, Action a)
	{
		this.actions.put(cmd, a);
	}
	
	public void addResponse(String cmd, String response)
	{
		this.actions.put(cmd, 
				new Action()
				{
			public void act()
			{
				System.out.println(response);
			}
				});
	}
	
}
