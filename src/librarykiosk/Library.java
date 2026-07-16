package librarykiosk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

            System.out.println("Unable to open books.txt");

        }
    }
}