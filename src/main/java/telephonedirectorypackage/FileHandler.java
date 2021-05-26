package telephonedirectorypackage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * FileHadler Utility to write and read Json file 	
 */
public class FileHandler {

	/**
	 * File Constructor
	 */
	public FileHandler() {}
	
	/**
	 * Write JSON file
	 * @param filePath
	 * @param jsonData
	 */
	public void writeToJsonFile(String filePath, String jsonData) {		
		try {
			FileWriter file = new FileWriter(filePath);
			
			// we can write jsonData to the file
			file.write(jsonData); 
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read JSON file
	 * @param filePath
	 * @return ContactList
	 * @throws ParseException
	 */
	public ArrayList<Contact> readFromJsonFile(String filePath) throws ParseException {
		try {
			FileReader reader = new FileReader(filePath);
			
            // Deserialize JSON data
			Type contactsType = new TypeToken<ArrayList<Contact>>(){}.getType(); 
			final ArrayList<Contact> data = new Gson().fromJson(reader, contactsType);			
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

