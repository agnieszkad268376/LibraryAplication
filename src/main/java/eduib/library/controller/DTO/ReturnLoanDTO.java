package eduib.library.controller.DTO;

import java.sql.Date;

public class ReturnLoanDTO {

    private long loanId;
    private Date returnDate;

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
