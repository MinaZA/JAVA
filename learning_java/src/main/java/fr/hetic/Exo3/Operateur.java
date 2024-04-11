package fr.hetic.Exo3;

public class Operateur {
    public static Operation createOperation(char operator) {
        switch (operator) {
            case '+':
                return new Addition();
            case '-':
                return new Soustraction();
            case '*':
                return new Multiplication();
            default:
                throw new IllegalArgumentException("Opérateur non pris en charge : " + operator);
        }
    }
}

