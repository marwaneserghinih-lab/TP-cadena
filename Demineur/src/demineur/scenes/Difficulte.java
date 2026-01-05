package demineur.scenes;

import demineur.game.*;
import demineur.engine.*; 

public class Difficulte extends Scene {
    /*
    Scène du choix des difficultées.
    */
    public Difficulte(Context context) {
        super(context); 
    }; 
    
    public void load() {
        objects.clear();
        addObject(new Button(context, 43, 50, 5));
        addObject(new Button(context, 43, 105, 6));
        addObject(new Button(context, 43, 160, 7));
    };
    
    public void update(float dt) {
        updateObjects(dt);
    }; 
    
    public void exit() {};
}
