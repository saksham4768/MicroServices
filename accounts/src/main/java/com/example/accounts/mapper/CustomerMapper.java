package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDetailsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Customer;

/**
 * Mapper class for converting between Customer entity and CustomerDto objects.
 * Provides static methods for mapping between entity and DTO representations.
 */
public class CustomerMapper {

    /**
     * Maps a Customer entity to a CustomerDto.
     *
     * @param customer The source Customer entity
     * @param customerDto The target CustomerDto to populate
     * @return The populated CustomerDto
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    /**
     * Maps a CustomerDto to a Customer entity.
     *
     * @param customerDto The source CustomerDto
     * @param customer The target Customer entity to populate
     * @return The populated Customer entity
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    /**
     * Maps a entity to a CustomerDetailsDto.
     *
     * @param customer The source Customer entity
     * @param CustomerDetailsDto The target CustomerDetailsDto to populate
     * @return The populated CustomerDetailsDto
     */
    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }
}
