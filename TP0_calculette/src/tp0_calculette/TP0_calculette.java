/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
// 1. Déclaration du package (doit être la première ligne)
/*
 * (Votre cartouche d'identification va ici)
 */
package tp0_calculette; // <-- Le nom du package correspond au projet

import java.util.Scanner;

// Le nom de la classe correspond au nom du fichier
public class TP0_calculette {

    public static void main(String[] args) {

        // --- Déclaration des variables ---
        int operateur;
        int operande1;
        int operande2;

        // --- Création de l'objet Scanner ---
        Scanner sc = new Scanner(System.in);

        // --- 2. Affichage du menu ---
        System.out.println("Please enter the operator:");
        System.out.println("1) add");
        System.out.println("2) substract");
        System.out.println("3) multiply");
        System.out.println("4) divide");
        System.out.println("5) modulo");

        // --- 3. Récupérer le choix de l'opérateur ---
        operateur = sc.nextInt();

        // --- 7. Test de l'opérateur (AVANT de demander les opérandes) ---
        if (operateur < 1 || operateur > 5) {
            
            // Si l'opérateur est invalide
            System.out.println("Erreur : Opérateur non valide.");
            
        } else {
            
            // Si l'opérateur EST valide, on continue...
            
            // --- 4. Demander et récupérer operande1 ---
            System.out.println("Please enter the first number:");
            operande1 = sc.nextInt();

            // --- 5. Demander et récupérer operande2 ---
            System.out.println("Please enter the second number:");
            operande2 = sc.nextInt();

            // --- 6 & 8. Calculer, gérer les erreurs et afficher ---
            switch (operateur) {
                case 1: // add
                    System.out.println("The result is : " + (operande1 + operande2));
                    break;

                case 2: // substract
                    System.out.println("The result is : " + (operande1 - operande2));
                    break;

                case 3: // multiply
                    System.out.println("The result is : " + (operande1 * operande2));
                    break;

                case 4: // divide
                    if (operande2 == 0) { // Test division par zéro
                        System.out.println("Erreur : Division par zéro impossible.");
                    } else {
                        System.out.println("The result is : " + (operande1 / operande2));
                    }
                    break;

                case 5: // modulo
                    if (operande2 == 0) { // Test modulo par zéro
                        System.out.println("Erreur : Modulo par zéro impossible.");
                    } else {
                        System.out.println("The result is : " + (operande1 % operande2));
                    }
                    break;
            }
        }
        
        // Bonne pratique : fermer le scanner à la fin
        sc.close();
    }
}