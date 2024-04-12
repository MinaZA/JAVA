package fr.hetic.EXO2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class file_op {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Veuillez fournir le chemin absolu du dossier en argument.");
            return;
        }

        File directory = new File(args[0]);
        File[] files = directory.listFiles();

        if (files == null) {
            System.err.println("Le chemin spécifié ne correspond pas à un dossier valide.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".op")) {
                processFile(file);
            }
        }
    }

    private static void processFile(File opFile) {
        File resFile = new File(opFile.getParent(), opFile.getName().replace(".op", ".res"));

        try (Scanner scanner = new Scanner(opFile);
             FileWriter writer = new FileWriter(resFile)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(" ");

                if (parts.length != 3) {
                    writer.write("ERROR: Format d'opération incorrect\n");
                    continue;
                }

                double num1, num2;
                char operator;
                try {
                    num1 = Double.parseDouble(parts[0]);
                    num2 = Double.parseDouble(parts[1]);
                    operator = parts[2].charAt(0);
                } catch (NumberFormatException e) {
                    writer.write("ERROR: Les deux premiers arguments doivent être des nombres.\n");
                    continue;
                }

                double result;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    default:
                        writer.write("ERROR: Opérateur non pris en charge\n");
                        continue;
                }

                writer.write(result + "\n");
            }
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de la lecture ou de l'écriture des fichiers.");
            e.printStackTrace();
        }
    }
}

