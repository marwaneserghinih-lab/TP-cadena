// 1. Le package doit être la TOUTE première ligne
package tp0_.addition; 

// 2. Les imports viennent APRES le package
import java.util.Scanner;

// 3. UNE SEULE classe, qui porte le nom de votre fichier (attention aux majuscules)
public class TPO_Addition { 

    // 4. Une seule méthode main
    public static void main(String[] args) {
        
        // Déclaration des variables
        int nb; // nombre d'entiers à additionner
        int result; // resultat
        int ind; // indice
        
        // Création de l'objet Scanner pour lire le clavier
        Scanner sc = new Scanner(System.in); 
        
        // --- initialisation ---
        
        // On demande le nombre à l'utilisateur
        System.out.println("Entrer le nombre :"); 
        nb = sc.nextInt(); // On lit et stocke le nombre
        
        // On initialise le résultat et l'indice de départ
        result = 0;
        ind = 1;
        
        // --- addition des nb premiers entiers ---
        
        while (ind <= nb) { 
            result = result + ind;
            ind++; // On passe à l'entier suivant
        }
        
        // --- affichage du resultat ---
        
        System.out.println("la somme des " + nb + " entiers est: " + result);
    }
}