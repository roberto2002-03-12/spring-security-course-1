package com.cursos.api.springsecuritycourse.service.auth;

import com.cursos.api.springsecuritycourse.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(UserDetails user) {
        return null;
    }
}
