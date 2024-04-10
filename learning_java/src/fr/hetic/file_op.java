package fr.hetic;
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
        String[] flist = directory.list();

        if (flist == null) {
            System.err.println("Le chemin spécifié ne correspond pas à un dossier valide.");
            return;
        }

        for (String filename : flist) {
            File opFile = new File(directory, filename);
            if (!opFile.isFile() || !filename.endsWith(".txt")) {
                continue;  
            }
            
            File resFile = new File(directory, filename.substring(0, filename.length() - 4) + ".res");

            try (Scanner readerOp = new Scanner(opFile);
                 FileWriter writerRes = new FileWriter(resFile)) {

                while (readerOp.hasNextLine()) {
                    String data = readerOp.nextLine();
                    if (data.length() < 5 || !Character.isDigit(data.charAt(0)) || !Character.isDigit(data.charAt(2))) {
                        writerRes.write("ERROR: Format d'opération incorrect\n");
                        continue;
                    }

                    int leftNumber = Character.getNumericValue(data.charAt(0));
                    int rightNumber = Character.getNumericValue(data.charAt(2));
                    char operator = data.charAt(4);

                    int result;
                    switch (operator) {
                        case '+':
                            result = leftNumber + rightNumber;
                            break;
                        case '-':
                            result = leftNumber - rightNumber;
                            break;
                        case '*':
                            result = leftNumber * rightNumber;
                            break;
                        default:
                            writerRes.write("ERROR: Opérateur non pris en charge\n");
                            continue;
                    }

                    writerRes.write(result + "\n");
                }
            } catch (IOException e) {
                System.err.println("Une erreur s'est produite lors de la lecture ou de l'écriture des fichiers.");
                e.printStackTrace();
            }
        }
    }
}
