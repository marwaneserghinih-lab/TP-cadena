/*Tp1 exo1 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp1_manipnombresint_marwane;

// Importation de la classe Scanner pour pouvoir lire l'entrée de l'utilisateur
// On importe la classe Scanner
import java.util.Scanner;

// C'est notre classe principale
public class tp1_manipnombresint_marwane {

    // C'est notre fonction principale (le "point d'entrée")
    public static void main(String[] args) {

        // 1. On crée le scanner (qu'on appelle 'sc')
        Scanner sc = new Scanner(System.in);

        // 2. On déclare les variables
        int premierNombre;
        int deuxiemeNombre;

        // 3. On demande et on lit les deux nombres
        System.out.print("Veuillez saisir le premier entier : ");
        premierNombre = sc.nextInt();

        System.out.print("Veuillez saisir le deuxième entier : ");
        deuxiemeNombre = sc.nextInt();

        // 4. On affiche ce que l'utilisateur a saisi
        System.out.println("\nVous avez saisi " + premierNombre + " et " + deuxiemeNombre + ".");

        // 5. On affiche les calculs (AVEC LES PARENTHÈSES)
        System.out.println("\n--- Calculs ---");
        System.out.println("La somme : " + (premierNombre + deuxiemeNombre));
        System.out.println("La différence : " + (premierNombre - deuxiemeNombre));
        System.out.println("Le produit : " + (premierNombre * deuxiemeNombre));

        // 6. On affiche la division (AVEC LES PARENTHÈSES)
        System.out.println("\n--- Division ---");
        System.out.println("Le quotient entier : " + (premierNombre / deuxiemeNombre));
        System.out.println("Le reste : " + (premierNombre % deuxiemeNombre));

        // 7. On ferme le scanner
        sc.close();
    }
}