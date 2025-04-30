package com.digibank.accounts.controller;

import com.digibank.accounts.constants.AccountConstants;
import com.digibank.accounts.dto.CustomerDTO;
import com.digibank.accounts.dto.ResponseDTO;
import com.digibank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {

        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.CREATE_REQUEST_SUCCESS_CODE, AccountConstants.CREATE_REQUEST_SUCCESS_MESSAGE));
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> getAccountDetails(@RequestParam String mobileNumber) {

        CustomerDTO customerDto = accountService.getAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/{customerId}/update")
    public ResponseEntity<ResponseDTO> updateAccount(@PathVariable Long customerId, @RequestBody CustomerDTO customerDto) {

        accountService.updateAccountDetails(customerId, customerDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.REQUEST_SUCCESS_CODE, AccountConstants.REQUEST_SUCCESS_MESSAGE));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam String mobileNumber) {

        accountService.deleteAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.REQUEST_SUCCESS_CODE, AccountConstants.REQUEST_SUCCESS_MESSAGE));
    }
}
