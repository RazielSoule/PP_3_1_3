package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "name")
   @NotEmpty(message = "Enter first name")
   @Size(min = 2, max = 50, message = "wrong name length")
   private String firstName;

   @Column(name = "last_name")
   @NotEmpty(message = "Enter last name")
   @Size(min = 2, max = 50, message = "wrong name length")
   private String lastName;

   @Column(name = "password")
   @NotEmpty(message = "Enter password")
   private String password;

   @Column(name = "email")
   @NotEmpty(message = "Enter email")
   @Email(message = "Enter correct email")
   private String email;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
         name = "users_roles",
         joinColumns = @JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "role_id"))
   private List<Role> roles;

   public User() {}

   public User(String firstName, String lastName, String password, String email, List<Role> roles) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      this.email = email;
      this.roles = roles;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<Role> getRoles() {
      return roles;
   }

   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
