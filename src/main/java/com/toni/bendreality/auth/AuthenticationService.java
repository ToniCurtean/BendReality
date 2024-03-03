package com.toni.bendreality.auth;

import com.toni.bendreality.security.jwt.JwtService;
import com.toni.bendreality.user.mapper.UserMapper;
import com.toni.bendreality.user.model.User;
import com.toni.bendreality.user.model.UserDTO;
import com.toni.bendreality.user.model.UserRole;
import com.toni.bendreality.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly=true)
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final JwtService jwtService;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request){
        UserDTO toAdd=new UserDTO(0, request.getFirstName(),request.getLastName(), request.getPhoneNumber(), request.getEmail(), passwordEncoder.encode(request.getPassword()), UserRole.REGULAR);
        User saved=userRepository.save(userMapper.toEntity(toAdd));
        saved.setCreatedBy(saved.getId());
        userRepository.save(saved);
        var jwtToken=jwtService.generateToken(userMapper.toViewDTO(saved));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        UserDTO toAdd = new UserDTO(0, request.getFirstName(), request.getLastName(), request.getPhoneNumber(),request.getEmail(), passwordEncoder.encode(request.getPassword()),UserRole.ADMIN);
        User saved = userRepository.save(userMapper.toEntity(toAdd));
        saved.setCreatedBy(saved.getId());
        userRepository.save(saved);
        var jwtToken = jwtService.generateToken(userMapper.toViewDTO(saved));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        try {
            log.info(request.getEmail());
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
                throw new AuthenticationException("Password is incorrect");
            var jwtToken = jwtService.generateToken(userMapper.toViewDTO(user));
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
    }

}
