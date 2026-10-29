package jeu.model;

import java.awt.*;

public class Rectangle extends Sprite {

    protected int largeur = 200;
    protected int hauteur = 40;

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect( x, y, largeur, hauteur);
    }

    public boolean collision(Balle balle) {

        return balle.getX() > x
                && balle.getX() < x + largeur
                && balle.getY() > y
                && balle.getY() < y + hauteur;
    }

}
