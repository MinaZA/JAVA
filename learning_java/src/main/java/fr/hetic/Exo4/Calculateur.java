package main.java.fr.hetic.Exo4;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Calculateur {
    private static final Map<String, BiFunction<Double, Double, Double>> OPERATIONS = new HashMap<>();

    static {
        OPERATIONS.put("+", Double::sum);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Calculateur : <nombre> <nombre> <opérateur>");
            return;
        }

        double num1, num2;
        String operateur;
        try {
            num1 = Double.parseDouble(args[0]);
            num2 = Double.parseDouble(args[1]);
            operateur = args[2];
        } catch (NumberFormatException e) {
            System.out.println("Les deux premiers arguments sont être des nombres.");
            return;
        }

        BiFunction<Double, Double, Double> operation = OPERATIONS.get(operateur);
        if (operation == null) {
            System.out.println("Opérateur invalide. Utilisez '+', '-' ou '*'.");
            return;
        }

        double resultat = operation.apply(num1, num2);
        System.out.println("Résultat : " + resultat);
    }
}
