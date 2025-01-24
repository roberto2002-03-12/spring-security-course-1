package com.cursos.api.springsecuritycourse.service.auth;

import com.cursos.api.springsecuritycourse.dto.RegisteredUser;
import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

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
}
