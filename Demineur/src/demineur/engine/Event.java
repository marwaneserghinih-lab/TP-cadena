package demineur.engine;

public class Event {
    
    /*
    La Classe Event permet de stocker le contenu d'un evenement,
    c'est a dire a forciori son type (keydown, keyup, transition, ...) 
    ainsi que des informations complémentaires.
    */
    
    public String type; // type d'évenement.
    public int[] intData; // informations complémentaires de type entier.
    public String[] strData; // informations complémentaires de type texte.
    
    public Event(String type_) {
        type = type_; 
        intData = null;
        strData = null; 
    }; 
   
    public Event(String type_, int[] intData_) {
        type = type_;
        intData = intData_; 
        strData = null; 
    };
    
    public Event(String type_, String[] strData_) {
        type = type_;
        strData = strData_;
        intData = null; 
    };
    
    public Event(String type_, int[] intData_, String[] strData_) {
        type = type_;
        strData = strData_;
        intData = intData_; 
    };
}
