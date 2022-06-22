package be.bf.banque.models;

import com.google.common.base.MoreObjects;

/**
 * Classe mutable représentant un compte pouvant aller en négatif
 * FA: Compte{numero, solde}
 *
 * @attribute <b>numero</b> {@link String String} => Format IBAN BEXX XXXX XXXX XXXX
 * @attribute <b>solde</b> {@link Double double}
 * @attribute <b>titulaire</b> {@link Titulaire Titulaire}
 *
 * @invariant numero != null and numero.length = 19
 * @invariant titulaire != null
 */
public abstract class Compte {
    private String numero;
    private double solde;
    private Titulaire titulaire;

    public Compte(String numero, double solde) {
        this.solde = solde;
        this.numero = numero;
    }
    public Compte(String numero, Titulaire titulaire) {
        this.setNumero(numero);
        this.setTitulaire(titulaire);
        this.solde = 0;
    }
    public Compte(String numero, Titulaire titulaire, double solde) {
        this(numero, titulaire);
        this.solde = solde;
    }

    public String getNumero() { return this.numero; }
    public Titulaire getTitulaire() { return new Titulaire(this.titulaire); }
    public double getSolde() { return this.solde; }

    private Compte setNumero(String numero) {
        if (numero == null) return this;
        if (numero.length() != 19) return this;

        this.numero = numero;
        return this;
    }
    private Compte setTitulaire(Titulaire titulaire) {
        if (titulaire == null) return this;

        this.titulaire = new Titulaire(titulaire);
        return this;
    }

    public void depot(double montant) {
        if (montant < 0) return;

        this.solde += montant;
    }

    /**
     * Procédure de retrait d'un montant du solde du compte
     * @param montant montant > 0
     */
    public void retrait(double montant) {
        if (montant < 0) return;

        this.solde -= montant;
    }

    public abstract double calculInteret();

    public void appliquerInteret() {
        this.solde += calculInteret();
    }

    public double plus(Compte c) {
        return this.solde + c.solde;
    }
    public double plus(double amount) {
        return this.solde + amount;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("numero= ").append(numero).append(", ");
        builder.append("solde= ").append(solde).append(", ");
        builder.append("titulaire= ").append(titulaire).append(", ");

        return builder.toString();
    }
}
