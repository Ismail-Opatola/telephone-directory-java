package telephonedirectorypackage;

import java.util.ArrayList;
import java.util.Comparator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Helpers - contains utilities to convert, parse
 * compare and loop data
 * @author Ismail
 *
 */
public class Helpers {

	/**
	 * Helper Constructor
	 */
	public Helpers() {}
	
	/**
	 * convert arraylist of contact to json
	 * @param data - contact list
	 * @return json - string
	 */
	public String convertListToJSON(ArrayList<Contact> data) {
		Gson prettyGson = new GsonBuilder()
								.serializeNulls()
								.setPrettyPrinting()
								.create();
		String prettyJson = prettyGson.toJson(data);
		return prettyJson;
	}
	/**
	 * convert contact object to json
	 * @param data - contact object
	 * @return json - string
	 */
	public String convertObjectToJSON(Contact data) {
		// Use this builder to construct a Gson instance when you need to set configuration options other than the default.
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		// This is the main class for using Gson. 
		// Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it. 
		// Gson instances are Thread-safe so you can reuse them freely across multiple threads.
		Gson gson = gsonBuilder
					.serializeNulls()
					.create();
	
		String objectJson = gson.toJson(data);
		
		return objectJson;
	}
	
	/**
	 * compare two contacts by firtsname
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compare(Contact s1, Contact s2) {
		String s1Firstname = (String) s1.getFirstname();
		String s2Firstname = (String) s2.getFirstname();			
		return s1Firstname.compareTo(s2Firstname);
	}
	/**
	 * compare two contacts by UUID
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compareByUUID(Contact s1, Contact s2) {
		String s1UUID = s1.getUid().toString();
		String s2UUID = s2.getUid().toString();			
		return s1UUID.compareTo(s2UUID);
	}
	
	/**
	 * Custom comparator: Sorts list by firstname
	 * <br>
	 * A comparator is a comparison function, which imposes a total ordering on somecollection of objects. 
	 * Comparators can be passed to a sort method (suchas Collections.sort or Arrays.sort) to allow precise controlover the sort order
	 * <hr>
	 * NOTE: here, we override the compare method 
	 * inside of the comparator class to explicitly
	 * define how data should be sorted. see <i>Helpers.compare()</i> method.
	 */
	public static Comparator<Contact> firstnameComparator = new Comparator<Contact>() {
		public int compare(Contact s1, Contact s2) {
			return Helpers.compare(s1, s2);
		}
	};
	/**
	 * Custom comparator: Sorts list by UUID
	 */
	public static Comparator<Contact> uuidComparator = new Comparator<Contact>() {
		public int compare(Contact s1, Contact s2) {
			return Helpers.compareByUUID(s1, s2);
		}
	};
	
	/**
	 * Custom Iterator with index
	 * @param <T>
	 * @param source
	 * @param consumer
	 */
	<T> void forEachWithCounter(Iterable<T> source, BiConsumer<Integer, T> consumer) {
		int i = 0;
		for(T item: source) {
			consumer.accept(i, item);
			i++;
		}
	}
	
	/**expects sorted arraylist by UUID
	 * improved time complexity from O(n) to O(logn)
	 * using a binary search algorithm
	 * @param contacts
	 * @param query
	 * @param low
	 * @param high
	 * @return index
	 */
	int customBinarySearchArrayOfObjects(ArrayList<Contact> contacts, String query) {
		int low = 0, high = contacts.size() - 1;
		
		// repeat until the pointers low and high meet each other
		while(low <= high) {
			// get index of mid element
			int mid = low + (high - low) / 2;
			
			// if query to be searched is the mid element
			if(	contacts.get(mid).getFirstname().compareToIgnoreCase(query) == 0
					|| contacts.get(mid).getLastname().compareToIgnoreCase(query) == 0
					|| contacts.get(mid).getPhone().compareToIgnoreCase(query) == 0
					|| contacts.get(mid).getUid().toString().compareToIgnoreCase(query) == 0
					) return mid;
			
			// if query is greater than mid element, compareTo returns a positive number
			// ignore left half
			if(	contacts.get(mid).getFirstname().compareToIgnoreCase(query) < 0
					|| contacts.get(mid).getLastname().compareToIgnoreCase(query) < 0
					|| contacts.get(mid).getPhone().compareToIgnoreCase(query) < 0
					|| contacts.get(mid).getUid().toString().compareToIgnoreCase(query) < 0
					)  {
				low = mid + 1;
			} else {
				// if query is less than mid element
				// ignore right half
				high = mid - 1;
			}
		}
		
		return -1;
	}
	/**
	 * improved time complexity from O(n) to O(logn)
	 * using a binary search algorithm
	 * @param contacts
	 * @param query
	 * @param low
	 * @param high
	 * @return index
	 */
	int customBinarySearchArrayOfObjectsByUUID(ArrayList<Contact> contacts, String query) {
		int low = 0, high = contacts.size() - 1;
		
		// repeat until the pointers low and high meet each other
		while(low <= high) {
			// get index of mid element
			int mid = low + (high - low) / 2;
			
			// if query to be searched is the mid element
			if(contacts.get(mid).getUid().toString().compareToIgnoreCase(query) == 0) 
				return mid;
			
			// if query is greater than mid element, compareTo returns a positive number
			// ignore left half
			if(contacts.get(mid).getUid().toString().compareToIgnoreCase(query) > 0)	
				low = mid + 1;
			else 
				// if query is less than mid element
				// ignore right half
				high = mid - 1;
		}
		// if no match
		return -1;
	}
}
