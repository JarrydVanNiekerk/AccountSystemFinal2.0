package za.ac.nwu.ac.domain.persistence;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT_TYPE",schema = "HR")
public class AccountType implements Serializable {

    private Long accountTypeId;
    private String mnemonic;
    private String accountTypeName;
    private LocalDate creationDate;
    private Set<AccountTransaction> accountTransactions;
    private Set<MemberAccount> memberAccount;

    public AccountType(Long accountTypeId, String mnemonic, String accountTypeName, LocalDate creationDate) {
        this.accountTypeId = accountTypeId;
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
    }

    public AccountType() {

    }

    public AccountType(String mnemonic, String accountTypeName, LocalDate creationDate) {
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
    }

    public void setAccountTypeID(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Id
    @SequenceGenerator(name="ACC_TYPE_SEQ",sequenceName = "HR.ACC_TYPE_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ACC_TYPE_SEQ")
    @Column(name = "Account_Type_ID")
    public Long getAccountTypeID() {
        return accountTypeId;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @Column(name="Mnemonic")
    public String getMnemonic() {
        return mnemonic;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Column(name="Account_Type_Name")
    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name="Creation_Date")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setAccountTransactions(Set<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public void setMemberAccount(Set<MemberAccount> memberAccount) {
        this.memberAccount = memberAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return accountTypeId == that.accountTypeId && Objects.equals(mnemonic, that.mnemonic) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeId, mnemonic, accountTypeName, creationDate);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeID=" + accountTypeId +
                ", mnemonic='" + mnemonic + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
