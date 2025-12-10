/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniprojet_cadenas_serghini_marwane;

import java.util.Random;
import java.util.Arrays;

/**
 * Cette classe gère la logique du jeu de type "Coffre-fort" ou "Mastermind numérique".
 * Elle remplace l'ancienne classe CadenasGame.
 */
public class cadenajuego {
   
    // Constantes renommées pour plus de clarté
    private static final int TAILLE_COMBINAISON = 4;
    private final int MAX_ESSAIS;
   
    // Variables d'état
    private int[] combinaisonSecrete;    // Remplaçant de CODE_SECRET
    private int[] saisieJoueur;          // Remplaçant de codeActuel
    private int essaisRestants;
    private int compteurTours;

    // ================== Constructeur ==================

    public cadenajuego () {
        this.MAX_ESSAIS = 5;
        this.saisieJoueur = new int[TAILLE_COMBINAISON];
        this.essaisRestants = MAX_ESSAIS;
        this.compteurTours = 0;
        this.combinaisonSecrete = initialiserCombinaison();
    }
   
    // ================== Logique Principale ==================

    /**
     * Génère la combinaison aléatoire à trouver.
     */
    private int[] initialiserCombinaison() {
        Random generateur = new Random();
        int[] code = new int[TAILLE_COMBINAISON];
        for (int i = 0; i < TAILLE_COMBINAISON; i++) {
            code[i] = generateur.nextInt(10); // Chiffre entre 0 et 9
        }
        return code;
    }
   
    /**
     * Compare la saisie actuelle avec le code secret.
     * Remplace la méthode 'testerPropositions'.
     */
    public ResultatAnalyse validerTentative() {
        // Si la partie est déjà finie (perdu ou gagné au tour d'avant), on renvoie 0 partout
        if (estJeuTermine()) {
            return new ResultatAnalyse(0, 0, 0);
        }
       
        int bienPlaces = 0;
        int plusGrands = 0;
        int plusPetits = 0;

        for (int i = 0; i < TAILLE_COMBINAISON; i++) {
            int valeurJoueur = saisieJoueur[i];
            int valeurSecrete = combinaisonSecrete[i];

            if (valeurJoueur == valeurSecrete) {
                bienPlaces++;
            } else if (valeurJoueur > valeurSecrete) {
                plusGrands++;
            } else {
                plusPetits++;
            }
        }
       
        // Mise à jour des compteurs
        essaisRestants--;
        compteurTours++;

        return new ResultatAnalyse(bienPlaces, plusGrands, plusPetits);
    }
   
    /**
     * Modifie un chiffre de la combinaison du joueur.
     * Remplace 'changerChiffre'.
     * @param index L'index du chiffre à modifier (0 à 3)
     * @param incrementer True pour +1, False pour -1
     */
    public void ajusterRoulette(int index, boolean incrementer) {
        if (index < 0 || index >= TAILLE_COMBINAISON) {
            return;
        }
       
        int valeurActuelle = saisieJoueur[index];
        
        // Logique mathématique condensée :
        // Si incrementer est vrai, on ajoute 1, sinon on ajoute -1.
        // On ajoute 10 avant le modulo pour gérer proprement le passage de 0 à 9 en reculant.
        int delta = incrementer ? 1 : -1;
        saisieJoueur[index] = (valeurActuelle + delta + 10) % 10;
    }
   
    // ================== Accesseurs et État ==================

    public int[] getSaisieActuelle() {
        return saisieJoueur;
    }
   
    public int[] getSolution() {
        return combinaisonSecrete;
    }
   
    public int getNbToursJoues() {
        return compteurTours;
    }

    public String getScoreFormatte() {
        return compteurTours + " / " + MAX_ESSAIS;
    }

    public boolean estJeuTermine() {
        return essaisRestants <= 0;
    }
   
    /**
     * Remet le jeu à zéro avec une nouvelle combinaison.
     */
    public void resetPartie() {
        this.saisieJoueur = new int[TAILLE_COMBINAISON];
        this.essaisRestants = MAX_ESSAIS;
        this.compteurTours = 0;
        this.combinaisonSecrete = initialiserCombinaison();
    }
}

// ================== Classe utilitaire (Remplace Propositions) ==================

/**
 * Cette classe sert à transporter le résultat d'une tentative.
 * Vous devrez utiliser "ResultatAnalyse" à la place de "Propositions" dans votre interface.
 */
class ResultatAnalyse {
    private final int exacts;
    private final int superieurs;
    private final int inferieurs;

    public ResultatAnalyse(int exacts, int superieurs, int inferieurs) {
        this.exacts = exacts;
        this.superieurs = superieurs;
        this.inferieurs = inferieurs;
    }

    public int getExacts() { return exacts; }
    public int getSuperieurs() { return superieurs; }
    public int getInferieurs() { return inferieurs; }
}
