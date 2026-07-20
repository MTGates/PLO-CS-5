package librarykiosk;
/**
 * Stores the information of a selected book
 * 
 * @author Montana Gates
 */
public class Book {
	private String title;
	private String author;
	private String bookId;
	private String publishDate;
	private String genre;
	private boolean available;
	
	public Book(String title, String author, String bookId, String publishDate,
			String genre, boolean available) {
		this.title = title;
		this.author = author;
		this.bookId = bookId;
		this.publishDate = publishDate;
		this.genre = genre;
		this.available = available;
	}
	
	public void checkout() {
		available = false;
	}
	
	public void returnBook() {
		available = true;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public String getData() {
		String bookStatus; 
		
		if (available) {
			bookStatus = "Available";
		} else {
			bookStatus = "Checked Out";
		}
		
		return "[ID#: " + bookId + "] " + title + " by " + author + " (" + publishDate + ") - " + bookStatus; 
	
	}
	
	// Getting book data
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public String getBookId() {
		return bookId;
	}
	// In case we have this getter used any where. Delete after confirming
	public String getIsbn() {
	    return bookId;
	}

}