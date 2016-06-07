package Service;

import java.util.Scanner;

public class StoreService {
	private Scanner userInput = new Scanner(System.in);

	public String displayMainMenu(){
		System.out.println("Welcome to our online store!");
		System.out.println("What is your name? ");
		String name = userInput.nextLine();
		System.out.println("Hello " + name + ", check out our variety of items on sale.");
		return name;		
	}
	
	public int displayItems(){
		System.out.println("If you want to see our collection of DVDs, press 1.\n" 
				+ "If you want to see our collection of CDs, press 2.\n" 
				+ "If you want to see our collection of books, press 3.\n");
		int userOption = userInput.nextInt();
		
		switch(userOption){
		case 1:
			OnlineMedia.dataFunctionProperties.getAllDVDs();
			break;
		case 2:
			OnlineMedia.dataFunctionProperties.getAllCDs();
			break;
		case 3:
			OnlineMedia.dataFunctionProperties.getAllBooks();
			break;
		default:
			System.out.println("This is not a valid option.");
			return -1;
		}
		return userOption;
	}
	
	public int getItemNo(){
		System.out.println("Which option would you like to purchase?");
		int itemNo = userInput.nextInt();
		return itemNo;
	}
	
}
