package com.wcs_cp2.kiakwa.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table
public class Materiel {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID matId;
    private String nomMat;
    private String img;
    private String detail;
    // propriétaire de la ressource
    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user = new User();

    public Materiel() {
    }

    public Materiel(UUID matId, String nomMat, String img, String detail, User user) {
        this.matId = matId;
        this.nomMat = nomMat;
        this.img = img;
        this.detail = detail;
        this.user = user;
    }

    public UUID getMatId() {
        return this.matId;
    }

    public void setMatId(UUID matId) {
        this.matId = matId;
    }

    public String getNomMat() {
        return this.nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Materiel matId(UUID matId) {
        this.matId = matId;
        return this;
    }

    public Materiel nomMat(String nomMat) {
        this.nomMat = nomMat;
        return this;
    }

    public Materiel img(String img) {
        this.img = img;
        return this;
    }

    public Materiel detail(String detail) {
        this.detail = detail;
        return this;
    }

    public Materiel user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Materiel)) {
            return false;
        }
        Materiel materiel = (Materiel) o;
        return Objects.equals(matId, materiel.matId) && Objects.equals(nomMat, materiel.nomMat)
                && Objects.equals(img, materiel.img) && Objects.equals(detail, materiel.detail)
                && Objects.equals(user, materiel.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matId, nomMat, img, detail, user);
    }

    @Override
    public String toString() {
        return "{" + " matId='" + getMatId() + "'" + ", nomMat='" + getNomMat() + "'" + ", img='" + getImg() + "'"
                + ", detail='" + getDetail() + "'" + ", user='" + getUser() + "'" + "}";
    }

}