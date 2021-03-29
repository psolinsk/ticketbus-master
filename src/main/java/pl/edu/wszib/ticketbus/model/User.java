package pl.edu.wszib.ticketbus.model;

import javax.persistence.*;

@Entity(name = "userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String pass;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(int id, String login, String pass, Role role) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User clone(){
        return new User(this.id, this.login, this.pass, this.role);
    }

    @Override
    public String toString() {
        return login;
    }

    public enum Role{
        ADMIN,
        USER
    }

}
