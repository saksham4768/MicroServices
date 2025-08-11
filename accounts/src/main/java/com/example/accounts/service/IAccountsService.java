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

    /**
     * Fetches account details for a customer using their mobile number.
     *
     * @param mobileNumber The mobile number of the customer whose account details to fetch
     * @return CustomerDto containing the customer and account details
     * @throws ResourceNotFoundException if no customer is found with the given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Updates an existing customer's account information.
     *
     * @param customerDto The CustomerDto containing the updated customer and account information
     * @return boolean indicating whether the update was successful
     * @throws ResourceNotFoundException if the account or customer is not found
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Deletes a customer's account using their mobile number.
     *
     * @param mobileNumber The mobile number of the customer whose account should be deleted
     * @return boolean indicating whether the deletion was successful
     * @throws ResourceNotFoundException if no customer is found with the given mobile number
     */
    boolean deleteAccount(String mobileNumber);
}
