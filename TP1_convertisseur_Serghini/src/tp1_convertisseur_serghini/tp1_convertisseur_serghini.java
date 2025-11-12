/*TP1 exo2 convertir la température 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp1_convertisseur_serghini;

/**
 *
 * @author dell
     */
        

import java.util.Scanner;

/**
 * Ce programme convertit une température donnée dans différentes unités
 * en utilisant un menu de sélection.
 */
public class tp1_convertisseur_serghini  {

    // --- PARTIE 4 : TOUTES LES MÉTHODES DE CONVERSION ---

    /**
     * Partie 3 : Convertit les degrés Celcius en Kelvin.
     * Une méthode doit faire une seule chose : calculer.
     * Elle ne doit PAS afficher de texte.
     */
    public static double CelciusVersKelvin(double tCelcius) {
        // Formule : K = C + 273.15
        return tCelcius + 273.15;
    }

    /**
     * Convertit les degrés Kelvin en Celcius.
     */
    public static double KelvinVersCelcius(double tKelvin) {
        // Formule : C = K - 273.15
        return tKelvin - 273.15;
    }

    /**
     * Convertit les degrés Celcius en Farenheit.
     */
    public static double CelciusVersFarenheit(double tCelcius) {
        // Formule : F = (C * 9/5) + 32
        // On utilise 9.0 et 5.0 pour forcer une division de 'double'
        return (tCelcius * 9.0 / 5.0) + 32;
    }

    /**
     * Convertit les degrés Farenheit en Celcius.
     */
    public static double FarenheitVersCelcius(double tFarenheit) {
        // Formule : C = (F - 32) * 5/9
        return (tFarenheit - 32) * 5.0 / 9.0;
    }

    /**
     * Convertit les Kelvin en Farenheit en réutilisant les autres méthodes
     * (K -> C puis C -> F).
     */
    public static double KelvinVersFarenheit(double tKelvin) {
        // 1. K -> C
        double tempEnCelcius = KelvinVersCelcius(tKelvin);
        // 2. C -> F
        double resultatFinal = CelciusVersFarenheit(tempEnCelcius);
        return resultatFinal;
        
        // Ou en une seule ligne (plus "malin") :
        // return CelciusVersFarenheit(KelvinVersCelcius(tKelvin));
    }

    /**
     * Convertit les Farenheit en Kelvin en réutilisant les autres méthodes
     * (F -> C puis C -> K).
     */
    public static double FarenheitVersKelvin(double tFarenheit) {
        // 1. F -> C
        double tempEnCelcius = FarenheitVersCelcius(tFarenheit);
        // 2. C -> K
        double resultatFinal = CelciusVersKelvin(tempEnCelcius);
        return resultatFinal;
        
        // Ou en une seule ligne :
        // return CelciusVersKelvin(FarenheitVersCelcius(tFarenheit));
    }


    // --- PARTIE 5 : LE MENU PRINCIPAL ---

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        // 1. Demander la valeur de température
        System.out.print("Bonjour, saisissez une valeur de température : ");
        double valeurSaisie = sc.nextDouble();

        // 2. Afficher le menu des choix
        System.out.println("\nSaisissez la conversion que vous souhaitez effectuer : ");
        System.out.println("1) De Celcius vers Kelvin");
        System.out.println("2) De Kelvin vers Celcius");
        System.out.println("3) De Celcius vers Farenheit");
        System.out.println("4) De Farenheit vers Celcius");
        System.out.println("5) De Kelvin vers Farenheit");
        System.out.println("6) De Farenheit vers Kelvin");
        System.out.print("Votre choix (1-6) : ");
        
        int choix = sc.nextInt();

        double resultat = 0;
        String uniteInitiale = "";
        String uniteFinale = "";

        // 3. Appeler la bonne méthode en fonction du choix
        switch (choix) {
            case 1:
                resultat = CelciusVersKelvin(valeurSaisie);
                uniteInitiale = "Celcius";
                uniteFinale = "Kelvin";
                break;
            case 2:
                resultat = KelvinVersCelcius(valeurSaisie);
                uniteInitiale = "Kelvin";
                uniteFinale = "Celcius";
                break;
            case 3:
                resultat = CelciusVersFarenheit(valeurSaisie);
                uniteInitiale = "Celcius";
                uniteFinale = "Farenheit";
                break;
            case 4:
                resultat = FarenheitVersCelcius(valeurSaisie);
                uniteInitiale = "Farenheit";
                uniteFinale = "Celcius";
                break;
            case 5:
                resultat = KelvinVersFarenheit(valeurSaisie);
                uniteInitiale = "Kelvin";
                uniteFinale = "Farenheit";
                break;
            case 6:
                resultat = FarenheitVersKelvin(valeurSaisie);
                uniteInitiale = "Farenheit";
                uniteFinale = "Kelvin";
                break;
            default:
                System.out.println("Erreur : Choix invalide.");
                sc.close(); // On ferme le scanner avant de quitter
                return; // On arrête le programme
        }

        // 4. Afficher le résultat final
        System.out.println("\n--- Résultat de la conversion ---");
        System.out.println(valeurSaisie + " degré(s) " + uniteInitiale + " est égal à " + resultat + " degré(s) " + uniteFinale);

        // 5. Fermer le scanner
        sc.close();
    }
}