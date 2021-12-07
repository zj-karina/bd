package com.romanova.bd.registration;

import com.romanova.bd.security.ApplicationUser;
import com.romanova.bd.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final UserService userService;

    public void register(RegistrationRequest request) {
       userService.signUpUser(new ApplicationUser(
                request.getFirstname(),
                request.getLastname(),
                request.getLogin(),
                request.getPassword()
        ));
    }

    public boolean checkIsUniqueLogin(String login) {
        return userService.checkIsUniqueLogin(login);
    }
}
