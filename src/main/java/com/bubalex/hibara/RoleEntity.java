package com.bubalex.hibara;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    //Names
    public static final String CLIENT_ROLE_NAME = "ROLE_CLIENT";
    public static final String VIEW_CASES_ROLE_NAME = "ROLE_VIEW_CASES";
    public static final String DEPENDENT_ROLE_NAME = "ROLE_DEPENDENT";
    public static final String PETITIONER_ROLE_NAME = "ROLE_PETITIONER";
    public static final String INTERPRETER_ROLE_NAME = "ROLE_INTERPRETER";
    public static final String BENEFICIARY_ROLE_NAME = "ROLE_BENEFICIARY";
    public static final String MANAGE_CASES_ROLE_NAME = "ROLE_MANAGE_CASES";
    public static final String EMPLOYEE_ROLE_NAME = "ROLE_EMPLOYEE";
    public static final String IMMIGRATION_SPECIALIST_ROLE_NAME = "ROLE_IMMIGRATION_SPECIALIST";
    public static final String ATTORNEY_ROLE_NAME = "ROLE_ATTORNEY";
    public static final String ATTORNEY_MANAGER_ROLE_NAME = "ROLE_ATTORNEY_MANAGER";
    public static final String SUPER_ADMIN_ROLE_NAME = "ROLE_SUPER_ADMIN";


    //Display names
    public static final String CLIENT_ROLE_DISPLAY_NAME = "Client";
    public static final String VIEW_CASES_ROLE_DISPLAY_NAME = "View cases";
    public static final String DEPENDENT_ROLE_DISPLAY_NAME = "Dependent";
    public static final String PETITIONER_ROLE_DISPLAY_NAME = "Petitioner";
    public static final String INTERPRETER_ROLE_DISPLAY_NAME = "Interpreter";
    public static final String BENEFICIARY_ROLE_DISPLAY_NAME = "Beneficiary";
    public static final String MANAGE_CASES_ROLE_DISPLAY_NAME = "Manage cases";
    public static final String EMPLOYEE_ROLE_DISPLAY_NAME = "Employee";
    public static final String IMMIGRATION_SPECIALIST_ROLE_DISPLAY_NAME = "Immigration specialist";
    public static final String ATTORNEY_ROLE_DISPLAY_NAME = "Attorney";
    public static final String ATTORNEY_MANAGER_ROLE_DISPLAY_NAME = "Attorney manager";
    public static final String SUPER_ADMIN_ROLE_DISPLAY_NAME = "Super admin";
    @Column(name = "authority")
    private Integer authority;

    @Column(name = "name")
    private String name;

    @FullTextField
    @Column(name = "display_name")
    private String displayName;

    @OneToMany(mappedBy = "role")
    private List<UserRoleEntity> userRoles;


    public enum Type {
        BENEFICIARY(BENEFICIARY_ROLE_NAME),
        PETITIONER(PETITIONER_ROLE_NAME),
        DEPENDENT(DEPENDENT_ROLE_NAME),
        ATTORNEY(ATTORNEY_ROLE_NAME);

        @Getter
        private final String roleName;


        Type(String roleName) {
            this.roleName = roleName;
        }
    }
}
