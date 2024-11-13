package ru.kata.spring.boot_security.demo.service;

import jakarta.validation.constraints.Null;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
       this.roleRepository = roleRepository;
       this.passwordEncoder = passwordEncoder;
   }

   @Transactional
   @Override
   public void addUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      if(user.getRoles() == null) {
         user.setRoles(List.of(roleRepository.findById(2).orElse(null)));
      }
      userRepository.save(user);
   }

   @Override
   public List<User> getUsers() {
      return userRepository.findAll();
   }

   @Override
   public User getUser(int id) {
      return userRepository.findById(id).orElse(null);
   }

   @Override
   public User getUserByUsername(String username) {
      return userRepository.findByEmail(username).orElse(null);
   }

   @Transactional
   @Override
   public void deleteUser(int id) {
      userRepository.deleteById(id);
   }

   @Transactional
   @Override
   public void updateUser(int id, User user) {
      user.setId((long) id);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }

   @Transactional
   @Override
   public void updateUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }
}
