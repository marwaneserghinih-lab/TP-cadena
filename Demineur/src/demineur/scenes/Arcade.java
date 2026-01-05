package demineur.scenes;

import demineur.engine.*;
import demineur.game.*;

public class Arcade extends Scene {
    /*
    Scène du mode de jeu "arcade".
    */
    public boolean transitionDone = false;
    public Arcade(Context context) {
        super(context); 
    }; 
    
    @Override 
    public void load() {
        objects.clear();
        transitionDone = false;
        context.arcadeScore = 0;
        context.arcadeTime = 15;
        context.nbBombs = 3;
        addObject(new Grid(context, context.nbBombs, 5, 5, 30, 30, "arcade"));
    }; 
    
    @Override 
    public void exit() {}; 
    
    @Override 
    public void update(float dt) {
        context.drawImage("arcade_hud", 0, 0);
        updateObjects(dt);
        context.drawNumber(context.arcadeScore, 100, 5);
        context.drawNumber((int)context.arcadeTime, 98, 24);
        if (context.arcadeTime > 0) {
            context.arcadeTime -= dt * 0.001;
        } else {
            context.arcadeTime = 0;
            if (!transitionDone) {
                context.sceneManager.transition("perdu", (long)1.5, "blur");
                transitionDone = true;
            };
        };
    };
}
