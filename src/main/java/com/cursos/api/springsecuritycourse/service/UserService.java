package com.cursos.api.springsecuritycourse.service;

import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User registerOneCustomer(SaveUser newUser);

    UserDetails findOneByUsername(String username);
}
