package librarykiosk;
import java.util.HashMap;

public class Employee extends Person{
	
	public Employee(int id, String name) {
		super(id, name);
	}
	
	public boolean authorize() {
		
		// Creates employee ID/name pairs using HashMap
		HashMap<Integer, String> validEmployees = new HashMap<>();
		
		validEmployees.put(999, "Melvil Dewey");
		
		// Gets employee id input
		int id = getId();
		
		// Determines if employee id is valid
		if (validEmployees.containsKey(id)) {
				return true;
			}
		
		return false;
	}
}