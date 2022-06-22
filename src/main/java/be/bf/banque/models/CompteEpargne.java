package be.bf.banque.models;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
/**
 * Classe mutable représentant un compte pouvant aller en négatif
 * FA: CompteEpargne{numero, solde, dateDernierRetrait}
 *
 * @attribute dateDernierRetrait double
 *
 * @see be.bf.banque.models.Compte
 */
public class CompteEpargne extends Compte {
    private LocalDate dateDernierRetrait;

    public CompteEpargne(String numero, Titulaire titulaire) {
        super(numero, titulaire);
    }
    public CompteEpargne(String numero, Titulaire titulaire, double solde) {
        super(numero, titulaire, solde);
    }

    public LocalDate getDateDernierRetrait() { return this.dateDernierRetrait; }

    /**
     * @see Compte#retrait
     */
    public void retrait(double montant) {
        if (getSolde() - montant < 0) return;

        super.retrait(montant);
        this.dateDernierRetrait = LocalDate.now();
    }

    @Override
    public double calculInteret() {
        return getSolde() * 0.1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Epargne{");

        builder.append(super.toString());
        builder.append("dateDernierRetrait= ").append(dateDernierRetrait);

        return builder.append("}").toString();
    }
}
