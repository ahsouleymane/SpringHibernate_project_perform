package net.sancfis.springhibernate_project_perform.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 30)
    @NotEmpty
    @Size(min = 2, max = 30)
    private String nom;
    @Column(length = 30)
    @NotEmpty
    @Size(min = 2, max = 30)
    private String prenom;


    // Pour moi
    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Pour JPA et moi
    public Etudiant() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() { return prenom; }

    public void setPrenom(String prenom) { this.prenom = prenom; }


}
