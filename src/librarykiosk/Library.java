package librarykiosk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
	
	// Location of the book database
    private static final String BOOK_FILE = "data/books.txt";

    // Stores every book currently loaded into memory
    private ArrayList<Book> books;

    /**
     * Creates a Library object and automatically
     * loads all books from the text file.
     */
    public Library() {
        books = new ArrayList<>();
        refreshBooks();
    }

    /**
     * Returns the ArrayList of books.
     * GUI classes can use this to display books.
     *
     * @return list of books
     */
    public ArrayList<Book> getBooks() {
        return books;

}
    /**
     * Reloads the book collection from books.txt.
     * Existing books are cleared before reloading.
     */
    public void refreshBooks() {

        books.clear();

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            System.out.println("Book file could not be found.");
            return;
        }

        try (Scanner input = new Scanner(file)) {

            // Skip the header row
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

            System.out.println("Unable to open books.txt");

        }
    }
