/**
 * Implementation of the Item Class for the Retail Store.
 * 
 * @author B.Gulseren
 * @version 1.0
 * @since October 9th, 2020
 */

public class Item {

	/** the id number of the item */
	private int id;
	
	/** the name of the item */
	private String name;
	
	/** the quantity of the item */
	private int qty;

	/** the unit price of the item */
	private double price;

	/** the supplierId of the item */
	private int supplierId;

	/** an orderLine is active for the item */
	private boolean orderActive;
	
	/**
	 * Item Class Constructor
	 *
	 */
	public Item() {
		this.orderActive = false;
	}
	
	/**
	 * Returns the id number of the item.
	 * 
	 * @return the id number of the item.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the item.
	 * 
	 * @return the name of the item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the price of the item.
	 * 
	 * @return the price of the item.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Returns the quantity of the item.
	 * 
	 * @return the quantity of the item.
	 */
	public int getQty() {
		return qty;
	}
	
	/**
	 * Returns the supplier id number of the item.
	 * 
	 * @return the supplier id number of the item.
	 */
	public int getSupplierId() {
		return supplierId;
	}
	
	/**
	 * Sets the id number for the item.
	 * 
	 * @param id id number to be set for the item.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Sets the name for the item.
	 * 
	 * @param name name to be set for the item.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the price for the item.
	 * 
	 * @param price price to be set for the item.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Sets the quantity for the item.
	 * 
	 * @param qty quantity to be set for the item. Cannot be a negative integer.
	 */
	public void setQty(int qty) {
		if (qty >= 0)
			this.qty = qty;
	}
	
	/**
	 * Sets the supplier Id for the item.
	 * 
	 * @param supplierId supplier id number to be set for the item.
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	
	/**
	 * Sets order active flag to false for the item.
	 * 
	 */
	public void clearOrderFlag() {
		this.orderActive = false;
	}
	
	/**
	 * Reduces the quantity for the item by amount entered.
	 * As a result of reduction, if item quantity drops below 40,
	 * then generates an order line for the item.
	 * 
	 * @param qty quantity to be reduced for the item.
	 * @return the order line (if item quantity was dropped below 40, otherwise null).
	 */
	public OrderLine reduceQty(int qty) {
		if (this.qty >= qty && qty > 0)
			//do not allow reducing item qty below 0. 
			this.qty = this.qty - qty;
		
		if (this.qty < 40 && !this.orderActive) {
			//The default quantity ordered by each item = 50 – number of existing items
			this.orderActive = true;
			
			OrderLine orderLine = new OrderLine();
			orderLine.setId(this.getId());
			orderLine.setName(this.getName());
			orderLine.setQty(50 - this.getQty());
			orderLine.setSupplierId(this.supplierId);
			
			return orderLine;
		}
		return null;
	}
	
	/**
	 * Outputs a string representation of this item object.
	 * 
	 * @return String representation of the item.
	 */
	@Override
	public String toString() {
		String output = "ID: " + getId() + " Name: " + getName() + " Qty: " + getQty() + " Unit $: " + getPrice(); 
		return output;
	}
	
}
