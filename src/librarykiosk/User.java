package librarykiosk;

import java.util.HashMap;


/**
 * Creates kiosk user and determines if the user's id is valid
 * Child class of Person
 * @author Montana Gates
 */
public class User extends Person {
	
	public User(int id, String name) {
		super(id, name);
	}
	
	/**
	 * Creates list of valid ID/name pairs and checks validity
	 * @return true if the id is in the list, false if it is not
	 */
	public boolean validateId() {
		
		// Creates user ID/name pairs using HashMap
		HashMap<Integer, String> validUsers = new HashMap<>();
		// Adding pairs to the HashMap
		validUsers.put(111, "Morgan");
		validUsers.put(222, "Ty");
		validUsers.put(333, "Montana");
			
		// Gets user id input
		int id = getId();
		
		// Determines if user id is valid
		if (validUsers.containsKey(id)) {
			return true;
			}
		
		return false;
	}
}
