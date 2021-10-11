package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.MemberAccount;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@ApiModel(value = "MemberAccount", description = "This DTO represents the MemberAccount")
public class MemberAccountDto implements Serializable {
    private static final long serialVersionUID = 5951234213607822710L;
    private Long memberId;
    private Long accountTypeId;
    private Integer balance;
    private LocalDate creationDate;

    public MemberAccountDto() {
    }

    public MemberAccountDto(Long memberID, Long accountTypeID, Integer balance, LocalDate creationDate) {
        this.memberId = memberID;
        this.accountTypeId = accountTypeID;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public MemberAccountDto(MemberAccount MemberAccount){
        this.memberId = MemberAccount.getMemberId();
        this.accountTypeId =MemberAccount.getAccountTypeId();
        this.balance = MemberAccount.getBalance();
        this.creationDate = MemberAccount.getCreationDate();
    }

    @ApiModelProperty(
            position = 1,
            value = "MemberAccount MemberID",
            name = "MemberID",
            notes = "Unique Member ID for the Member that owns the account",
            dataType = "java.lang.String",
            example ="110",
            required = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(
            position = 2,
            value = "MemberAccount AccountType",
            name = "AccountTypeID",
            notes = "The AccountTypeID for the specific AccountType",
            dataType = "java.lang.String",
            example = "2",
            required = true
    )
    public Long getAccountTypeID() {
        return accountTypeId;
    }

    public void setAccountTypeID(Long accountTypeID) {
        this.accountTypeId = accountTypeID;
    }

    @ApiModelProperty(
            position = 3,
            value = "MemberAccount Account Balance",
            name = "balance",
            notes = "Contains the amount of a currency that a User " +
                    "has in their account and is allowed to spend.",
            dataType = "java.lang.Integer",
            example = "5500",
            required = true
    )
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @ApiModelProperty(
            position = 4,
            value = "MemberAccount Creation Date",
            name = "The creation date of the MemberAccount",
            dataType = "java.lang.String",
            example = "2020-01-01",
            required = true
    )
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
        MemberAccountDto that = (MemberAccountDto) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(balance, that.balance) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, accountTypeId, balance, creationDate);
    }

    @JsonIgnore
    public MemberAccount getMemberAccount(){
        return new MemberAccount(getMemberId(), getAccountTypeID(), getBalance(), getCreationDate());
    }

    @Override
    public java.lang.String toString() {
        return "MemberAccountDto{" +
                "memberID=" + memberId +
                ", accountTypeID=" + accountTypeId +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}