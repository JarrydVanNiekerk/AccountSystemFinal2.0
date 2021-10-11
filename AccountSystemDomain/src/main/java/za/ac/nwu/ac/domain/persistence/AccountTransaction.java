package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ACCOUNT_TRANSACTION", schema = "HR")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 1199041377884282633L;
    private Long accountTxId;
    private Long accountTypeId;
    private Long memberId;
    private Integer amount;
    private LocalDate txDate;

    public AccountTransaction(Long accountTxId, Long accountTypeId, Long memberId, Integer amount, LocalDate txDate) {
        this.accountTxId = accountTxId;
        this.accountTypeId = accountTypeId;
        this.memberId = memberId;
        this.amount = amount;
        this.txDate = txDate;
    }

    public AccountTransaction(Long accountTypeId, Long memberId, Integer amount, LocalDate txDate) {
        this.accountTypeId = accountTypeId;
        this.memberId = memberId;
        this.amount = amount;
        this.txDate = txDate;
    }

    public AccountTransaction(Long memberId, Integer amount, LocalDate txDate) {
        this.memberId = memberId;
        this.amount = amount;
        this.txDate = txDate;
    }

    public AccountTransaction() {
    }

    public void setAccountTxId(Long accountTxId) {
        this.accountTxId = accountTxId;
    }

    @Id
    @SequenceGenerator(name = "ACC_TX_SEQ", sequenceName = "HR.ACC_TX_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_TX_SEQ")
    @Column(name = "Account_TX_ID")
    public Long getAccountTxId() {
        return accountTxId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public void setMemberID(Long memberId) {
        this.memberId = memberId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setTxDate(LocalDate txDate) {
        this.txDate = txDate;
    }

    @JoinColumn(name = "Account_Type_ID")
    public Long getAccountTypeId() {
        return accountTypeId;
    }

    @Column(name = "Amount")
    public Integer getAmount() {
        return amount;
    }

    @Column(name = "TX_Date")
    public LocalDate getTxDate() {
        return txDate;
    }

    @Column(name = "Member_ID")
    public Long getMemberID() {
        return memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(accountTxId, that.accountTxId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTxId, accountTypeId, memberId, amount, txDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "accountTxId=" + accountTxId +
                ", accountTypeID=" + accountTypeId +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", txDate=" + txDate +
                '}';
    }
}
