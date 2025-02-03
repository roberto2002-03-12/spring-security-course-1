package com.cursos.api.springsecuritycourse.dto;

import java.io.Serializable;

public record AuthenticationRequest(
        String username,
        String password
) implements Serializable { }
