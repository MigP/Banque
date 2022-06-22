package be.bf.banque.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class mutable représentant un titulaire de compte au sein de la banque
 * FA: Titulaire{nom, prenom}
 *
 * @attribute nom String
 * @attribute prenom String
 * @attribute dateNaissance LocalDate
 *
 * @invariant nom != null && nom.length > 0
 * @invariant prenom != null && prenom.length > 0
 */
public class Titulaire {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    public Titulaire(Titulaire titulaire) {
        this.setNom(titulaire.nom);
        this.setPrenom(titulaire.prenom);
        this.setDateNaissance(titulaire.dateNaissance);
    }
    public Titulaire(String nom, String prenom) {
        this(nom, prenom, LocalDate.of(1900, 1, 1));
    }
    public Titulaire(String nom, String prenom, LocalDate dateNaissance) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
    }
    public String getNom() {
        return nom;
    }
    private Titulaire setNom(String nom) {
        if (nom == null) return this;
        if (nom.length() <= 0) return this;

        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }
    private Titulaire setPrenom(String prenom) {
        if (prenom == null) return this;
        if (prenom.length() <= 0) return this;

        this.prenom = prenom;
        return this;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    public Titulaire setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }
    public Titulaire setDateNaissance(int year, int month, int day) {
        this.dateNaissance = LocalDate.of(year, month, day);
        return this;
    }

//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (!(o instanceof Titulaire)) return false;
//        Titulaire t = (Titulaire) o;
//        return this.equals(t);
//    }
//    private boolean equals(Titulaire t) {
//        if (!this.nom.equals(t.nom)) return false;
//        if (!this.prenom.equals(t.prenom)) return false;
//
//        return true;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titulaire titulaire = (Titulaire) o;
        return Objects.equal(nom, titulaire.nom) && Objects.equal(prenom, titulaire.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom, prenom);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nom", nom)
                .add("prenom", prenom)
                .toString();
    }
}
