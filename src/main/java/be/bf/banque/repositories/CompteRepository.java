package be.bf.banque.repositories;

import be.bf.banque.models.Compte;
import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.CompteEpargne;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class CompteRepository extends Repository {

    protected Compte fromResultSet(ResultSet resultSet) throws SQLException {
        String numero = resultSet.getString("numero");
        double solde = resultSet.getDouble("solde");

            if (Objects.equals(resultSet.getString("desc"), "COURANT")) {
                return new CompteCourant(numero, solde);
            } else if (Objects.equals(resultSet.getString("desc"), "EPARGNE")) {
                return new CompteEpargne(numero, null, solde);
            } else {
                return null;
            }
    }

    public ArrayList<Compte> findAll() throws SQLException {
        open();
        ResultSet resultSet = executeQuery("SELECT * FROM Compte");

        ArrayList<Compte> comptes = new ArrayList<>();

        while (resultSet.next()) {
            comptes.add(fromResultSet(resultSet));
        }
        close();
        return comptes;
    }

    public Compte findById(int id) throws SQLException {
        open();
        PreparedStatement statement = preparedStatement("SELECT * FROM Compte WHERE id = ?");
        statement.setInt(1, id);

        ResultSet resultSet = executeQuery(statement);
        if (!resultSet.next()) {
            close();
            return null;
        }
        Compte t = fromResultSet(resultSet);
        close();
        return t;
    }
}
