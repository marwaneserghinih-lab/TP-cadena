package demineur.scenes;

import demineur.engine.*;
import demineur.game.*;

public class Menu extends Scene {
    /*
    Scène du menu principal.
    */
    
    // Timer d'animation du logo.
    float animLogoTimer = 100;
    
    public Menu(Context context) {
        super(context);
    };
    
    @Override
    public void exit() {};
    
    @Override 
    public void load() {
        System.out.println("HERE BRO");
        objects.clear();
        addObject(new Button(context, 43, 80, 0));
        addObject(new Button(context, 43, 130, 1));
        addObject(new Button(context, 43, 180, 2));   
    };
    
    public void update(float dt) {  
        handleTimers(dt);
        context.drawImage("title", (int)(Math.cos(animLogoTimer * 10) * 7) - 2, -5);
        updateObjects(dt);
    };
    
    private void handleTimers(float dt) {
        /*
        Gère les timers (en l'occurence un).
        */
        animLogoTimer += (dt * 0.0001);
    };
};