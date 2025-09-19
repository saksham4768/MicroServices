package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer, account, card and loans information"
)
public class CustomerDetailsDto {
    @Schema(
            description = "Name of the customer", example = "xyz"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer", example = "xyz@gmail.com"
    )
    @NotEmpty(message = "Email can not be a null or empty")
    @Email
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "8848484848"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details  of the customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Cards details  of the customer"
    )
    private CardDto cardDto;

    @Schema(
            description = "Loans details  of the customer"
    )
    private LoansDto loansDto;
}
