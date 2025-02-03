package com.cursos.api.springsecuritycourse.dto.auth;

import java.io.Serializable;

public record AuthenticationResponse(
        String jwt
) implements Serializable { }
