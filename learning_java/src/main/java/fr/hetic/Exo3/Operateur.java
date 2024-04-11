package fr.hetic.Exo3;

public class Operateur {
    public static Operation createOperation(String operateur) {
        switch (operateur) {
            case "+":
                return new Addition();
            case "-":
                return new Soustraction();
            case "*":
                return new Multiplication();
            default:
                throw new IllegalArgumentException("Opérateur invalide. Utilisez '+', '-' ou '*'.");
        }
    }
}
