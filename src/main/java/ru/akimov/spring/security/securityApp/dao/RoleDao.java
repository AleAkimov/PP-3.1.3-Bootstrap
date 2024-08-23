package ru.akimov.spring.security.securityApp.dao;

import ru.akimov.spring.security.securityApp.model.Role;

import java.util.List;

public interface RoleDao {
    void saveRole(Role role);
    List<Role> getAllRoles();
    Role findByRole(String role);
}