package eduib.library.controller.DTO;

import java.sql.Date;

public class LoanResponseDTO {
    private long id;
    private Date loanDate;
    private Date terminDate;
    private long userId;
    private long bookId;

    public LoanResponseDTO(long id, Date loanDate, Date terminDate, long userId, long bookId) {
        this.id = id;
        this.loanDate = loanDate;
        this.terminDate = terminDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
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
