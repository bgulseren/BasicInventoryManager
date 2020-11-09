/**
 * Implementation of the Supplier Class for the Retail Store.
 * 
 * @author B.Gulseren
 * @version 1.0
 * @since October 9th, 2020
 */

public class Supplier {

	/** the id number of the supplier */
	private int id;
	
	/** the name of the supplier */
	private String name;
	
	/** the address of the supplier */
	private String address;

	/** the contact person of the supplier */
	private String contact;
	
	/**
	 * Supplier Class Constructor
	 *
	 * @param id id number to be set for this supplier
	 * @param name name to be set for this supplier
	 * @param address address to be set for this supplier
	 * @param contact contact name to be set for this supplier
	 */
	public Supplier(int id, String name, String address, String contact) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
	}

	/**
	 * Returns the address of the supplier
	 * 
	 * @return the address of the supplier
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Returns the contact name of the supplier
	 * 
	 * @return the contact name of the supplier
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Returns the id number of the supplier
	 * 
	 * @return the id number of the supplier
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the supplier
	 * 
	 * @return the name of the supplier
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Outputs a string representation of this supplier.
	 * 
	 * @return String representation of the supplier.
	 */
	@Override
	public String toString() {
		String output = "ID: " + getId() + " Name: " + getName() + " Address: " + getAddress() + " Contact: " + getContact(); 
		return output;
	}
	
	
}
