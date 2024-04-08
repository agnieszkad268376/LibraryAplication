package eduib.library.controller.DTO;

public class AddBookDTO {
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private String availableCopies;

    public AddBookDTO(String ISBN, String title, String author, String publisher, int publishYear, String availableCopies) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.availableCopies = availableCopies;
    }

    public AddBookDTO() {
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
