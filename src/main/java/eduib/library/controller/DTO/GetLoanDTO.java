package eduib.library.controller.DTO;

import eduib.library.entity.BookEntity;
import eduib.library.entity.UserEntity;

import java.sql.Date;

public class GetLoanDTO {
    private long id;
    private Date loanDate;
    private Date terminDate;
    private GetUserDTO userId;
    private GetBookDTO bookId;

    public GetLoanDTO(long id, Date loanDate, Date terminDate, GetUserDTO userId, GetBookDTO bookId) {
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

    public GetUserDTO getUserId() {
        return userId;
    }

    public void setUserId(GetUserDTO userId) {
        this.userId = userId;
    }

    public GetBookDTO getBookId() {
        return bookId;
    }

    public void setBookId(GetBookDTO bookId) {
        this.bookId = bookId;
    }
}
