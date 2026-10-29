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

    //TODO : La brique a une resistance (3 par defaut)
    //si elle est en collision a vec la balle, elle perd 1 en resistance,
    // et change de couleur (orange, puis jaune)
    // a 0 elle est detruite

    /**
     * Reduit la resistance de 1
     * @return true si la resistance tombe a 0
     */
    public boolean degat() {
        return false;
    }


}
