package ru.akimov.spring.security.securityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akimov.spring.security.securityApp.dao.RoleDaoImpl;
import ru.akimov.spring.security.securityApp.dao.UserDaoImpl;
import ru.akimov.spring.security.securityApp.model.Role;
import ru.akimov.spring.security.securityApp.model.User;
import ru.akimov.spring.security.securityApp.service.RoleService;
import ru.akimov.spring.security.securityApp.service.UserService;


import javax.annotation.PostConstruct;


@Component
public class CreateTable {

    private final UserService userService;
    private final RoleService roleService;
    private final RoleDaoImpl roleDaoImpl;
    private final UserDaoImpl userDaoImpl;

    @Autowired
    public CreateTable(UserService userService, RoleService roleService, RoleDaoImpl roleDaoImpl, UserDaoImpl userDaoImpl) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleDaoImpl = roleDaoImpl;
        this.userDaoImpl = userDaoImpl;
    }

    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");

    private final User admin = new User("tommy@mail.ru", "J", "MIB", "admin");
    private final User user = new User("will@mail.ru", "K", "MIB", "admin");

    String[] roleAdmin = new String[]{"ROLE_ADMIN", "ROLE_USER"};
    String[] roleUser = new String[]{"ROLE_USER"};

    @PostConstruct
    public void create() {
        if (roleDaoImpl.findByRole(adminRole.getRole()) == null) {
            roleService.saveRole(adminRole);
        } else {
            System.out.println("Role " + adminRole.getRole() + " already exists.");
        }

        if (roleDaoImpl.findByRole(userRole.getRole()) == null) {
            roleService.saveRole(userRole);
        } else {
            System.out.println("Role " + userRole.getRole() + " already exists.");
        }

        if (userDaoImpl.findUserByEmail(admin.getEmail()) == null) {
            userService.saveUser(admin, roleAdmin);
        } else {
            System.out.println("User " + admin.getEmail() + " already exists.");
        }

        if (userDaoImpl.findUserByEmail(user.getEmail()) == null) {
            userService.saveUser(user, roleUser);
        } else {
            System.out.println("User " + user.getEmail() + " already exists.");
        }
    }
}
//    @PostConstruct
//    public void create() {
//        if (roleDaoImpl.findByRoleName(adminRole.getRole()) == null) {
//            roleService.saveRole(adminRole);
//        }
//        if (roleDaoImpl.findByRoleName(userRole.getRole()) == null) {
//            roleService.saveRole(userRole);
//        }
//
//        if (userDaoImpl.getUserByEmail(admin.getEmail()) == null) {
//            userService.saveUser(admin, roleAdmin);
//        }
//        if (userDaoImpl.getUserByEmail(user.getEmail()) == null) {
//            userService.saveUser(user, roleUser);
//        }
//    }
//}