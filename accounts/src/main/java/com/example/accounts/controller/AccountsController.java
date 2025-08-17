package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.dto.ErrorResponseDto;
import com.example.accounts.dto.ResponseDto;
import com.example.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing bank accounts.
 * Provides endpoints for account-related operations.
 */
@Tag(
        name = "CRUD REST APIS for Accounts",
        description = "CRUD REST APIS for Accounts for create, update, delete and fetch account details"
)
@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@RequiredArgsConstructor
@Validated
public class AccountsController {

    private final IAccountsService accountsService;
    /**
     * Creates a new bank account for the given customer.
     *
     * @param customerDto The CustomerDto containing customer and account information
     * @return ResponseEntity containing status and message of the operation
     */
    @Operation(
            summary = "Creates a new bank account",
            description = "Creates a new bank account for the given customer with a randomly generated account number"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    /**
     * Fetches account details for a customer using their mobile number.
     *
     * @param mobileNumber The 10-digit mobile number of the customer
     * @return ResponseEntity containing the customer's account details
     * @throws jakarta.validation.ConstraintViolationException if mobile number format is invalid
     * @throws ResourceNotFoundException if no account is found for the given mobile number
     */

    @Operation(
            summary = "Fetch account details for a customer",
            description = "fetch account for the given customer mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                          @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                          String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    /**
     * Updates an existing customer's account information.
     *
     * @param customerDto The CustomerDto containing the updated customer and account information
     * @return ResponseEntity containing the status of the update operation
     * @throws jakarta.validation.ValidationException if the request body is invalid
     * @throws ResourceNotFoundException if the account or customer is not found
     */

    @Operation(
            summary = "update account details for a customer",
            description = "update account for the given customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    /**
     * Deletes a customer's account using their mobile number.
     *
     * @param mobileNumber The 10-digit mobile number of the customer whose account should be deleted
     * @return ResponseEntity containing the status of the delete operation
     * @throws jakarta.validation.ConstraintViolationException if mobile number format is invalid
     * @throws ResourceNotFoundException if no customer is found with the given mobile number
     */
    @Operation(
            summary = "delete account details for a customer",
            description = "delete account for the given customer mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status internal server error",
                    content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                    String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
