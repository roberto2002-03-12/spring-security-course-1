package com.cursos.api.springsecuritycourse.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCT,
            RolePermission.CREATE_PRODUCT,
            RolePermission.UPDATE_PRODUCT,
            RolePermission.DISABLE_PRODUCT,
            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_ONE_CATEGORY,
            RolePermission.CREATE_CATEGORY,
            RolePermission.UPDATE_CATEGORY,
            RolePermission.DISABLE_CATEGORY,
            RolePermission.READ_MY_PROFILE
    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCT,
            RolePermission.UPDATE_PRODUCT,

            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_ONE_CATEGORY,
            RolePermission.UPDATE_CATEGORY,

            RolePermission.READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCT,

            RolePermission.READ_ALL_CATEGORIES,
            RolePermission.READ_ONE_CATEGORY,

            RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
