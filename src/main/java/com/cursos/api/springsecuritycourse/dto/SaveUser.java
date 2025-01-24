package com.cursos.api.springsecuritycourse.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveUser(
        @Size(min = 3, max = 45)
        String username,

        @Size(min = 3, max = 65)
        String name,

        @Size(min = 8, max = 32)
        String password,

        @Size(min = 8, max = 32)
        String repeatPassword
) implements Serializable {
}
