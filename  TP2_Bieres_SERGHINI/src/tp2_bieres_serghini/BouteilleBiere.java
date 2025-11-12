/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2_bieres_serghini;

/**
 *
 * @author dell
 */


/**
 * @author dell
 */
// J'ai supprimé la ligne "package..." pour éviter l'erreur de package
// Si vous en avez besoin, remettez-la.

/**
 * @author dell
 */
// J'ai supprimé la ligne "package..." pour éviter l'erreur de package
// Si vous en avez besoin, remettez-la.

/**
 * @author dell
 */
// J'ai supprimé la ligne "package..." pour éviter l'erreur de package
// Si vous en avez besoin, remettez-la.

/**
 * @author dell
 */
public class BouteilleBiere {

    // 1. Attributs
    private String Nom;
    private double degreAlcool;
    private String brasserie;
    private boolean ouverte; // Cet attribut sera utilisé par Décapsuler()

    /*
     * 2. Le Constructeur
     */
    public BouteilleBiere(String unNom, double unDegre, String uneBrasserie) {
        Nom = unNom;
        degreAlcool = unDegre;
        brasserie = uneBrasserie;
        ouverte = false; // Par défaut, une nouvelle bouteille est fermée
    }

    // 3. Méthode pour lire l'étiquette (redondante avec toString)
    public void lireEtiquette() {
        System.out.println("Bouteille de " + Nom + " (" + degreAlcool +
                           " degres) \nBrasserie : " + brasserie);
    }

    /*
     * 4. NOUVELLE MÉTHODE (Question 12)
     * Ouvre la bière si elle est fermée.
     */
    public boolean Décapsuler() {
        if (ouverte == false) { // On peut aussi écrire if (!ouverte)
            // La bière n'était pas ouverte, on l'ouvre
            ouverte = true;
            return true;
        } else {
            // La bière était déjà ouverte
            System.out.println("erreur : bière déjà ouverte");
            return false;
        }
    }

    /*
     * 5. NOUVELLE MÉTHODE (Question 13)
     * Redéfinit la façon dont l'objet est affiché par System.out.println()
     */
    @Override
    public String toString() {
        String chaine_a_retourner;
        
        // Ce code implémente ce qui est demandé dans l'image,
        // en corrigeant les fautes de frappe (nom -> Nom et la ligne "Ouverte ? ;")
        chaine_a_retourner = Nom + " (" + degreAlcool + " degrés) Ouverte ? ";

        if (ouverte == true) { // On peut aussi écrire if (ouverte)
            chaine_a_retourner += "oui";
        } else {
            chaine_a_retourner += "non";
        }
        return chaine_a_retourner;
    }


    /*
     * 6. Point d'entrée (main)
     * Avec les nouveaux tests pour les questions 12 et 13.
     */
    public static void main(String[] args) {
        
        BouteilleBiere uneBiere = new BouteilleBiere("Cuvée des trolls", 7.0, "Dubuisson");
        BouteilleBiere secondeBiere = new BouteilleBiere("Leffe", 6.6, "Abbaye de Leffe");
        
        // --- NOUVEAUX TESTS (Questions 12 & 13) ---
        
        System.out.println("\n--- Test de Décapsuler (Q12) ---");
        
        // On affiche l'état avant (grâce à toString)
        System.out.println("Avant : " + uneBiere); 
        
        // On tente d'ouvrir
        System.out.println("On décapsule...");
        uneBiere.Décapsuler(); // Doit réussir
        
        // On vérifie l'état après (la valeur 'ouverte' a changé)
        System.out.println("Après : " + uneBiere); 
        
        // On tente de rouvrir
        System.out.println("On re-décapsule...");
        uneBiere.Décapsuler(); // Doit afficher l'erreur
        

        System.out.println("\n--- Test de toString (Q13) ---");
        // 'uneBiere' est ouverte, doit afficher "oui"
        System.out.println(uneBiere);
        
        // 'secondeBiere' est fermée, doit afficher "non"
        System.out.println(secondeBiere);
    }
}