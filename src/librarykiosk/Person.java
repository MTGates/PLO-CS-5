package librarykiosk;

/**
 * Stores and shares the id and name of a person
 * Parent class of User and Employee
 * 
 * @author Montana Gates
 */
public abstract class Person {
	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
