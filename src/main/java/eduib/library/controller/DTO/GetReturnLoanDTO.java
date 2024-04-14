package eduib.library.controller.DTO;

import java.sql.Date;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for getting return loan from database
 */
public class GetReturnLoanDTO {

    private long id;
    private Date loanDate;
    private Date terminDate;
    private Date returnDate;
    private GetUserDTO userId;
    private GetBookDTO bookId;

    /**
     * Contruscts a GetReurnLoanDTO object
     * @param id id of the loan (long)
     * @param loanDate date of the loan (Date)
     * @param terminDate termin date of the loan (Date)
     * @param returnDate return date of the loan (Date)
     * @param userId loans user id (long)
     * @param bookId loans user id (long)
     */
    public GetReturnLoanDTO(long id, Date loanDate, Date terminDate, Date returnDate, GetUserDTO userId, GetBookDTO bookId) {
        this.id = id;
        this.loanDate = loanDate;
        this.terminDate = terminDate;
        this.returnDate = returnDate;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
