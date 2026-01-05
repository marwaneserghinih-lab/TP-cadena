package demineur.scenes;

import demineur.engine.*;
import demineur.game.*;

public class Perdu extends Scene {
    /*
    Scène du menu "perdu".
    */
    
    public Perdu(Context context) {
        super(context);
    }; 
    
    @Override
    public void update(float dt) {
        context.drawImage("perdu", 0, 0);
        updateObjects(dt);
    };
    
    @Override
    public void load() {
        // reset le screen shake
        context.screenShake(0); 
        objects.clear();
        addObject(new Button(context, 43, 105, 3));
        addObject(new Button(context, 43, 160, 4));
    }
    
    @Override
    public void exit() {};
};
