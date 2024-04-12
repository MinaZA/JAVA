package fr.hetic.Exo7;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Bbd_file  {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String user = "etudiant";
        String password = "MT4@hetic2324";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectFilesQuery = "SELECT ID, NOM FROM FICHIER WHERE TYPE = 'OP'";
            try (PreparedStatement filesStatement = connection.prepareStatement(selectFilesQuery)) {
                ResultSet filesResult = filesStatement.executeQuery();
                while (filesResult.next()) {
                    int fileId = filesResult.getInt("ID");
                    String fileName = filesResult.getString("NOM");
                    String fileContent = generateFileContent(connection, fileId);
                    writeFile(fileName, fileContent);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateFileContent(Connection connection, int fileId) throws SQLException {
        StringBuilder contentBuilder = new StringBuilder();
        String selectLinesQuery = "SELECT PARAM1, PARAM2, OPERATEUR FROM LIGNE WHERE FICHIER_ID = ?";
        try (PreparedStatement linesStatement = connection.prepareStatement(selectLinesQuery)) {
            linesStatement.setInt(1, fileId);
            ResultSet linesResult = linesStatement.executeQuery();
            while (linesResult.next()) {
                int param1 = linesResult.getInt("PARAM1");
                int param2 = linesResult.getInt("PARAM2");
                char operator = linesResult.getString("OPERATEUR").charAt(0);
                contentBuilder.append(param1).append(" ").append(param2).append(" ").append(operator).append("\n");
            }
        }
        return contentBuilder.toString();
    }

    private static void writeFile(String fileName, String content) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        }
    }
}


