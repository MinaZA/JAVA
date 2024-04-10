package fr.hetic.Exo3;

public class Calculateur {
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

        try {
            Operation operation = Operateur.createOperation(operateur);
            double resultat = operation.execute(num1, num2);
            System.out.println("Résultat : " + resultat);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

