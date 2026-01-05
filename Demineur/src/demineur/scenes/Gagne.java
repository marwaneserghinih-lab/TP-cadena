package demineur.scenes;

import demineur.engine.*;
import demineur.game.*;

public class Gagne extends Scene {
    /*
    Scène du menu "gagné".
    */
    
    public Gagne(Context context) {
        super(context);
    }; 
    
    @Override
    public void update(float dt) {
        context.drawImage("gagne", 0, 0);
        updateObjects(dt);
    };
    
    @Override
    public void load() {
        // reset le screen shake
        context.screenShake(0); 
        objects.clear();
        addObject(new Button(context, 43, 105, 3));
        addObject(new Button(context, 43, 160, 4));
    };
    
    @Override
    public void exit() {};
}
