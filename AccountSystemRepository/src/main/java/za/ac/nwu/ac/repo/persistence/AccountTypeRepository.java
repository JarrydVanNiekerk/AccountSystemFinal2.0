package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.time.LocalDate;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {  // JPARepository<Type,ID_TYPE>

    @Query( // uses hibernate sql. Refers to database entities in application. More optimal for use on single tables
            value = "SELECT "+"at "+"FROM "+"AccountType at "+"WHERE at.mnemonic = :mnemonic"
    )
    AccountType getAccountTypeByMnemonic(String mnemonic);

    @Query( // Use this if you're working with multiple tables, joins etc.
            value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto("+
                    "at.mnemonic,"+"at.accountTypeName,"+"at.creationDate) "+
                    "FROM "+"AccountType at "+"WHERE at.mnemonic = :mnemonic"
    )
    AccountType getAccountTypeDtoByMnemonic(String mnemonic);

    @Query( // NB! Not recommended to use, since query won't work if you change databases, due to native nature
            value = "SELECT "+"ACCOUNT_TYPE_ID,"+"ACCOUNT_TYPE_NAME,"+"CREATION_DATE,"+"MNEMONIC "+
                    "FROM "+"HR.ACCOUNT_TYPE "+"WHERE MNEMONIC = :mnemonic", nativeQuery = true
    )
    AccountType getAccountTypeByMnemonicNativeQuery(String mnemonic);

    @Modifying
    @Query(value = "DELETE FROM "+ "AccountType at"+ " WHERE at.mnemonic = :mnemonic ")
    void deleteAccountTypeByMnemonic( String mnemonic);

    @Modifying
    @Query(value = "UPDATE " + "AccountType at " + "SET at.accountTypeName = :newAccountTypeName, " +
            "at.creationDate = :newCreationDate " + "WHERE " + "at.mnemonic = :mnemonic")
    void updateAccountType(String mnemonic, String newAccountTypeName, @Param("newCreationDate") LocalDate newCreationDate);

}