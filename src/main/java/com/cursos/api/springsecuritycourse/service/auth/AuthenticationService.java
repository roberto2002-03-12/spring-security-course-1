package com.cursos.api.springsecuritycourse.service.auth;

import com.cursos.api.springsecuritycourse.dto.RegisteredUser;
import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.dto.auth.AuthenticationRequest;
import com.cursos.api.springsecuritycourse.dto.auth.AuthenticationResponse;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.service.UserService;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public RegisteredUser registerOneCustomer(SaveUser newUser) {
        User user = userService.registerOneCustomer(newUser);

        RegisteredUser registeredUser = new RegisteredUser();

        registeredUser.setId(user.getId());
        registeredUser.setUsername(user.getUsername());
        registeredUser.setName(user.getName());
        registeredUser.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        registeredUser.setJwt(jwt);

        return registeredUser;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(), authenticationRequest.password());

        authenticationManager.authenticate(authentication);

        UserDetails userDetails = userService.findOneByUsername(authenticationRequest.username());
        String jwt = jwtService.generateToken(userDetails, generateExtraClaims((User) userDetails));

        AuthenticationResponse authRes = new AuthenticationResponse(jwt);

        return authRes;
    }

    public boolean validateToken(String token) {
        try {
            jwtService.extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
