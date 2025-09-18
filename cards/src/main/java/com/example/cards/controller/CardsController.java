package com.example.cards.controller;

import com.example.cards.constants.CardConstants;
import com.example.cards.dto.CardDto;
import com.example.cards.dto.CardsContactInfoDto;
import com.example.cards.dto.ErrorResponseDto;
import com.example.cards.dto.ResponseDto;
import com.example.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing card operations.
 * Provides endpoints for creating, reading, updating, and deleting card details.
 * All endpoints are prefixed with "/api" and produce JSON responses.
 */
@Tag(
        name = "CRUD REST APIs for Cards",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CardsController {

    /**
     * Service for handling card-related business logic.
     */
    private final ICardService iCardsService;

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;
    private final CardsContactInfoDto cardsContactInfoDto;
    /**
     * Creates a new card for the given mobile number.
     *
     * @param mobileNumber The mobile number to associate with the new card (must be 10 digits)
     * @return ResponseEntity with status 201 (Created) and success message if successful
     * @throws com.example.cards.exception.CardAlreadyxistsException if a card already exists for the mobile number
     * @throws jakarta.validation.ValidationException if the mobile number is invalid
     */
    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Mobile number is already in use",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(
            @Valid @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    /**
     * Fetches card details for the given mobile number.
     *
     * @param mobileNumber The mobile number to fetch card details for (must be 10 digits)
     * @return ResponseEntity with status 200 (OK) and the card details if found
     * @throws com.example.cards.exception.ResourceNotFoundException if no card is found for the mobile number
     * @throws jakarta.validation.ValidationException if the mobile number is invalid
     */
    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found with the given mobile number",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCardDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        CardDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    /**
     * Updates the card details for the given card number.
     *
     * @param cardsDto The card data transfer object containing updated information
     * @return ResponseEntity with status 200 (OK) and success message if update is successful,
     *         or status 417 (Expectation Failed) if update fails
     * @throws com.example.cards.exception.ResourceNotFoundException if no card is found with the given card number
     * @throws jakarta.validation.ValidationException if the card data is invalid
     */
    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found with the given card number",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardsDto) {
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    /**
     * Deletes the card associated with the given mobile number.
     *
     * @param mobileNumber The mobile number associated with the card to delete (must be 10 digits)
     * @return ResponseEntity with status 200 (OK) and success message if deletion is successful,
     *         or status 417 (Expectation Failed) if deletion fails
     * @throws com.example.cards.exception.ResourceNotFoundException if no card is found for the mobile number
     * @throws jakarta.validation.ValidationException if the mobile number is invalid
     */
    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found with the given mobile number",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Delete operation failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get build information",
            description = "Get build information that is deployed into cards microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Get java version information",
            description = "Get java version information that is used in cards microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get contact related  information",
            description = "Get contact related information of cards microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDto);
    }
}
