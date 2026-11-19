package jeu.model;

import java.awt.*;

public class Bonus extends Cercle {

    private int vitesseChute = 3;

    public Bonus(int x, int y) {
        this.x = x;
        this.y = y;
        this.diametre = 15;
        this.couleur = Color.MAGENTA;
    }

    public void deplacement() {
        y += vitesseChute;
    }
}
