package com.cursos.api.springsecuritycourse.dto.auth;

import java.io.Serializable;

public record AuthenticationRequest(
        String username,
        String password
) implements Serializable { }
