package com.cursos.api.springsecuritycourse.service.impl;

import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.exception.InvalidPasswordException;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.persistence.repository.UserRepository;
import com.cursos.api.springsecuritycourse.persistence.util.Role;
import com.cursos.api.springsecuritycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerOneCustomer(SaveUser newUser) {
        User user = new User();

        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }

    private void validatePassword(SaveUser dto) {
        if (!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatPassword()))
            throw new InvalidPasswordException("Password don't match", null);

        if (!dto.getPassword().equals(dto.getRepeatPassword()))
            throw new InvalidPasswordException("Password don't match", null);

    }
}
