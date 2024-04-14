package eduib.library.controller.DTO;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for adding book to database
 */
public class AddBookDTO {
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private String availableCopies;

    /**
     * Constructs an AddBookDTO object.
     * @param ISBN ISBN of the book. (String)
     * @param title title of the book. (String)
     * @param author author of the book. (String)
     * @param publisher publisher of the book. (String)
     * @param publishYear publishing year of the book. (int)
     * @param availableCopies available copies of the book. (String)
     */
    public AddBookDTO(String ISBN, String title, String author, String publisher, int publishYear, String availableCopies) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.availableCopies = availableCopies;
    }

    /**
     * Gets the ISBN of the book.
     * @return ISBN of the book. (String)
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Sets the ISBN of the book.
     * @param ISBN ISBN for the book. (String)
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets the title of the book.
     * @return title of the book. (String)
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title The title for the book. (String)
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     * @return author of the book. (String)
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author  author for the book. (String)
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Gets the publisher of the book.
     * @return publisher of the book. (String)
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     * @param publisher  publisher for the book. (String)
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the publish year of the book.
     * @return publish year of the book. (int)
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the publish year of the book.
     * @param publishYear publish year to set for the book. (int)
     */
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }


    /**
     * Gets the available copies of the book.
     * @return available copies of the book. (String)
     */
    public String getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets the available copies of the book.
     * @param availableCopies available copies for the book. (String)
     */
    public void setAvailableCopies(String availableCopies) {
        this.availableCopies = availableCopies;
    }
}
