package sc08_TextAdventure;

public class Main {
	
	static Player player;
	
	public static void build()
	{
		Room start = new Room("The Flat",
				"You are in a somewhat dishevilled flat. There is a bed with a hunched figure on it. South leads out to the hallway."
				+ " There seems to be some sort of hell portal in the north window.");
		start.addItem("key");
		start.addDescriptor("figure", "It may not be alive... But there is a laptop placed on it.");
		start.addDescriptor("laptop", "The screen shows constantly scrolling text like 'LDA #$01' 'TAX' 'INX' 'ADC #$c4'."
				+ "You can't translate it.");
		start.addAction("take key",
				new Action()
				{
			public void act()
			{
				System.out.println("Took key!");
				player.addItem("key");
				start.removeItem("key");
			}
				});
		
		player = new Player(start);
		
		Room hall = new Room("The Hallway",
				"The door shuts behind you as you step into the hallway. There are five other doors to five other flats."
				+ "East lies an exit to the building, but it is all caved in. North is the door to the flat.");
		hall.addExit("north", start);
		hall.addDescriptor("door", "It's the door to the flat. It's locked");
		hall.addDescriptor("doors", "Actually they might just be painted onto the walls...");
		
		
		Room lab = new Room("Computer lab",
				"A lab filled with an array of computers. It is dark. No-one else seems to be there. A metal box is placed on "
				+ "one of the desks."
				+ " There is some sort of hell portal in the south window.");
		lab.addExit("south", start);
		lab.addAction("use key", 
				new Action()
				{
					public void act()
					{
						if (player.hasItem("key"))
						{
							System.out.println("You unlock the box and the lid flips up, revealing something awful!"
									+ " A glitch! The game is unfinished! Damn.");
						}
						else
						{
							System.out.println("You don't have a key!");
						}
					}
				});
		lab.addResponse("unlock box", "With what?");
		
		
	}
	

	public static void main(String[] args) {
		Parser p = new Parser();
		build();
		
		System.out.println("GAME START");
		player.checkRoom();
		
		while(true)
		{
			String input = p.readLine().toLowerCase();
			//String[] code = input.split(" ");
			
			if(input.equals("quit"))
			{
				System.out.println("GAME EXIT");
				System.exit(0);
			}
			else if(input.equals("north") || input.equals("south") || input.equals("east") || input.equals("west"))
			{
				player.move(input);
			}
			else if(input.equals("i"))
			{
				player.printInventory();
			}
			else
			{
				player.tryAction(input);
			}
		}
		

	}

}
