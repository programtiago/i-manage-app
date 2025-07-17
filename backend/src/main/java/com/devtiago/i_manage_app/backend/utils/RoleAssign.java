package com.devtiago.i_manage_app.backend.utils;

import com.devtiago.i_manage_app.backend.entity.enums.UserRole;

import java.util.Map;
public class RoleAssign {

    private static final Map<String, UserRole> DEPARTMENT_ROLE_MAP = Map.of(
            "Human Resources", UserRole.RH,
            "Production", UserRole.PROD,
            "Administration", UserRole.ADMIN,
            "Information Technology", UserRole.IT
    );

    /**
     * Resolves a {@link UserRole} based on the given department name
     *
     * This method uses a predefined mapping with a static Map called DEPARTMENT_ROLE_MAP
     *
     * @param department the name of the department (must be not null)
     * @return the matching {@link UserRole} for the given department
     * @throws IllegalArgumentException if the department is null
     * @throws IllegalStateException if no matching role is found for the department
     */
    public static UserRole resolveRole(String department){
        if (department == null) throw new IllegalArgumentException("Department cannot be null");

        UserRole role = DEPARTMENT_ROLE_MAP.get(department.trim());

        if (role == null) throw new IllegalStateException("Department without match role " + department);

        return role;
    }
}
