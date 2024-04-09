package fr.hetic;
import java.io.*;

public class file_op {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileProcessor <chemin_dossier>");
            return;
        }
        File folder = new File(args[0]);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Le chemin spécifié n'est pas un répertoire valide.");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".op"));

        for (File file : files) {
            processFile(file);
        }
    }

    private static void processFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath().replace(".op", ".res")))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 3) {
                    writer.write("ERROR\n");
                    continue;
                }
                try {
                    double num1 = Double.parseDouble(parts[0]);
                    double num2 = Double.parseDouble(parts[1]);
                    String operator = parts[2];

                    double result;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        default:
                            writer.write("ERROR\n");
                            continue;
                    }

                    writer.write(String.valueOf(result) + "\n");
                } catch (NumberFormatException e) {
                    writer.write("ERROR\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du traitement du fichier : " + e.getMessage());
        }
    }
}
