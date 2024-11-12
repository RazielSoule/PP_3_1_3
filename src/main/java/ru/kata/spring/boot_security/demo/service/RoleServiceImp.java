package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {

   private final RoleRepository roleRepository;

   @Autowired
   public RoleServiceImp(RoleRepository roleRepository) {
      this.roleRepository = roleRepository;
   }

   @Transactional
   @Override
   public void addRole(Role role) {
      roleRepository.save(role);
   }

   @Override
   public List<Role> getRoles() {
      return roleRepository.findAll();
   }

   @Override
   public Role getRole(int id) {
      return roleRepository.findById(id).orElse(null);
   }

   @Transactional
   @Override
   public void deleteRole(int id) {
      roleRepository.deleteById(id);
   }

   @Transactional
   @Override
   public void updateRole(int id, Role role) {
      role.setId((long) id);
      roleRepository.save(role);
   }
}
