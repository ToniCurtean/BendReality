package com.toni.bendreality.security.jwt;

import com.toni.bendreality.user.model.UserCredentials;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.annotation.Nonnull;
import java.io.IOException;

import static com.toni.bendreality.security.jwt.JwtService.ClaimsEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) {
        final String requestTokenHeader = request.getHeader("Authorization");

        Claims claims = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);

            try {
                claims = jwtService.getClaims(jwtToken);
            } catch (Exception e) {
                log.trace(e.getMessage());
            }
        }

        if (nonNull(claims) && isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserCredentials userCredentials = new UserCredentials(
                    (Integer) claims.get(ID.name()),
                    (String) claims.get(EMAIL.name()),
                    (String) claims.get(ROLE.name())
            );
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userCredentials, null, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
