package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.entity.Accounts;

/**
 * Mapper class for converting between Accounts entity and AccountsDto objects.
 * Provides static methods for mapping between entity and DTO representations.
 */
public class AccountsMapper {

    /**
     * Maps an Accounts entity to an AccountsDto.
     *
     * @param accounts The source Accounts entity
     * @param accountsDto The target AccountsDto to populate
     * @return The populated AccountsDto
     */
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    /**
     * Maps an AccountsDto to an Accounts entity.
     *
     * @param accountsDto The source AccountsDto
     * @param accounts The target Accounts entity to populate
     * @return The populated Accounts entity
     */
    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
