package com.digibank.accounts.service.Impl;

import com.digibank.accounts.constants.AccountConstants;
import com.digibank.accounts.dto.AccountDTO;
import com.digibank.accounts.dto.CustomerDTO;
import com.digibank.accounts.entity.Account;
import com.digibank.accounts.entity.Customer;
import com.digibank.accounts.exception.CustomerAlreadyExistsException;
import com.digibank.accounts.exception.ResourceNotFoundException;
import com.digibank.accounts.mapper.CustomerMapper;
import com.digibank.accounts.mapper.AccountMapper;
import com.digibank.accounts.repository.AccountRepository;
import com.digibank.accounts.repository.CustomerRepository;
import com.digibank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    /**
     * @param customerDTO - CustomerDTO Object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number " + customerDTO.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param mobileNumber - mobile number of customer
     * @return the account details
     */
    @Override
    public CustomerDTO getAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDTO customerDto = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDto.setAccountDto(AccountMapper.mapToAccountsDto(account, new AccountDTO()));

        return customerDto;
    }

    /**
     * @param customerDto - customerDto of customer
     */
    @Override
    public void updateAccountDetails(CustomerDTO customerDto) {
        AccountDTO accountDto = customerDto.getAccountDto();
        Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString())
        );

        Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "customerId", account.getCustomerId().toString())
        );

        Customer updatedCustomer = CustomerMapper.mapToCustomer(customerDto, customer);
        Account updatedAccount = AccountMapper.mapToAccounts(accountDto, account);
        updatedAccount.setCustomerId(customer.getCustomerId());
        customerRepository.save(updatedCustomer);
        accountRepository.save(updatedAccount);
    }

    /**
     * @param mobileNumber - mobile number of customer
     */
    @Override
    public void deleteAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        customerRepository.delete(customer);
        accountRepository.delete(account);
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
