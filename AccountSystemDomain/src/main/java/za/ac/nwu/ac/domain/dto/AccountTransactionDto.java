package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountTransaction", description = "A DTO that represents the AccountTransaction")
public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = -7819344808062462808L;

    private Long AccountTypeId;
    private Long memberId;
    private Integer amount;
    private LocalDate txDate;

    public AccountTransactionDto(Long memberId, Integer amount, LocalDate txDate) {
        this.memberId = memberId;
        this.amount = amount;
        this.txDate = txDate;
    }

    public AccountTransactionDto(Long memberID, Long AccountTypeId, Integer amount) {
        this.memberId = memberId;
        this.AccountTypeId = AccountTypeId;
        this.amount = amount;
        this.txDate = LocalDate.now();
    }

    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.setMemberId(accountTransaction.getMemberID());
        this.setAmount(accountTransaction.getAmount());
        this.setTxDate(accountTransaction.getTxDate());
    }

    public AccountTransactionDto() {

    }

    @ApiModelProperty(position = 1,
            value = "Account Type ID fo the account transaction",
            name = "accountTypeId",
            notes = "Uniquely identifies the account type of the account on which a transaction was performed",
            dataType = "java.lang.Long",
            example = "3",
            required = true)
    public Long getAccountTypeId() {
        return AccountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        AccountTypeId = accountTypeId;
    }


    @ApiModelProperty(
            position = 2,
            value = "Member ID",
            name = "memberId",
            notes = "Uniquely identifies a member",
            dataType = "java.lang.String",
            example = "110",
            required = true
    )
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(
            position = 3,
            value = "Amount",
            name = "Amount",
            notes = "Amount on the member's account",
            dataType = "java.lang.String",
            example = "200",
            required = true
    )
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @ApiModelProperty(
            position = 4,
            value = "Transaction Date",
            name = "txDate",
            notes = "The date the transaction occurred",
            dataType = "java.lang.String",
            example = "2021-02-01",
            required = true
    )
    public LocalDate getTxDate() {
        return txDate;
    }

    public void setTxDate(LocalDate txDate) {
        this.txDate = txDate;
    }

    @JsonIgnore
    public AccountTransaction getTransaction() {
        return new AccountTransaction(getAccountTypeId(),getMemberId(),getAmount(),getTxDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(AccountTypeId, that.AccountTypeId) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(txDate, that.txDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AccountTypeId, memberId, amount, txDate);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "AccountTypeId=" + AccountTypeId +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", txDate=" + txDate +
                '}';
    }
}
