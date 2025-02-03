package com.cursos.api.springsecuritycourse.dto.auth;

import com.cursos.api.springsecuritycourse.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

public record ProfileDetailsResponse(
        Long id,
        String username,
        String name,
        String role,
        boolean enabled,
        boolean credentialsNonExpired,
        boolean accountNonExpired,
        List<String> authorities,
        boolean accountNonLocked
) implements Serializable {

    // this is bad code but is a short way to convert to dto
    public static ProfileDetailsResponse userDetailsToDto(User user) {
        return new ProfileDetailsResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole().name(),
                user.isEnabled(),
                user.isCredentialsNonExpired(),
                user.isAccountNonExpired(),
                user.getAuthorities().stream().map(Object::toString).toList(),
                user.isAccountNonLocked()
        );
    }

}
