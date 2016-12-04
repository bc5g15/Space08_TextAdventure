package sc08_TextAdventure;

import java.util.Scanner;

public class Parser {

	private Scanner sc;
	
	public Parser()
	{
		sc = new Scanner(System.in);
	}
	
	public String readLine()
	{
		return sc.nextLine();
	}
	
	public void close()
	{
		sc.close();
	}
	
	
}
