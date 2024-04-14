package eduib.library.controller.DTO;

import java.sql.Date;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It is for updating return date in loan
 */
public class ReturnLoanDTO {

    private long loanId;
    private Date returnDate;

    /**
     * Construct ReturnLoanDTO
     * @param loanid loan's id (long)
     * @param returnDate loan's return date (Date)
     */
    public ReturnLoanDTO(long loanid, Date returnDate) {
        this.loanId = loanid;
        this.returnDate = returnDate;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
