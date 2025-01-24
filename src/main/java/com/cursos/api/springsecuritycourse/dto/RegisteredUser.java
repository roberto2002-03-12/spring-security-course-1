package com.cursos.api.springsecuritycourse.dto;

import java.io.Serializable;

public record RegisteredUser(
        String username,
        String name,
        String role,
        String jwt
) implements Serializable {
}
