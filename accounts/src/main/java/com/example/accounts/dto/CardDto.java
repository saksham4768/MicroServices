package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Cards",
        description = "Schema to hold Card information"
)
public class CardDto {

    @NotBlank(message = "Mobile Number should not be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer", example = "4354437687"
    )
    private String mobileNumber;

    @NotBlank(message = "Card Number should not be null or empty")
    @Pattern(regexp = "^$|[0-9]{12}", message = "Card Number must be 12 digits")
    @Schema(
            description = "Card Number of the customer", example = "100646930341"
    )
    private String cardNumber;

    @NotBlank(message = "Card Type should not be null or empty")
    @Schema(
            description = "Type of the card", example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "Total Limit must be greater than 0")
    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Amount Used must be greater than or equal to 0")
    @Schema(
            description = "Total amount used by a Customer", example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Available Amount must be greater than or equal to 0")
    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    private int availableAmount;
}

