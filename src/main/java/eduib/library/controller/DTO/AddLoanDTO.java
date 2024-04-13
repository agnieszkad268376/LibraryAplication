package eduib.library.controller.DTO;

import java.sql.Date;


public class AddLoanDTO {
    private Date terminDate;
    private long userId;
    private long bookId;

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
