package com.toni.bendreality.user.service;

import com.toni.bendreality.exceptions.BusinessValidationException;
import com.toni.bendreality.exceptions.ChangePasswordException;
import com.toni.bendreality.user.mapper.UserMapper;
import com.toni.bendreality.user.model.ChangePasswordRequest;
import com.toni.bendreality.user.model.User;
import com.toni.bendreality.user.model.UserCredentials;
import com.toni.bendreality.user.model.UserViewDTO;
import com.toni.bendreality.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        UserCredentials userCredentials = (UserCredentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> toUpdate = userRepository.findByEmail(userCredentials.email());
        if (toUpdate.isPresent()) {
            User user = toUpdate.get();
            //check if the current password is correct
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new ChangePasswordException("Wrong password!");
            }

            //check if the 2 new passwords are the same
            if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
                throw new ChangePasswordException("Passwords are not the same");
            }

            //update the password
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));

            log.info("is aici");
            //save the new password
            userRepository.save(user);
            log.info("is dupa save");
        }
    }
}
