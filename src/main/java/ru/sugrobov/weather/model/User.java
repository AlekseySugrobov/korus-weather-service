package ru.sugrobov.weather.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @NotNull
  private String email;
  @NotNull
  private String password;
  private String firstName;
  private String lastName;


  public User() {
  }

  public User(String email, String password, String firstName, String lastName) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}