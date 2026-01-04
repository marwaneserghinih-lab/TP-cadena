package demineur.scenes;
import demineur.engine.*;
import java.awt.event.*;
import java.awt.Color;

public class Main extends Scene {
    /*
    Représente la toute première scene, celle juste avant
    le menu.
    */
    
    // Caractéristiques de l'animation
    private static final float WAVE_SPEED = 0.7f;
    private static final float WAVE_HEIGHT = 5.0f;
    private static final float TITLE_RISE_SPEED = 10.0f;
    private static final int TITLE_BASE_Y = 0;
    private static final int ENTER_X_OFFSET = -5; 
    private static final float ANIMATION_DURATION = 2.0f;

    // Timers
    private float titleTime = 0;
    private float animationTime = ANIMATION_DURATION;
    
    private boolean isAnimating = true;
    
    public Main(Context context) {
        super(context);
    }
    
    @Override
    public void update(float dt) {
        context.drawRect(
            new Rect(0, 0, context.DISPLAY_WIDTH, context.DISLAY_HEIGHT), 
            new Color(0, 0, 0)
        );
        handleTimers(dt);
        
        // Recuperation des coordonnées des éléments de l'animation
        int titleY = calculateTitleY();
        int enterX = calculateEnterX();
        
        // Affichage de ceux-ci
        context.drawImage("marwane_std", 0, titleY + (enterX / 2));
        context.drawImage("enter", enterX, 85);
        
        transitionCheck();
        
    }
    
    private void handleTimers(float dt) {
        /*
        gère les timers.
        */
        float dtSeconds = dt * 0.001f;
        
        titleTime += dtSeconds;
        
        if (isAnimating && animationTime > 0) {
            animationTime -= dtSeconds;
            if (animationTime < 0) {
                animationTime = 0;
                isAnimating = false;
            }
        }
    }
    private void transitionCheck() {
        /*
        Vérifie si la transition vers le menu doit s'effectuer.
        */
        for (Event e : context.events) {
            if (e.type.equals("keydown")) {
                if (e.intData[0] == KeyEvent.VK_ENTER) {
                    context.sceneManager.transition("menu", (long)2.0, "blur");
                }
            }
        }
    }; 
    
    /*
    Cette scene étant brève et non accessible après avoir été quittée, elle 
    ne nécéssite pas d'exit() ni de load(); 
    */
    
    @Override
    public void exit() {
        
    }
    
    @Override 
    public void load() {
        
    }
    
    private int calculateTitleY() {
        /*
        Retourne la coordonée Y du titre.
        */
        float wave = (float)Math.sin(titleTime * WAVE_SPEED * Math.PI * 2) * WAVE_HEIGHT;
       
        float riseOffset = 0;
        if (isAnimating) {
            float progress = 1.0f - (animationTime / ANIMATION_DURATION);
            float eased = 1.0f - (float)Math.pow(1.0f - progress, 3);
            riseOffset = eased * TITLE_RISE_SPEED;
        } else {
            riseOffset = TITLE_RISE_SPEED;
        }
        
        return TITLE_BASE_Y - (int)riseOffset + (int)wave;
    }
    
    private int calculateEnterX() {
        /*
        Récupere la coordonée X du sous-titre.
        */
        int finalX = ENTER_X_OFFSET;
        
        if (!isAnimating && animationTime == 0) {
            return finalX;
        }
         
        float progress = 1.0f - (animationTime / ANIMATION_DURATION);
        float eased = 1.0f - (float)Math.pow(1.0f - progress, 3);
       
        int totalDistance = context.DISPLAY_WIDTH + ENTER_X_OFFSET;
        
        return - context.DISPLAY_WIDTH + (int)(eased * totalDistance);
    }
}