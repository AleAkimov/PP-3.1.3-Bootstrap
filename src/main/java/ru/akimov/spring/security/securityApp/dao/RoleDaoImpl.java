package ru.akimov.spring.security.securityApp.dao;


import org.springframework.stereotype.Repository;
import ru.akimov.spring.security.securityApp.model.Role;
//import ru.akimov.spring.security.securityApp.reposiroty.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

//    private final RoleRepository roleRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
//        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r FROM Role r", Role.class).getResultList();
    }

//    public Role findByRoleName(String role) {
//        return roleRepository.findByRole(role);
//    }
@Override
public Role findByRole(String role) {
    try {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    } catch (NoResultException e) {
        return null; // Возвращаем null, если роль не найдена
    } catch (Exception e) {
        // Логируем другие ошибки
        System.err.println("Error fetching role: " + e.getMessage());
        throw e; // или обработайте ошибку соответствующим образом
    }
}
}
