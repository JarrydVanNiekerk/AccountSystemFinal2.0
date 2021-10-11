package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.MemberAccount;

@Repository
public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long> {
    @Query(value = "SELECT "+ "mb "+ "FROM " + "MemberAccount mb " + "WHERE " +
            "mb.memberId = :memberId " + "AND mb.accountTypeId = :accountTypeId")
    MemberAccount getMember(Long memberId, Long accountTypeId);


    @Modifying
    @Query(value = "UPDATE " + "MemberAccount mb " + "SET mb.balance = :amount " +
            "WHERE mb.memberId = :memberId " + "AND mb.accountTypeId = :accountTypeId")
    void updateMemberAccount(@Param("amount") Integer amount, @Param("memberId") Long memberId, @Param("accountTypeId") Long accountTypeId);
}