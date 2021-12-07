package com.romanova.bd.registration;

import com.romanova.bd.security.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class RegistrationRequest {

    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private UserRole role;
}
