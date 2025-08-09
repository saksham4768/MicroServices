package com.example.accounts.service;

import com.example.accounts.dto.CustomerDto;

/**
 * Service interface for account-related operations.
 * Defines the contract for account management functionality.
 */
public interface IAccountsService {

    /**
     * Creates a new bank account for the specified customer.
     *
     * @param customerDto The CustomerDto containing customer and account information
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
