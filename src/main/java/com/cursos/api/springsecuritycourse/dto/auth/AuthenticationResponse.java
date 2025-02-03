package com.cursos.api.springsecuritycourse.dto;

import java.io.Serializable;

public record AuthenticationResponse(
        String jwt
) implements Serializable { }
