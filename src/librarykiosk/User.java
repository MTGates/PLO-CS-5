package librarykiosk;
import java.util.HashMap;

public class User extends Person {
	
	public User(int id, String name) {
		super(id, name);
	}
	
	public boolean validateId() {
		
		// Creates user ID/name pairs using HashMap
		HashMap<Integer, String> validUsers = new HashMap<>();
		
		validUsers.put(111, "Morgan");
		validUsers.put(222, "Ty");
		validUsers.put(333, "Montana");
			
		int id = getId();
		
		if (validUsers.containsKey(id)) {
				return true;
			}
		
		// id is not a valid user ID
		return false;
	}
}
