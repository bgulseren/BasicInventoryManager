/**
 * Implementation of the Inventory Class for the Retail Store.
 * 
 * @author B.Gulseren
 * @version 1.0
 * @since October 9th, 2020
 */

import java.util.ArrayList;

public class Inventory {
	
	/** the array list of items belonging to this inventory*/
	private ArrayList<Item> items;
	
	/** the order lines captured so far within this inventory*/
	private ArrayList<OrderLine> orderLines;
	
	/**
	 * Default Inventory Class Constructor
	 * Initializes member variables
	 */
	public Inventory() {
		this.items = new ArrayList<Item>(); //instantiate the member variables
		this.orderLines = new ArrayList<OrderLine>();
	}
	
	/**
	 * Overloaded Inventory Class Constructor
	 * Initializes member variables and gets the list of items from argument
	 * @param items the list of items to be passed into the constructor 
	 */
	public Inventory(ArrayList<Item> items) {
		this.items = items;
		this.orderLines = new ArrayList<OrderLine>();
	}
	
	/**
	 * Searches and returns the item in the inventory by its name
	 * 
	 * @param itemName the name of the item to look for
	 * @return the item which was found or null if it was not found
	 */
	public Item searchItem(String itemName) {
		for (int i = 0; i < items.size(); i++) {
			if (itemName.toLowerCase().contentEquals(items.get(i).getName().toLowerCase())) {
				return items.get(i); //matching name found in the item list, return it.
			}
		}
		return null; //item not found, return null
	}
	
	/**
	 * Searches and returns the item in the inventory by its id number
	 * 
	 * @param itemId the id number of the item to look for
	 * @return the item which was found or null if it was not found
	 */
	public Item searchItem(int itemId) {
		for (int i = 0; i < items.size(); i++) {
			if (itemId == items.get(i).getId())
				return items.get(i); //matching id found in the item list, return it.
		}
		return null; //item not found, return null
	}
	
	/**
	 * Searches the inventory for an item by its id number and returns the quantity of the item.
	 * If not found or item quantity was 0, returns 0.
	 * 
	 * @param itemId id number of the item to search for.
	 * @return the quantity of the item found (or 0 if not found).
	 */
	public int checkQty(int itemId) {
		Item foundItem = this.searchItem(itemId);
		
		if (foundItem != null)
			return foundItem.getQty();
		
		return 0;
	}
	
	/**
	 * Searches the inventory and adds an item to it if no item with specified parameters found.
	 * If item already exists, then increases the item's quantity only (other args are ignored).
	 * 
	 * @param itemId id number of the item to add.
	 * @param itemName name of the item to add.
	 * @param itemQty quantity of the item to add (for existing items, this is used to increase the actual quantity by).
	 * @param itemPrice price of the item to add.
	 * @param itemSupId supplier id number of the item to add.
	 */
	public void addItem(int itemId, String itemName, int itemQty, double itemPrice, int itemSupId) {
		Item foundItem = this.searchItem(itemId);
		
		if (foundItem == null) {
			//item id does not exist, therefore add it as a new item
			Item item = new Item();
			item.setId(itemId);
			item.setName(itemName);
			item.setQty(itemQty);
			item.setPrice(itemPrice);
			item.setSupplierId(itemSupId);
			
			this.items.add(item);
		} else {
			// item id already exists, therefore only add the quantity on top of the existing.
			// the other parameters will be ignored.
			foundItem.setQty(foundItem.getQty() + itemQty);
		}
	}
	
	/**
	 * Reduces quantity of the specified item from the inventory.
	 * If item reduction results an order line, then captures it into
	 * the order lines array of the inventory.
	 * 
	 * @param itemId id number of the item to reduce.
	 * @param itemQty quantity to reduce from the item.
	 */
	public void removeItem(int itemId, int itemQty) {
		Item foundItem = this.searchItem(itemId);
		OrderLine ol = new OrderLine();
		if (foundItem != null) {
			ol = foundItem.reduceQty(itemQty); //reduce item quantity and check if results an order line.
			if (ol != null) {
				this.orderLines.add(ol); //there is an order line active for this item so register it into the list.
			}
			
		}
	}
	
	/**
	 * Returns the order lines saved so far to the inventory.
	 * 
	 * @return array of order lines captured so far inside inventory.
	 */
	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}
	
	/**
	 * Clears the order lines saved so far from the inventory.
	 * Also clears the order active flags of each item belonging to this inventory.
	 * 
	 */
	public void clearOrderLines() {
		this.orderLines.clear();
		this.orderLines = new ArrayList<OrderLine>();
		
		for (Item item : this.items) {
			item.clearOrderFlag();
		}
	}
	
	/**
	 * Outputs a string representation of this inventory.
	 * 
	 * @return String representation of the inventory.
	 */
	@Override
	public String toString() {
		String outString = new String();
		for (int i = 0; i < items.size(); i++) {
			outString = outString + items.get(i).toString() + "\n";
		}
		return outString;
	}

}
