package com.digibank.accounts.service;

import com.digibank.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     *
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber - mobile number of customer
     * @return the account details
     */
    CustomerDTO getAccountDetails(String mobileNumber);

    /**
     *
     * @param customerDto - customerDto of customer
     */
    void updateAccountDetails(Long customerId, CustomerDTO customerDto);

    /**
     *
     * @param mobileNumber - mobile number of customer
     */
    void deleteAccountDetails(String mobileNumber);
}
