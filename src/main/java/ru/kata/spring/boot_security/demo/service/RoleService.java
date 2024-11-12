package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    List<Role> getRoles();
    Role getRole(int id);
    void deleteRole(int id);
    void updateRole(int id, Role role);
}
