package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "MEMBER_ACCOUNT", schema = "HR")
public class MemberAccount implements Serializable {
    private static final long serialVersionUId = -6632638440092162565L;
    private Long memberAccountId;
    private Long memberId;
    private Long accountTypeId;
    private Integer balance;
    private LocalDate creationDate;

    public MemberAccount() {
    }

    public MemberAccount(Long memberAccountId, Long memberId, Long accountTypeId, Integer Balance, LocalDate creationDate) {
        this.memberAccountId = memberAccountId;
        this.memberId = memberId;
        this.accountTypeId = accountTypeId;
        this.balance = Balance;
        this.creationDate = creationDate;
    }
    public MemberAccount(Long memberId, Long accountTypeId, Integer Balance, LocalDate creationDate) {
        this.memberId = memberId;
        this.accountTypeId = accountTypeId;
        this.balance = Balance;
        this.creationDate = creationDate;
    }

    public MemberAccount(Integer Balance,Long memberId, Long accountTypeId) {
        this.memberId = memberId;
        this.accountTypeId = accountTypeId;
        this.balance = Balance;
    }

    @Id
    @SequenceGenerator(name = "ACC_MEMBER_SEQ", sequenceName = "HR.ACC_MEMBER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_MEMBER_SEQ")
    @Column(name = "Member_Account_ID")
    public Long getMemberAccountId() {
        return memberAccountId;
    }

    public void setMemberAccountId(Long memberAccountId) {
        this.memberAccountId = memberAccountId;
    }

    @Column(name = "Member_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "Account_Type_ID")
    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Column(name = "Balance")
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer Balance) {
        this.balance = Balance;
    }

    @Column(name = "Created_Date")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAccount that = (MemberAccount) o;
        return Objects.equals(memberAccountId, that.memberAccountId) && Objects.equals(memberId, that.memberId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(balance, that.balance) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberAccountId, memberId, accountTypeId, balance, creationDate);
    }

    @Override
    public java.lang.String toString() {
        return "MemberAccount{" +
                "memberAccountId=" + memberAccountId +
                ", memberId=" + memberId +
                ", accountTypeId=" + accountTypeId +
                ", Balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}