package com.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePassword (

    @NotBlank(message = "Old password must not be blank")
    String oldPassword,

    @NotBlank(message = "New Password must not be blank")
    @Size(min = 6, max = 100, message = "New password must be between 6 and 100 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "New password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    String newPassword,

    @NotBlank(message = "Confirm password must not be blank")
    String confirmPassword

){}
