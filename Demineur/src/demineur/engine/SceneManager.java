
package demineur.engine;

import java.awt.Color;

public class SceneManager extends GameObject {
    
    /*
    Le SceneManager permet de gerer les différentes scènes du jeu, il
    admet aussi un système de transition avec actuellement deux types 
    possibles.
    "circle" : transition en cercle.
    "blur" : transition par pixelisation.
    */
    
    // HashMap des scènes (nomScène : scène) 
    java.util.HashMap<String, Scene> scenes; 
    
    // scène courante.
    public String current; 
    
    // Timers
    private float transitionTimer; 
    private float transitionTimerMax;
    private String transitionType;
    private String transitionTarget;
    private boolean transitionHappened;
    
    public SceneManager(Context context) {
        super(context); 
        scenes = new java.util.HashMap<String, Scene>();        
    }; 
    
    @Override 
    public void update(float dt) {
        scenes.get(current).update(dt); 
        handleTransition(dt);
    };
    
    public void addScene(String name, Scene scene) {
        /*
        Ajoute une scène au SceneManager.
        name (String):
            nom (label) de la scène à ajouter.
        scene (Scene):
            scène à ajouter.
        */
        scenes.put(name, scene); 
    };
    
    public void handleTransition(float dt) {
        /*
        Gère les transitions.
        */
        if (transitionTimer > 0) {
            transitionTimer -= (dt * 0.001);
            checkSwitch();
            displayTransition();
        } else {
            transitionTimer = 0;
            context.setPixelated(0.0f);
            
        };
    }
    public void checkSwitch() {
        /*
        Vérifie si un switch doit être effectué (au milieu d'une transition).
        */
        if (transitionTimer < transitionTimerMax / 2 && !transitionHappened) {
            transitionHappened = true; 
            switchScene(transitionTarget);
        };   
    }
    
    public void displayTransition() {
        /*
        Affiche la transition courante (cercle, pixelisation, ...).
        */
        if (transitionType.equals("blur")) {
            context.setPixelated((float)(getCurve("exponential", false) * 0.15 + 0.05));
        } else if (transitionType.equals("circle")) {
            context.drawCircle(context.DISPLAY_WIDTH / 2 - 5, context.DISLAY_HEIGHT / 2, (int)(context.DISPLAY_WIDTH * getCurve("exponential", false) * 1.5), Color.BLACK);
        } else if (transitionType.equals("slide")) {
            context.slide((int)(getCurve("exponential", true) * context.DISPLAY_WIDTH));
        }
    }; 
   
    
    public void transition(String scene, long transitionTime, String transitionType_) {
        /*
        fonction d'API permettant d'effectuer une transition entre deux scènes.
        scene (String):
            la scène vers laquelle transitionner.
        transitionTime (long):
            la durée de la transition.
        transitionType_ (String):   
            le type de la transition ("blur", "circle", ...).
        */
        transitionTimer = transitionTime; 
        transitionTimerMax = transitionTime;
        transitionType = transitionType_; 
        transitionTarget = scene;  
        transitionHappened = false;
    };
    
    public void switchScene(String scene) {
        /*
        Change de scène proprement (en appelant les hooks concernés).
        */
        scenes.get(current).exit();
        current = scene; 
        scenes.get(current).load();  
    };
    
    private float getCurve(String curveType, boolean invert) {
        /*
        Fonction d'interpolation assez confuse (pas eu le temps 
        de faire un meilleur système).
        */
        
        float progress = 1.0f - (transitionTimer / transitionTimerMax);
        float curve = 0;
        
        switch (curveType) {
            // courbe linéaire.
            case "linear":
                if (progress < 0.5f) {
                    curve = progress * 2.0f; 
                } else {
                    curve = (1.0f - progress) * 2.0f; 
                };
                break;
            // courbe exponentielle.
            case "exponential":
                if (progress < 0.5f) {
                    float t = progress * 2.0f;
                    curve = t * t;
                } else {
                    float t = (1.0f - progress) * 2.0f;
                    curve = t * t; 
                };
                break;
            // courbe exponentielle inversée.
            case "exponential-inv":
                if (progress < 0.5f) {
                       float t = progress * 2.0f;
                       curve = 1.0f - (1.0f - t) * (1.0f - t); 
                   } else {
                       float t = (1.0f - progress) * 2.0f;
                       curve = 1.0f - (1.0f - t) * (1.0f - t); 
                   };
                   break;
            // courbe sinusoidale.
            case "sinusoidal":
            default:
                curve = (float)Math.sin(progress * Math.PI);
                break;
        };
        if (invert && transitionTimer > transitionTimerMax / 2) 
            return - curve;
        else 
            return curve;
    };
};
