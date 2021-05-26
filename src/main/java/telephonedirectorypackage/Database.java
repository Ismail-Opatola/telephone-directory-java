package telephonedirectorypackage;

import java.text.ParseException;
import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.UUID;

/**
 * Database class - handle all db operations
 * @author Ismail
 *
 */
public class Database implements IDatabase {
	private ArrayList<Contact> contactList;
	private String pathToDatabase;
	FileHandler fileHandler;
	Helpers helpers;

	/**
	 * Database Constructor
	 * @param pathToDatabase
	 */
	public Database(String pathToDatabase) {
		// set path To db.json file
		this.pathToDatabase = pathToDatabase;
		// instantiate fileHandler 
		this.fileHandler = new FileHandler();
		// instantiate helpers
		this.helpers = new Helpers();
		// load data from storage i.e db.json
		this.contactList = fetchDB();
	}

	/**
	 * get all contacts in database
	 * @return contact list
	 */
	public ArrayList<Contact> getAll() {
		return contactList;
	}

	/**
	 * get a single contact
	 * @param query - string
	 */
	public Contact get(String query) {
		return null;
	}
	
	/**
	 * create and write to database
	 * @param data - contact object
	 */
	public void create(Contact data) {
		try {
			this.contactList.add(data);
			this.contactList.sort(Helpers.uuidComparator);
			String dataToJSON = helpers.convertListToJSON(contactList);
			fileHandler.writeToJsonFile(this.pathToDatabase, dataToJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update or patch contact object and write to database
	 * @param data - contact object
	 * @return boolean
	 */
	public boolean update(Contact data) {
		
		for(Contact contact : contactList) {
			if(
				contact.getUid().toString().compareToIgnoreCase(data.getUid().toString()) == 0
			) {
				contact.setFirstname(data.getFirstname());
				contact.setLastname(data.getLastname());
				contact.setPhone(data.getPhone());
			}
		}
		
		 // update database storage file
		 String dataToJSON = helpers.convertListToJSON(contactList);
		 fileHandler.writeToJsonFile(this.pathToDatabase, dataToJSON);
		 contactList = fetchDB();
		 return true;
	}
	
	/**
	 * delete contact object from database
	 * @param query - uuid
	 */
	public boolean delete(Contact contact) {
		 // delete data from in memory 
		 contactList.remove(contact);
		 // update database storage file
		 String dataToJSON = helpers.convertListToJSON(contactList);
		 fileHandler.writeToJsonFile(this.pathToDatabase, dataToJSON);
		 contactList = fetchDB();
		 return true;
	}

	/**
	 * find all matches to a query in the database
	 * @param queryString
	 * @return array list of contacts or empty array list 
	 */
	public ArrayList<Contact> findAll(String queryString) {
		ArrayList<Contact> result = new ArrayList<Contact>(); 
		
		for(Contact contact : contactList) {
			if(
				contact.getFirstname().toLowerCase().contains(queryString.toLowerCase())
				|| contact.getLastname().toLowerCase().contains(queryString.toLowerCase())
				|| contact.getPhone().toLowerCase().contains(queryString.toLowerCase())
			) {
				result.add(contact);
			}
		}
		
		return result;
	}
	/**
	 * find exact match to a query in the database
	 * @param queryString
	 * @return contact object or null 
	 */
	public Contact find(String queryString) {
		for(Contact contact : contactList) {
			if(
				contact.getFirstname().toLowerCase().equalsIgnoreCase(queryString.toLowerCase())
				|| contact.getLastname().toLowerCase().equalsIgnoreCase(queryString.toLowerCase())
				|| contact.getPhone().toLowerCase().equalsIgnoreCase(queryString.toLowerCase())
			) {
				return contact;
			}
		}
		
		return null;
	}

	/**
	 * fetch db.json file from local storage,
	 * parse and return data
	 * @return array list of contacts 
	 */
	public ArrayList<Contact> fetchDB() {
		ArrayList<Contact> res;
		try {			
			if(this.pathToDatabase != null || !this.pathToDatabase.isBlank()) {
				res = fileHandler.readFromJsonFile(pathToDatabase);
				res.sort(Helpers.uuidComparator);;
				return res;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
