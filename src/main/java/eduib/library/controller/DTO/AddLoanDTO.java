package eduib.library.controller.DTO;

import java.sql.Date;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for adding loan to database
 */
public class AddLoanDTO {
    private Date terminDate;
    private long userId;
    private long bookId;

    /**
     * Construct an AddLoanDTO object
     * @param terminDate termin date for the loan. (Date)
     * @param userId id of the user. (long)
     * @param bookId id of the book. (long)
     */
    public AddLoanDTO(Date terminDate, long userId, long bookId) {
        this.terminDate = terminDate;
        this.userId = userId;
        this.bookId = bookId;
    }


    public Date getTerminDate() {
        return terminDate;
    }

    public void setTerminDate(Date terminDate) {
        this.terminDate = terminDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
