package be.bf.banque.repositories;

import be.bf.banque.models.Titulaire;

import java.sql.*;
import java.util.ArrayList;

public class TitulaireRepository extends Repository {

    protected Titulaire fromResultSet(ResultSet resultSet) throws SQLException {
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");

        return new Titulaire(nom, prenom);
    }

    public ArrayList<Titulaire> findAll() throws SQLException {
        open();
        ResultSet resultSet = executeQuery("SELECT * FROM Titulaire");

        ArrayList<Titulaire> titulaires = new ArrayList<>();

        while (resultSet.next()) {
            titulaires.add(fromResultSet(resultSet));
        }
        close();
        return titulaires;
    }

    public Titulaire findById(int id) throws SQLException {
        open();
        PreparedStatement statement = preparedStatement("SELECT * FROM Titulaire WHERE id = ?");
        statement.setInt(1, id);

        ResultSet resultSet = executeQuery(statement);
        if (!resultSet.next()) {
            close();
            return null;
        }
        Titulaire t = fromResultSet(resultSet);
        close();
        return t;
    }
}
