
package demineur.engine;

import java.awt.Color;

public class SceneManager extends GameObject {
    
    java.util.HashMap<String, Scene> scenes; 
    public String current; 
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
        scenes.put(name, scene); 
    };
    
    public void handleTransition(float dt) {
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
        if (transitionTimer < transitionTimerMax / 2 && !transitionHappened) {
            transitionHappened = true; 
            switchScene(transitionTarget);
        };   
    }
    
    public void displayTransition() {
        if (transitionType.equals("blur")) {
            context.setPixelated((float)(getCurve("exponential") * 0.15 + 0.05));
        } else if (transitionType.equals("circle")) {
            context.drawCircle(context.DISPLAY_WIDTH / 2 - 5, context.DISLAY_HEIGHT / 2, (int)(context.DISPLAY_WIDTH * getCurve("exponential") * 1.5), Color.BLACK);
        }
    }; 
   
    
    public void transition(String scene, long transitionTime, String transitionType_) {
        transitionTimer = transitionTime; 
        transitionTimerMax = transitionTime;
        transitionType = transitionType_; 
        transitionTarget = scene;  
        transitionHappened = false;
    };
    
    public void switchScene(String scene) {
        scenes.get(current).exit();
        current = scene; 
        scenes.get(current).load();  
    }
    
    private float getCurve(String curveType) {
        // Progression de 0 à 1 sur toute la durée
        float progress = 1.0f - (transitionTimer / transitionTimerMax);
        
        float curve = 0;
        
        switch (curveType) {
            case "linear":
                // Courbe triangulaire : 0 -> 1 -> 0
                if (progress < 0.5f) {
                    curve = progress * 2.0f; // Montée linéaire
                } else {
                    curve = (1.0f - progress) * 2.0f; // Descente linéaire
                }
                break;
                
            case "exponential":
                // Courbe exponentielle : accélération au milieu
                if (progress < 0.5f) {
                    float t = progress * 2.0f;
                    curve = t * t; // x² pour montée rapide
                } else {
                    float t = (1.0f - progress) * 2.0f;
                    curve = t * t; // x² pour descente rapide
                }
                break;
             
            case "exponential-inv":
                if (progress < 0.5f) {
                       float t = progress * 2.0f;
                       curve = 1.0f - (1.0f - t) * (1.0f - t); // 1 - (1-x)² pour démarrage rapide
                   } else {
                       float t = (1.0f - progress) * 2.0f;
                       curve = 1.0f - (1.0f - t) * (1.0f - t); // 1 - (1-x)² pour fin rapide
                   }
                   break;
                
            case "sinusoidal":
            default:
                // Courbe sinusoïdale smooth : 0 -> 1 -> 0
                // sin(0) = 0, sin(PI/2) = 1, sin(PI) = 0
                curve = (float)Math.sin(progress * Math.PI);
                break;
        }
        
        return curve;
    }
};
