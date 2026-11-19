package jeu.model;

import java.awt.*;

public class Brique extends Rectangle {

    public Brique(int x, int y) {

        this.x = x;
        this.y = y;

        couleur = Color.RED;
        largeur = 50;
        hauteur = 20;
    }

    private int resistance = 3;

    /**
     * Reduit la resistance de 1
     * @return true si la resistance tombe a 0
     */
    public boolean degat() {
        resistance--;

        if(resistance == 2) {
            couleur = Color.ORANGE;
        } else if(resistance == 1) {
            couleur = Color.YELLOW;
        }

        return resistance == 0;
    }

    public int getResistance() {
        return resistance;
    }

}
