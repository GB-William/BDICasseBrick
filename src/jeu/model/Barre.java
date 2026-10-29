package jeu.model;

import jeu.CasseBrique;

import java.awt.*;

public class Barre extends Rectangle {

    public Barre() {
        couleur = Color.GREEN;
        //on centre la barre
        x = CasseBrique.LARGEUR / 2 - largeur / 2;
        //on place la barre a 100px du sol
        y = CasseBrique.HAUTEUR - 100;
    }

    public void deplacementGauche() {
        if(x > 0) {
            x -= 5;
        } else {
            x = 0;
        }

    }

    public void deplacementDroite() {

        if(x < CasseBrique.LARGEUR - largeur) {
            x += 5;
        } else {
            x = CasseBrique.LARGEUR - largeur;
        }
    }
}
