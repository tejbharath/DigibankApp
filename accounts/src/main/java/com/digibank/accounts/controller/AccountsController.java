package com.digibank.accounts.controller;

import com.digibank.accounts.constants.AccountConstants;
import com.digibank.accounts.dto.CustomerDTO;
import com.digibank.accounts.dto.ErrorResponseDTO;
import com.digibank.accounts.dto.ResponseDTO;
import com.digibank.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "DigiBank Account Rest APIs",
    description = "CRUD Rest APIS for Accounts in DigiBank"
)
@RestController
@RequestMapping(path="/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountService accountService;

    @Operation(
            summary = "Create Account Rest API",
            description = "Create New Account in DigiBank using this Rest API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {

        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.CREATE_REQUEST_SUCCESS_CODE, AccountConstants.CREATE_REQUEST_SUCCESS_MESSAGE));
    }


    @Operation(
            summary = "Get Account Details Rest API",
            description = "Get Account Details by Mobile Number using this Rest API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> getAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be numeric and must be 10 digits")
                                                             String mobileNumber) {

        CustomerDTO customerDto = accountService.getAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @Operation(
            summary = "Update Account Details Rest API",
            description = "Update Account based on the Customer details using this Rest API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDto) {

        accountService.updateAccountDetails(customerDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.REQUEST_SUCCESS_CODE, AccountConstants.REQUEST_SUCCESS_MESSAGE));
    }


    @Operation(
            summary = "Delete Account Rest API",
            description = "Delete Account based on the Mobile Number using this Rest API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be numeric and must be 10 digits")
                                                         String mobileNumber) {

        accountService.deleteAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.REQUEST_SUCCESS_CODE, AccountConstants.REQUEST_SUCCESS_MESSAGE));
    }
}
