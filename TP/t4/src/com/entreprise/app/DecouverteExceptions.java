package com.entreprise.app;

import com.entreprise.compta.Facture;

public class DecouverteExceptions {

    public static void main(String[] args) {

        // tableau trop petit
        try {
            double[] primes = new double[3];
            primes[5] = 100;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getClass().getSimpleName()
                    + " : " + e.getMessage());
        }

        // conversion impossible
        try {
            double m = Double.parseDouble("abc");
        } catch (NumberFormatException e) {
            System.out.println(e.getClass().getSimpleName()
                    + " : " + e.getMessage());
        }

        // objet null
        try {
            Facture f = null;
            f.getMontantAPayer();
        } catch (NullPointerException e) {
            System.out.println(e.getClass().getSimpleName()
                    + " : " + e.getMessage());
        }

        // division par zéro
        try {
            int partParPersonne = 9000 / 0;
        } catch (ArithmeticException e) {
            System.out.println(e.getClass().getSimpleName()
                    + " : " + e.getMessage());
        } finally {
            System.out.println("Fin des essais");
        }

        /*
         * Avec 9000.0 / 0, il n'y a pas d'exception.
         * Le résultat est Infinity car on travaille avec un double.
         */

        /*
         * catch (Exception e) avant catch (NumberFormatException e)
         * provoque une erreur car Exception est plus générale.
         * Le catch spécifique doit être placé avant le catch général.
         */
    }
}
