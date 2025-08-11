package com.example.accounts.repository;

import com.example.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    /**
     * Finds an account by customer ID.
     *
     * @param customerId the ID of the customer whose account to find
     * @return an Optional containing the account if found, or empty if not found
     */
    Optional<Accounts> findByCustomerId(Long customerId);

    /**
     * Deletes all accounts associated with the given customer ID.
     *
     * @param customerId the ID of the customer whose accounts should be deleted
     * @throws javax.persistence.TransactionRequiredException if no transaction is active
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
