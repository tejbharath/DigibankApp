package com.digibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account details",
        requiredProperties = {"name", "email", "mobileNumber", "accountDto"}
)
public class CustomerDTO {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=3, max=30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be numeric and must be 10 digits")
    private String mobileNumber;

    private AccountDTO accountDto;
}
