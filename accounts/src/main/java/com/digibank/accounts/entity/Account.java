package com.digibank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor
public class Account extends BaseEntity {

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="account_number")
    @Id
    private long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;
}
