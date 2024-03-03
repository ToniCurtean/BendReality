package com.toni.bendreality.security;

import com.toni.bendreality.user.model.UserCredentials;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Objects.nonNull;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @SneakyThrows
    @Nullable
    public static Integer getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(nonNull(authentication) && authentication.getPrincipal() instanceof UserCredentials principal){
            return principal.id();
        }
        return null;
    }

    @SneakyThrows
    @Nullable
    public static String getCurrentUserRole(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(nonNull(authentication) && authentication.getPrincipal() instanceof UserCredentials principal){
            return principal.role().toString();
        }
        return null;
    }

}
