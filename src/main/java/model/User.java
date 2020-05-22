package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "surname")
    private String surname;
    @Column (name = "name")
    private String name;
    @Column (name = "password")
    private String password;
    @Column (name = "email")
    private String email;

    public User() {

    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String surname, String name, String password, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String surname, String name, String password, String email) {
        this.surname = surname;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, password, email);
    }
}
