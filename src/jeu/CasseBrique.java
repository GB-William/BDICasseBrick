package jeu;

import jeu.model.Balle;
import jeu.model.Barre;
import jeu.model.Bonus;
import jeu.model.Brique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CasseBrique extends Canvas {


    private ArrayList<Balle> listeBalle = new ArrayList<>();
    private ArrayList<Brique> listeBrique = new ArrayList<>();
    private ArrayList<Bonus> listeBonus = new ArrayList<>();
    private Barre barre = new Barre();

    public static final int LARGEUR = 600;
    public static final int HAUTEUR = 800;

    private boolean toucheGauche = false;
    private boolean toucheDroite = false;

    public CasseBrique() throws InterruptedException {

        JFrame fenetre = new JFrame("Mon super casse brique");

        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR);

        JPanel panneau = new JPanel();
        panneau.add(this);

        fenetre.setContentPane(panneau);

        fenetre.requestFocus();
        this.setFocusable(false);

        KeyListener evenement = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    toucheGauche = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    toucheDroite = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    toucheGauche = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    toucheDroite = false;
                }
            }
        };

        fenetre.addKeyListener(evenement);

        fenetre.setIgnoreRepaint(true);
        fenetre.setResizable(false);

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0,0, LARGEUR, HAUTEUR);

        fenetre.setVisible(true);

        this.createBufferStrategy(2);

        demarrer();
    }

    public void demarrer() throws InterruptedException {


        listeBalle.add(new Balle());

        //créer un "rectangle" de 8 briques sur 6, avec 4 pixel d'espace

        for (int indexHorizontal = 0; indexHorizontal < 8 ; indexHorizontal++) {
            for (int indexVertical = 0; indexVertical < 6 ; indexVertical++) {
                listeBrique.add(new Brique(80 + indexHorizontal * 54, 40 + indexVertical * 24));
            }
        }


        while(true) {

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();
            // tout le code du jeu

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);

//            for(int i = 0; i < tableauBalle.length; i ++) {
//                tableauBalle[i].deplacement();
//                tableauBalle[i].dessiner(dessin);
//            }

            if(toucheGauche) {
                barre.deplacementGauche();
            } else if(toucheDroite) {
                barre.deplacementDroite();
            }

            barre.dessiner(dessin);

            ArrayList<Brique> listeBriqueAsupprimer = new ArrayList<>();

            for(Brique brique : listeBrique) {
                for(Balle balle : listeBalle) {
                    if(brique.collision(balle)) {
                        balle.inverserVitesseVerticale();

                        if(brique.degat()) {
                            listeBriqueAsupprimer.add(brique);

                            // 50% de chance de faire apparaitre un bonus
                            if(Math.random() < 0.5) {
                                listeBonus.add(new Bonus(brique.getX(), brique.getY()));
                            }
                        }
                    }
                }
                brique.dessiner(dessin);
            }

            //on suuprime toutes les brique ou une balle est entré en collision
            // dans la boucle precedente (on ne peut pas supprimer la brique de la liste parcourue)
            for(Brique brique : listeBriqueAsupprimer) {
                listeBrique.remove(brique);
            }

            for(Balle balle : listeBalle) {
                balle.deplacement();
                if(barre.collision(balle)) {
                    balle.inverserVitesseVerticale();
                }
                balle.dessiner(dessin);
            }

            ArrayList<Bonus> listeBonusAsupprimer = new ArrayList<>();

            for(Bonus bonus : listeBonus) {
                bonus.deplacement();

                if(barre.collision(bonus)) {
                    listeBonusAsupprimer.add(bonus);
                    int vitesseHorizontale = (int)(Math.random() * 9) - 4;
                    listeBalle.add(new Balle(bonus.getX(), barre.getY() - 21, vitesseHorizontale, -5));
                } else if(bonus.getY() > HAUTEUR) {
                    listeBonusAsupprimer.add(bonus);
                }

                bonus.dessiner(dessin);
            }

            for(Bonus bonus : listeBonusAsupprimer) {
                listeBonus.remove(bonus);
            }

            dessin.dispose();
            getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }

    }

    public static void main() throws InterruptedException {

        new CasseBrique();

    }
}
