package com.wcs_cp2.kiakwa.model;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eId;

    @Column(nullable = false )
    private String qui;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date quand;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date retour;
    
    @ManyToOne
    @JoinColumn(name = "matID", nullable = false)
    private Materiel materiel = new Materiel();  
    

    public Emprunt() {
    }

    public Emprunt(String qui, Date quand, Materiel materiel) {
        this.qui = qui;
        this.quand = quand;
        this.retour = null;
        this.materiel = materiel;
    }

    public Emprunt(Long eId, String qui, Date quand, Materiel materiel) {
        this.eId = eId;
        this.qui = qui;
        this.quand = quand;
        this.retour = null;
        this.materiel = materiel;
    }

    public Emprunt(Long eId, String qui, Date quand, Date retour, Materiel materiel) {
        this.eId = eId;
        this.qui = qui;
        this.quand = quand;
        this.retour = retour;
        this.materiel = materiel;
    }
    public Long getEId() {
        return this.eId;
    }

    public void setEId(Long eId) {
        this.eId = eId;
    }

    public String getQui() {
        return this.qui;
    }

    public void setQui(String qui) {
        this.qui = qui;
    }

    public Date getQuand() {
        return this.quand;
    }

    public void setQuand(Date quand) {
        this.quand = quand;
    }

    public Date getRetour() {
        return this.retour;
    }

    public void setRetour(Date retour) {
        this.retour = retour;
    }

    public Materiel getMateriel() {
        return this.materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Emprunt eId(Long eId) {
        this.eId = eId;
        return this;
    }

    public Emprunt qui(String qui) {
        this.qui = qui;
        return this;
    }

    public Emprunt quand(Date quand) {
        this.quand = quand;
        return this;
    }

    public Emprunt retour(Date retour) {
        this.retour = retour;
        return this;
    }

    public Emprunt materiel(Materiel materiel) {
        this.materiel = materiel;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Emprunt)) {
            return false;
        }
        Emprunt emprunt = (Emprunt) o;
        return Objects.equals(eId, emprunt.eId) && Objects.equals(qui, emprunt.qui) && Objects.equals(quand, emprunt.quand) && Objects.equals(retour, emprunt.retour) && Objects.equals(materiel, emprunt.materiel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eId, qui, quand, retour, materiel);
    }

    @Override
    public String toString() {
        return "{" +
            " eId='" + getEId() + "'" +
            ", qui='" + getQui() + "'" +
            ", quand='" + getQuand() + "'" +
            ", retour='" + getRetour() + "'" +
            ", materiel='" + getMateriel() + "'" +
            "}";
    }
    
}