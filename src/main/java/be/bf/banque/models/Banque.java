package be.bf.banque.models;

import java.util.*;

/**
 * Classe mutable représentant un ensemble de compte
 * FA: Banque{nom, qttCompte}
 *
 * @attribute nom String
 * @attribute comptes Map |key=String && value= CompteCourant
 *
 * @invariant nom != null && nom.length > 0
 * @invariant comptes != null
 */
public class Banque {
    private String nom;
    private HashMap<String, Compte> comptes = new HashMap<String, Compte>();

    public Banque(String nom) {
        this.setNom(nom);
    }

    private Banque setNom(String nom) {
        if (nom == null) return this;
        if (nom.length() <= 0) return this;

        this.nom = nom;
        return this;
    }
    public String getNom() {
        return this.nom;
    }

    private boolean containsAccount(String numero) {
        return this.comptes.containsKey(numero);
    }

    /**
     * Fonction permettant la récupération d'un compte si il existe
     * @param numero String
     * @return compte
     */
    public Optional<Compte> get(String numero) {
        if (!this.containsAccount(numero)) return Optional.empty();
        return Optional.of(this.comptes.get(numero));
    }
    public ArrayList<Compte> get(Titulaire titulaire) {
        ArrayList<Compte> comptes = new ArrayList<>();

        for(Compte c: this.comptes.values()) {
            if (!c.getTitulaire().equals(titulaire)) continue;

            comptes.add(c);
        }

        return comptes;
    }

    /**
     * Fonction permettant l'ajout d'un compte dans la banque
     * @param compte CompteCourant
     * @modify comptes tq comptes.length = comptes.length + 1
     * @return this
     */
    public Banque add(Compte compte) {
        if (this.containsAccount(compte.getNumero())) return this;
        this.comptes.put(compte.getNumero(), compte);
        return this;
    }

    public Banque remove(String numero) {
        if (!this.containsAccount(numero)) return this;
        this.comptes.remove(numero);
        return this;
    }

    public Map.Entry<String, Compte>[] getComptes() {
        Map<String, Compte> copy = new HashMap<String, Compte>();
        for(Map.Entry<String, Compte> entry: this.comptes.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy.entrySet().toArray(new Map.Entry[0]);
    }

    public double avoirDesComptes(Titulaire titulaire) {
        ArrayList<Compte> comptes = this.get(titulaire);
        double total = 0;

        for(Compte c: comptes) {
            if (c.getSolde() >= 0) {
                total = c.plus(total);
            }
        }

        return total;
    }

    public void appliquerInteret() {
        for(Compte c: comptes.values()) {
            c.appliquerInteret();
        }
    }
}
