package main.java.fr.hetic.Exo4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class file_op {
    private static final Map<Character, BinaryOperator> OPERATORS = new HashMap<>();

    static {
        OPERATORS.put('+', (a, b) -> a + b);
        OPERATORS.put('-', (a, b) -> a - b);
        OPERATORS.put('*', (a, b) -> a * b);
    }

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
            if (file.isFile() && file.getName().endsWith(".txt")) {
                processFile(file);
            }
        }
    }

    private static void processFile(File opFile) {
        File resFile = new File(opFile.getParent(), opFile.getName().replace(".txt", ".res"));
        try (Scanner scanner = new Scanner(opFile);
             FileWriter writer = new FileWriter(resFile)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (!data.matches("\\d+ \\d+ [+-\\/*]")) {
                    writer.write("ERROR: Format d'opération incorrect\n");
                    continue;
                }

                String[] parts = data.split(" ");
                int leftNumber = Integer.parseInt(parts[0]);
                int rightNumber = Integer.parseInt(parts[1]);
                char operator = parts[2].charAt(0);

                BinaryOperator operation = OPERATORS.get(operator);
                if (operation == null) {
                    writer.write("ERROR: Opérateur non pris en charge\n");
                    continue;
                }

                int result = operation.apply(leftNumber, rightNumber);
                writer.write(result + "\n");
            }
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de la lecture ou de l'écriture des fichiers.");
            e.printStackTrace();
        }
    }

    private interface BinaryOperator {
        int apply(int a, int b);
    }
}
