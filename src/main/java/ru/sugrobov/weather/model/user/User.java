package ru.sugrobov.weather.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String password = "";

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Role role;

    @JsonIgnore
    @Getter
    @Setter private boolean isActive;

    public User(){
        this("new", "PASSWORD", "new", "new", "", Role.USER, true);
    }

    public User(String userId, String password, String firstName, String lastName){
        this(userId, password, firstName, lastName, "", Role.USER, true);
    }

    public User(String userId, String password, Role role, String firstName, String lastName, boolean isActive){
        this(userId, password, firstName, lastName, "", role, isActive);
    }

    public User(String userId, String password, String firstName, String lastName, String email, Role role,
                boolean isActive) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }

    public String getFullName(){
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
