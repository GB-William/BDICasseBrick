package jeu.model;

import jeu.CasseBrique;

import java.awt.*;

public class Balle extends Cercle {

    private int vitesseHorizontale;
    private int vitesseVerticale;

    public Balle() {
        this.x = (int)(Math.random() * CasseBrique.LARGEUR);
        this.y = (int)(Math.random() * CasseBrique.HAUTEUR);
        this.vitesseHorizontale = (int)(Math.random() * 9) + 1;
        this.vitesseVerticale = (int)(Math.random() * 9) + 1;
        this.couleur = new Color(
                (float)Math.random(),
                (float)Math.random(),
                (float)Math.random());
    }

    public Balle(int x, int y, int vitesseHorizontale, int vitesseVerticale) {
        this.x = x;
        this.y = y;
        this.vitesseHorizontale = vitesseHorizontale;
        this.vitesseVerticale = vitesseVerticale;
        this.couleur = Color.BLUE;
    }

    public void deplacement() {
        x += vitesseHorizontale;
        y += vitesseVerticale;

        if(x < 0 || x > CasseBrique.LARGEUR - diametre) {
            vitesseHorizontale = -vitesseHorizontale;
        }

        if(y < 0 || y > CasseBrique.HAUTEUR - diametre) {
            vitesseVerticale = -vitesseVerticale;
        }
    }

    public void inverserVitesseVerticale() {

        vitesseVerticale = -vitesseVerticale;

    }


    public int getVitesseHorizontale() {
        return vitesseHorizontale;
    }

    public void setVitesseHorizontale(int vitesseHorizontale) {
        this.vitesseHorizontale = vitesseHorizontale;
    }

    public int getVitesseVerticale() {
        return vitesseVerticale;
    }

    public void setVitesseVerticale(int vitesseVerticale) {
        this.vitesseVerticale = vitesseVerticale;
    }


}
