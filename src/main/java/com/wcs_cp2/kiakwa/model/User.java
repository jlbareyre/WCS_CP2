package com.wcs_cp2.kiakwa.model;

import org.hibernate.annotations.Type;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID userID;
    private String name;
    private String pass;
    private String mail;
    private String role;

    public User() {
        this.userID = UUID.randomUUID();
        // this.name = "";
        // this.pass = "";
        // this.mail = "";
        this.role = "BaseUser";
    }

    public User(String name, String pass, String mail, String role) {
        this.userID = UUID.randomUUID();
        this.name = name;
        this.pass = pass;
        this.mail = mail;
        this.role = role;
    }

    public User(UUID UserID, String name, String pass, String mail, String role) {
        this.userID = UserID;
        this.name = name;
        this.pass = pass;
        this.mail = mail;
        this.role = role;
    }

    public UUID getUserID() {
        return this.userID;
    }

    public void setUserID(UUID UserID) {
        this.userID = UserID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User UserID(UUID UserID) {
        this.userID = UserID;
        return this;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public User pass(String pass) {
        this.pass = pass;
        return this;
    }

    public User mail(String mail) {
        this.mail = mail;
        return this;
    }

    public User role(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userID, user.userID) && Objects.equals(name, user.name) && Objects.equals(pass, user.pass)
                && Objects.equals(mail, user.mail) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, pass, mail, role);
    }

    @Override
    public String toString() {
        return "{" + " userID='" + getUserID() + "'" + ", name='" + getName() + "'" + ", mail='" + getMail() + "'"
                + ", role='" + getRole() + "'" + "}";
    }

}