package be.bf.banque.models;


import com.google.common.base.MoreObjects;
/**
 * Classe mutable représentant un compte pouvant aller en négatif
 * FA: CompteCourant{numero, solde, ligneCredit}
 *
 * @attribute ligneCredit double
 *
 * @invariant ligneCredit >= 0
 * @see be.bf.banque.models.Compte
 */
public class CompteCourant extends Compte {
    private double ligneCredit;

    public CompteCourant(String numero) {
        super(numero, 0);
    }
    public CompteCourant(String numero, double solde) {
        super(numero, solde);
    }
    public CompteCourant(String numero, Titulaire titulaire) {
        super(numero, titulaire);
        this.ligneCredit = 0;
    }
    public CompteCourant(String numero, Titulaire titulaire, double ligneCredit) {
        super(numero, titulaire);
        this.setLigneCredit(ligneCredit);
    }
    public CompteCourant(String numero, Titulaire titulaire, double ligneCredit, double solde) {
        super(numero, titulaire, solde);
        this.setLigneCredit(ligneCredit);
    }

    public double getLigneCredit() { return this.ligneCredit; }
    public void setLigneCredit(double ligneCredit) {
        if (ligneCredit < 0) return;

        this.ligneCredit = ligneCredit;
    }

    /**
     * @see Compte#retrait
     */
    public void retrait(double montant) {
        if (getSolde() - montant < -this.ligneCredit) return;

        super.retrait(montant);
    }

    @Override
    public double calculInteret() {
        return getSolde() * (getSolde() >= 0 ? 0.01 : 0.075);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Courant{");

        builder.append(super.toString());
        builder.append("ligneCredit= ").append(ligneCredit);

        return builder.append("}").toString();
    }
}
