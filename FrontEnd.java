import java.io.IOException;
import java.util.Scanner;  // Import the Scanner class

/**
 * Implementation of the FrontEnd Class for the Retail Store.
 * 
 * @author B.Gulseren
 * @version 1.0
 * @since October 9th, 2020
 */

public class FrontEnd {
	
	/** the shop object to be instantiated when front end exists, used to manipulate shop functions */
	private Shop shop;
	/** the scanner object to be instantiated to capture user inputs */
	private Scanner scan;
	
	/**
	 * Default FrontEnd Class Constructor
	 * 
	 * Initializes the shop by importing files
	 * Initializes the scanner object to capture user inputs
	 * 
	 */
	public FrontEnd() throws IOException {
		this.shop = new Shop("items.txt", "suppliers.txt");
		this.scan = new Scanner(System.in);
	}
	
	/**
	 * Displays a menu of options to the user
	 * 
	 */
	private void printMenu() {
		System.out.println("Please choose one of the following options:");
		System.out.println("1. List all tools in the inventory.");
		System.out.println("2. Search for tool by name.");
		System.out.println("3. Search for tool by id");
		System.out.println("4. Check item quantity.");
		System.out.println("5. Decrease item quantity.");
		System.out.println("6. Print today's order.");
		System.out.println("7. List all suppliers.");
		System.out.println("8. Quit.");
		System.out.println();
	}
	
	/**
	 * Captures user input as the menu action and calls the subsequent function.
	 * 
	 */
	private void menu() {
		while (true) {
			printMenu();
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
			case 1:
				System.out.println(this.shop.getInventory().toString());
				break;
			case 2:
				this.searchItemByName();
				break;
			case 3:
				this.searchItemById();
				break;
			case 4:
				this.checkItemQty();
				break;
			case 5:
				this.decreaseItem();
				break;
			case 6:
				this.getTodaysOrder();
				break;
			case 7:
				System.out.println(this.shop.outputSuppliers());
				break;
			case 8:
				System.out.println("Terminated!");
				scan.close();
				return;
			default:
				System.out.println("Invalid selection.");
				break;
			}
		}
	}
	
	/**
	 * Returns the entered user string as item name.
	 * 
	 * @return captured item name, must be a string.
	 */
	private String getItemName() {
		System.out.println("Please enter the name of the item: ");
		
		String line = scan.nextLine().trim();
		return line;
	}
	
	/**
	 * Returns the entered user integer as item id.
	 * 
	 * @return captured item id, must be an integer.
	 */
	private int getItemId() {
		System.out.println("Please enter the id number of the item: ");
		
		int num = scan.nextInt();
		scan.nextLine();
		return num;
	}
	
	/**
	 * Returns the entered user integer as item quantity.
	 * 
	 * @return captured item quantity, must be an integer.
	 */
	private int getItemQty() {
		System.out.println("Please enter the quantity to reduce: ");
		
		int num = scan.nextInt();
		scan.nextLine();
		return num;
	}
	
	/**
	 * Prompts user to enter item id and displays the quantity result from inventory
	 * 
	 */
	private void checkItemQty() {
		int id = getItemId();
		System.out.println("There is " + this.shop.getInventory().checkQty(id) + " of this item.\n");
	}
	
	/**
	 * Prompts user to enter item id and quantity to reduce for that item.
	 * Displays the quantity result afterwards.
	 *  
	 */
	private void decreaseItem() {
		int id = getItemId();
		System.out.println("There is " + this.shop.getInventory().checkQty(id) + " of this item.");
		int qty = getItemQty();
		this.shop.getInventory().removeItem(id, qty);
		
		System.out.println("The quantity is now " + this.shop.getInventory().checkQty(id) + " for this item.\n");
	}
	
	/**
	 * Prompts user to enter item id to look among the shop inventory.
	 * Displays the found item (or none if not found).
	 *  
	 */
	private void searchItemById() {
		int id = getItemId();
		System.out.println("Searching for item ID: " + id + "...");
		System.out.println(this.shop.getInventory().searchItem(id));
	}
	
	/**
	 * Prompts user to enter item name to look among the shop inventory.
	 * Displays the found item (or none if not found).
	 *  
	 */
	private void searchItemByName() {
		String name = getItemName();
		System.out.println("Searching for item name: " + name + "...");
		System.out.println(this.shop.getInventory().searchItem(name));
	}
	
	/**
	 * Calls the shop generate order function to generate a report of the 
	 * order lines raised so far.
	 *  
	 */
	private void getTodaysOrder() {
		this.shop.createOrder();
		System.out.println(this.shop.getOrder());
	}

	/**
	 * Main method, calls front end methods.
	 * 
	 * @param args optional arguments to be called.
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Initializing shop application, please wait...");
		
		FrontEnd fe = new FrontEnd();
		System.out.println("Ready");
		fe.menu();
		
	}
	

}
