package com.romanova.bd.service;

import com.romanova.bd.security.ApplicationUser;
import com.romanova.bd.repository.UserRepository;
import com.romanova.bd.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<ApplicationUser> userOptional = userRepository.getUserByLogin(login);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot load user with login '%s'", login));
            log.error("Cannot find user by login", ex);
            throw ex;
        }
    }

    @Override
    public ApplicationUser getUserByLogin(String login) {
        Optional<ApplicationUser> userOptional = userRepository.getUserByLogin(login);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with login '%s'", login));
            log.error("Cannot find user by login", ex);
            throw ex;
        }
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void signUpUser(ApplicationUser user){
        Optional<ApplicationUser> userOptional = userRepository.getUserByLogin(user.getLogin());

        if(userOptional.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAccountEnabled(true);
            userRepository.save(user);
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with login '%s'", user.getLogin()));
            log.error("Cannot save user", ex);
            throw ex;
        }
    }

    @Override
    public ApplicationUser getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(Long id, ApplicationUser user) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            ApplicationUser retrievedUser = optionalUser.get();
            retrievedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            retrievedUser.setFirstname(user.getFirstname());
            retrievedUser.setLastname(user.getLastname());
            retrievedUser.setRole(user.getRole());

            userRepository.save(retrievedUser);
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with id '%d'", user.getId()));
            log.error("Cannot find user", ex);
            throw ex;
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with id '%d'", id));
            log.error("Cannot delete user", ex);
            throw ex;
        }

    }

    @Override
    public void blockUser(Long id) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            ApplicationUser retrievedUser = optionalUser.get();
            retrievedUser.setAccountBlocked(true);
            userRepository.save(retrievedUser);
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with id '%d'", id));
            log.error("Cannot block user", ex);
            throw ex;
        }
    }

    @Override
    public void unblockUser(Long id) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            ApplicationUser retrievedUser = optionalUser.get();
            retrievedUser.setAccountBlocked(false);
            userRepository.save(retrievedUser);
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with id '%d'", id));
            log.error("Cannot unblock user", ex);
            throw ex;
        }
    }

    @Override
    public List<ApplicationUser> getUsersByRole(UserRole role) {
        return null;
    }

    @Override
    public void enableAppUser(String login) {
        Optional<ApplicationUser> optionalUser = userRepository.getUserByLogin(login);
        if(optionalUser.isPresent()){
            ApplicationUser retrievedUser = optionalUser.get();
            retrievedUser.setAccountEnabled(true);
            userRepository.save(retrievedUser);
        }
        else {
            RuntimeException ex = new IllegalStateException(
                    String.format("Cannot find user with login '%s'", login));
            log.error("Cannot enable user", ex);
            throw ex;
        }
    }

    @Override
    public boolean checkIsUniqueLogin(String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }
}
