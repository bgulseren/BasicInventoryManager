/**
 * Implementation of the Shop Class for the Retail Store.
 * 
 * @author B.Gulseren
 * @version 1.0
 * @since October 9th, 2020
 */

import java.util.ArrayList;
import java.io.IOException;

public class Shop {
	
	/** the inventory object belonging to this shop */
	private Inventory inventory;
	
	/** the suppliers list belonging to this shop */
	private ArrayList<Supplier> suppliers;
	
	/** the active order for the day */
	private Order order;
	
	/**
	 * Default Shop Class Constructor
	 * 
	 * Initializes inventory and suppliers as blank.
	 * 
	 */
	public Shop() {
		this.inventory = new Inventory();
		this.suppliers = new ArrayList<Supplier>();
	}
	
	/**
	 * Overloaded Shop Class Constructor
	 * 
	 * Imports items and suppliers files into inventory and shop.
	 * 
	 * @param itemsFileName the file name to be read when importing items list externally
	 * @param supFileName the file name to be read when importing suppliers list externally
	 */
	public Shop(String itemsFileName, String supFileName) throws IOException {
		
		this.inventory = new Inventory(this.importItems(itemsFileName));
		this.suppliers = this.importSuppliers(supFileName);
	}
	
	/**
	 * Searches and returns the supplier among the suppliers list by its name.
	 * 
	 * @param name the name of the supplier to look for
	 * @return the supplier which was found or null if it was not found
	 */
	public Supplier searchSupplier(String name) {
		for (int i = 0; i < suppliers.size(); i++) {
			if (name.toLowerCase() == suppliers.get(i).getName().toLowerCase())
				return suppliers.get(i); //matching name found in the item list, return it.
		}
		return null; //item not found, return null
	}
	
	/**
	 * Searches and returns the supplier among the suppliers list by its id.
	 * 
	 * @param id the id number of the supplier to look for
	 * @return the supplier which was found or null if it was not found
	 */
	public Supplier searchSupplier(int id) {
		for (int i = 0; i < suppliers.size(); i++) {
			if (id == suppliers.get(i).getId())
				return suppliers.get(i); //matching name found in the item list, return it.
		}
		return null; //item not found, return null
	}
	
	/**
	 * Searches the suppliers list and adds a supplier to it if no supplier with specified id found.
	 * If supplier already exists, then does nothing (prints a msg to console).
	 * 
	 * @param id id number of the supplier to add.
	 * @param name name of the supplier to add.
	 * @param address address of the supplier to add.
	 * @param contact contact of the item to add.
	 */
	public void addSupplier(int id, String name, String address, String contact) {
		Supplier foundSupplier = this.searchSupplier(id);
		
		if (foundSupplier == null) {
			//supplier id does not exist, therefore we can add it as a new supplier
			Supplier sup = new Supplier(id, name, address, contact);
			this.suppliers.add(sup);
		} else {
			System.out.println("Supplier already exists, cannot add!");
		}
	}
	
	/**
	 * Returns the inventory associated with the shop.
	 * 
	 * @return the inventory object associated with the shop.
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Reads the order lines from the inventory and submits them as the active order.
	 * Also adds supplier name to the order by checking item supplier id from supplier list.
	 * When order is submit, clears the order lines from the inventory (since they are submit).
	 */
	public void createOrder() {
		ArrayList<OrderLine> ol = this.inventory.getOrderLines();
		
		if (ol != null && this.order == null) {
			// add supplier name to the order lines
			for (int i = 0; i < ol.size(); i++) {
				for (int j = 0; j < suppliers.size(); j++) {
					//set supplier name by matching supplier id inside order line and checking that from supplier list 
					
					if (ol.get(i).getSupplierId() == this.suppliers.get(j).getId()) {
						ol.get(i).setSupplierName(this.suppliers.get(j).getName());
						break;
					}
				}
			}
			
			this.order = new Order(ol);
			this.order.setOrderLines(ol);
		}
	}
	
	/**
	 * Returns the active order for this inventory.
	 * 
	 * @return the active order
	 */
	public Order getOrder() {
		return this.order;
	}
	
	/**
	 * Reads the specified file and returns a list of items read from the file.
	 * 
	 * @param fileName name of the file to be read.
	 * @return list of items read from the file
	 */
	public ArrayList<Item> importItems(String fileName) throws IOException {
		FileHandler fh = new FileHandler();
		return fh.readItems(fileName);
	}
	
	/**
	 * Reads the specified file and returns a list of suppliers read from the file.
	 * 
	 * @param fileName name of the file to be read.
	 * @return list of suppliers read from the file
	 */
	public ArrayList<Supplier> importSuppliers(String fileName) throws IOException {
		FileHandler fh = new FileHandler();
		return fh.readSuppliers(fileName);
	}
	
	/**
	 * Returns list of suppliers in string format.
	 * 
	 * @return list of suppliers in string format.
	 */
	public String outputSuppliers() {
		String outString = new String();
		for (int i = 0; i < suppliers.size(); i++) {
			outString = outString + suppliers.get(i).toString() + "\n";
		}
		return outString;
	}
	
}
