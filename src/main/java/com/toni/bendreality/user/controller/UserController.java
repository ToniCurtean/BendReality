package com.toni.bendreality.user.controller;

import com.toni.bendreality.exceptions.BusinessValidationException;
import com.toni.bendreality.security.check.RolesAllowed;
import com.toni.bendreality.user.model.ChangePasswordRequest;
import com.toni.bendreality.user.model.UserViewDTO;
import com.toni.bendreality.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.security.Principal;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @RolesAllowed(value={"ADMIN","REGULAR"})
    @PutMapping
    public ResponseEntity<?> updateUserPassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
        userService.changePassword(request,connectedUser);
        return ResponseEntity.ok().build();
    }

}
