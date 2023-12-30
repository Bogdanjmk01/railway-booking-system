package com.bogdanjmk.railwaybookingsystem.query;

public class RoleQuery {
    public static final String SELECT_ROLES_QUERY = "SELECT * FROM roles ORDER BY id";
    public static final String SELECT_ROLE_BY_USER_ID_QUERY = """
               SELECT r.id, r.name, r.description FROM roles r JOIN user_roles ur ON ur.role_id = r.id JOIN users u ON u.id = ur.user_id WHERE u.id = :id
            """;
}
