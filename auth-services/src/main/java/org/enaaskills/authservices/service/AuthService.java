package org.enaaskills.authservices.service;

import lombok.RequiredArgsConstructor;

import org.enaaskills.authservices.auth.AuthenticationRequest;
import org.enaaskills.authservices.auth.AuthenticationResponse;
import org.enaaskills.authservices.auth.RegisterRequest;
import org.enaaskills.authservices.model.User;
import org.enaaskills.authservices.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public boolean validateToken(String token) {
        // This method will be used by the API Gateway to validate the token
        // For simplicity, we'll just check if the token is not expired and has a username
        // A more robust validation would involve checking against a revoked token list or similar
        try {
            String username = jwtService.extractUsername(token);
            return jwtService.isTokenValid(token, repository.findByUsername(username).orElseThrow());
        } catch (Exception e) {
            return false;
        }
    }
}
