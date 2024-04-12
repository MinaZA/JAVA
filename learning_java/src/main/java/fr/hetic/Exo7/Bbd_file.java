package fr.hetic.Exo7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bbd_file {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String user = "etudiant";
        String password = "MT4@hetic2324";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie à la base de données PostgreSQL !");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
}

