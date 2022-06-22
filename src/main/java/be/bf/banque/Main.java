package be.bf.banque;


import be.bf.banque.models.*;
import be.bf.banque.repositories.TitulaireRepository;
import be.bf.banque.repositories.CompteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");

        TitulaireRepository repository = new TitulaireRepository();

        List<Titulaire> titulaires = repository.findAll();
        for(Titulaire t: titulaires) {
            System.out.println(t);
        }
        System.out.println(repository.findById(1));

        CompteRepository repository2 = new CompteRepository();

        List<Compte> comptes = repository2.findAll();
        for(Compte c: comptes) {
            System.out.println(c);
        }


    }
}
