package librarykiosk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Manages the library's collection of books.
 *
 * Responsibilities:
 * - Load books from books.txt
 * - Search the collection
 * - Check books in and out
 * - Save updates back to the file
 *
 * @author Ty Bride
 */
public class Library {

    // Location of the text file that stores the library database.
    private static final String BOOK_FILE = "data/books.txt";

    // Stores every Book object currently loaded into memory.
    private ArrayList<Book> books;

    /**
     * Creates a Library object and loads all books
     * from the text file into the ArrayList.
     */
    public Library() {
        books = new ArrayList<>();
        refreshBooks();
    }

    /**
     * Returns the current collection of books.
     * GUI classes can use this list to display search results.
     *
     * @return ArrayList of Book objects
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Reloads every book from books.txt.
     * Clears the current list before loading.
     */
    public void refreshBooks() {

        books.clear();

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            System.out.println("Book file could not be found.");
            return;
        }

        try (Scanner input = new Scanner(file)) {

            // Skip the header row of the text file.
            if (input.hasNextLine()) {
                input.nextLine();
            }

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (!line.trim().isEmpty()) {

                    Book book = createBookFromLine(line);

                    if (book != null) {
                        books.add(book);
                    }
                }
            }

        } catch (FileNotFoundException e) {

        	System.out.println("Unable to open books.txt: " + e.getMessage());
        	
        }

    }
    
    /**
     * Converts one line from books.txt into a Book object.
     *
     * Example line:
     * B0001|Pride and Prejudice|Austen, Jane|1813|Romance|true
     *
     * Creates a Book object using the values
     * read from one line of books.txt.
     */
    private Book createBookFromLine(String line) {
        String[] data = line.split("\\|");
        	
        if (data.length != 6) {
        	return null;
        	}
        	
        String bookId = data[0];
        String title = data[1];
        String author = data[2];
        String publishDate = data[3];
        String genre = data[4];
        boolean available = Boolean.parseBoolean(data[5]);
       	
    return new Book(
        	title,
        	author,
        	bookId,
        	publishDate,
        	genre,
        	available);
        
    }
    /**
    * Searches the library based on the user's query and
    * selected search type.
    *
    * Search types:
    * - Title
    * - Author
    * - Keyword
    *
    * Keyword searches all text fields.
    *
    * @param query text entered by the user
    * @param type selected search category
    * @return matching books
    */
    public ArrayList<Book> searchBooks(String query, String type) {
        ArrayList<Book> results = new ArrayList<>();
        	
        if (query == null || query.trim().isEmpty()) {
        	return results;
        }
        	
        query = query.toLowerCase().trim();
        	
        for (Book book : books) {
        	if (matchesSearch(book, query, type)) {
        			results.add(book);
        		}
        	}
        	
        return results;
        	
    }        
    
    /**
     * Checks whether a book matches the user's search.
     *
     * Uses the Book getters to compare search values.
     */
    private boolean matchesSearch(Book book, String query, String type) {
    	
    	String id = book.getBookId().toLowerCase();
    	String title = book.getTitle().toLowerCase();
    	String author = book.getAuthor().toLowerCase();
    	String year = book.getPublishDate().toLowerCase();
    	String genre = book.getGenre().toLowerCase();
    	
    	if (type.equalsIgnoreCase("Author")) {
    		return author.contains(query);
    	}
    	
    	if (type.equalsIgnoreCase("Title")) {
    		return title.contains(query);
    	}
    	
    	//Keyword searches every text field
    	return id.contains(query)
    			|| title.contains(query)
    			|| author.contains(query)
    			|| year.contains(query)
    			|| genre.contains(query);
    	}
    
    /**
     * Marks the selected book as checked out
     * and saves the updated collection.
     */
    public void checkoutBook(Book book) {
    	
    	if (book == null) {
    		return;
    	}
    	
    	/*
    	 * Update the selected book's availability.
    	 */
    	
    	book.checkout();
    	saveBooks();
    }
    
    /**
     * Marks the selected book as returned
     * and saves the updated collection.
     */
    public void returnBook(Book book) {
    	
    	if(book == null) {
    		return;
    	}
    	
    	/*
    	 * Mark the selected book as available again.
    	 */
    	
    	book.returnBook();
    	saveBooks();
    }
    
    /**
     * Saves the current collection of books back to books.txt.
     *
     * The file is completely rewritten each time this method
     * is called so the text file always matches the current
     * state of the ArrayList.
     */
    public void saveBooks() {
    	
    	try (PrintWriter output = new PrintWriter(new FileWriter(BOOK_FILE))) {
    		
    		// Rewrite the header row first
    		output.println("bookId|title|author|publishYear|genre|available");
    		
    		for (Book book : books) {
    			output.println(formatBookForFile(book));
    		}
    	} catch (IOException e) {
    		
    		System.out.println("Error saving books: " + e.getMessage());
    		
    		}
    	}
    
    /**
     * Converts a Book object into the format required
     * for books.txt.
     *
     * Example:
     * B0001|Pride and Prejudice|Austen, Jane|1813|Romance|true
     *
     * Having this helper method keeps saveBooks()
     * shorter and prevents duplicate formatting code.
     */
    private String formatBookForFile(Book book) {
    	
    	return book.getBookId() + "|"
    			+ book.getTitle() + "|"
    			+ book.getAuthor() + "|"
    			+ book.getPublishDate() + "|"
    			+ book.getGenre() + "|"
    			+ book.isAvailable();
    }
    
}

    
    


    
    
