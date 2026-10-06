package com.entreprise.app;

import com.entreprise.compta.Facture;
import com.entreprise.compta.payable;
import com.entreprise.exception.EquipeCompletException;
import com.entreprise.exception.MontantInvalideException;
import com.entreprise.rh.Augmentable;
import com.entreprise.rh.Commercial;
import com.entreprise.rh.Employe;
import com.entreprise.rh.ChefProjet;
import com.entreprise.rh.Permanent;

public class Program {

    public static void main(String[] args) {

        Service service = new Service(4);

        // Ajout des premiers employés
        try {
            Commercial karim =
                    new Commercial("Karim", 3000, 40000, "Rabat");

            ChefProjet salma =
                    new ChefProjet("Salma", 6000, "Casablanca");

            service.ajouter(karim);
            service.ajouter(salma);

        } catch (MontantInvalideException | EquipeCompletException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        String[] noms = {
                "Nadia", "Omar", "Yassine", "Leila", "Hamza"
        };

        String[] salaires = {
                "4500", "abc", "-800", "3800", "5200"
        };

        int tentatives = 0;

        for (int i = 0; i < noms.length; i++) {

            tentatives++;

            try {

                double salaire = Double.parseDouble(salaires[i]);

                Permanent p = new Permanent(noms[i], salaire);

                service.ajouter(p);

                System.out.println("Embauche de "
                        + noms[i] + " : " + salaire);

            } catch (NumberFormatException e) {

                System.out.println("Saisie ignorée : \""
                        + salaires[i] + "\" n'est pas un montant");

            } catch (MontantInvalideException e) {

                System.out.println("Erreur : " + e.getMessage());

            } catch (EquipeCompletException e) {

                System.out.println("Erreur : " + e.getMessage());

            } finally {
                // le compteur augmente toujours
            }
        }

        System.out.println("Tentatives : " + tentatives
                + ", employés dans le service : " + service.getNb());

        // Tri des employés
        service.trier();
        service.afficher();

        System.out.println("Masse salariale : "
                + service.getMasseSalariale());

        // Récupération du moins payé
        try {

            Employe e = service.getEmploye(0);

            Augmentable a = (Augmentable) e;

            a.augmenterStandard();

            System.out.println("Après augmentation : " + e);

            a.augmenter(0.5);

            System.out.println(
                    "Cette ligne ne s'affiche jamais");

        } catch (MontantInvalideException e) {

            System.out.println("Erreur : " + e.getMessage()
                    + " [valeur reçue : " + e.getValeur() + "]");
        }

        // Test d'un indice incorrect
        try {

            service.getEmploye(7);

        } catch (IllegalArgumentException e) {

            System.out.println("Erreur : " + e.getMessage());
        }

        // Paiements
        payable[] paiements = {
                new Facture("F-102", "Bureau Plus", 450),
                service.getEmploye(1)
        };

        double total = 0;

        for (payable p : paiements) {

            System.out.println("À payer : " + p);

            total += p.getMontantAPayer();
        }

        System.out.println("Total à payer : " + total);

        System.out.println("Nombre d'employés créés : "
                + Employe.getNbEmployes());
    }
}
