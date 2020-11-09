import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File Handler for Items and Suppliers files.
 * Reads them and parses them into correct objects.
 * 
 * @author Burak Gulseren
 * @since 2020-10-8
 * 
 */

public class FileHandler {
	
	/**
	 * Opens the specified file name using file io.
	 * Checks if the file name conforms to the restrictions of the application.
	 * 
	 * @param inputfilename the name of the file to open.
	 * @return the file object which was opened (or null if file was not found).
	 */
	private File openFile(String inputfilename) throws IOException {
		System.out.print("Reading " + inputfilename + ", please wait ...");
		inputfilename = inputfilename.trim(); //trim the leading and trailing white spaces
		
		//file name arguments validity check
		if (!inputfilename.endsWith(".txt") || inputfilename.length() > 24) {
			
			System.out.println("File name not entered correctly, it must end with .txt and must be maximum 24 characters long.");
			return null;
		}
		return new File(inputfilename);
	}

	/**
	 * Opens the specified file name using file io and parses each line in the text
	 * file to be converted into an Item.
	 * When all lines are parsed, returns the array of items parsed from each line.
	 * 
	 * @param inputfilename the name of the file to open.
	 * @return the array list of items read from the file (or null if no item was found).
	 */
	public ArrayList<Item> readItems(String inputfilename) throws IOException {
		File file = openFile(inputfilename);
		
		if (file != null) {
			
			Scanner reader = new Scanner(file);
			ArrayList<Item> items = new ArrayList<Item>();
			
			while (reader.hasNextLine()) {
				String[] data = reader.nextLine().split(";");
				Item item = new Item();
				item.setId(Integer.parseInt(data[0]));
				item.setName(data[1]);
				item.setQty(Integer.parseInt(data[2]));
				item.setPrice(Double.parseDouble(data[3]));
				item.setSupplierId(Integer.parseInt(data[4]));
				
				items.add(item);
			}
			
			reader.close(); //all items are read and saved into inventory.
			System.out.println("... File read complete.");
			
			return items;
		}
		return null;
	}
	
	/**
	 * Opens the specified file name using file io and parses each line in the text
	 * file to be converted into a Supplier.
	 * When all lines are parsed, returns the array of suppliers parsed from each line.
	 * 
	 * @param inputfilename the name of the file to open.
	 * @return the array list of suppliers read from the file (or null if no supplier was found).
	 */
	public ArrayList<Supplier> readSuppliers(String inputfilename) throws IOException {
		File file = openFile(inputfilename);
		
		if (file != null) {
			
			Scanner reader = new Scanner(file);
			ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
			
			while (reader.hasNextLine()) {
				String[] data = reader.nextLine().split(";");
				
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				String address = data[2];
				String contact = data[3];
				
				Supplier supplier = new Supplier(id, name, address, contact);
				
				suppliers.add(supplier);
			}
			
			reader.close(); //all suppliers are read and saved into the list.
			System.out.println("... File read complete.");
			
			return suppliers;
		}
		return null;
	}
}

