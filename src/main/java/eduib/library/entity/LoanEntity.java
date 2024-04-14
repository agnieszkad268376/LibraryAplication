package eduib.library.entity;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * Entity class representing Loan
 */
@Entity
@Table(name="loan", schema="library")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "loan_name", nullable = false)
    private Date loanDate;
    @Basic
    @Column(name = "termin_date", nullable = false)
    private Date terminDate;
    @Basic
    @Column(name = "return_date")
    private Date returnDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private BookEntity book;


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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
