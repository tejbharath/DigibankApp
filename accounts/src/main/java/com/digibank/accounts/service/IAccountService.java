package com.digibank.accounts.service;

import com.digibank.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     *
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);
}
