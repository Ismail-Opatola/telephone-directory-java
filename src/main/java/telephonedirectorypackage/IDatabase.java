package telephonedirectorypackage;

import java.util.ArrayList;
//import java.util.UUID;

/**
 * Database Interface
 * @author Ismail
 *
 */
public interface IDatabase {
	ArrayList<Contact> getAll();
	void create(Contact data);
	boolean update(Contact data);
	boolean delete(Contact contact);
	Contact find (String query);
	ArrayList<Contact> findAll (String query);
	Contact get(String query);
	ArrayList<Contact> fetchDB();
}

