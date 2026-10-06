package com.entreprise.rh;

import com.entreprise.exception.MontantInvalideException;

public class ChefProjet extends Permanent {

    public ChefProjet(String nom, double salaire, String agence)
            throws MontantInvalideException {

        super(nom, salaire, agence);
    }

    public double getPrime() {
        return 500;
    }

    @Override
    public String getPoste() {
        return "ChefProjet";
    }

    @Override
    public double getSalaire() {
        return super.getSalaire() + getPrime();
    }
}