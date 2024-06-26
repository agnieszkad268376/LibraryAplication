package eduib.library.controller.DTO;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for getting book from database
 */
public class GetBookDTO {

    private long bookId;
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private String availableCopies;

    /**
     * Construct an GetBookDTO object
     * @param bookId id of the book (long)
     * @param ISBN isbn of the book (String)
     * @param title title of the book (String)
     * @param author author of the book (String)
     * @param publisher publisher of the book (String)
     * @param publishYear publish year of the book (int)
     * @param availableCopies availables copies of the book (String)
     */
    public GetBookDTO(long bookId, String ISBN, String title, String author, String publisher, int publishYear, String availableCopies) {
        this.bookId = bookId;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.availableCopies = availableCopies;
    }

    public GetBookDTO() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(String availableCopies) {
        this.availableCopies = availableCopies;
    }
}
