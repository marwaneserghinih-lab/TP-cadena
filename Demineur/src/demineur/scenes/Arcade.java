package demineur.scenes;

import demineur.engine.*;
import demineur.game.*;

public class Arcade extends Scene {
    public Arcade(Context context) {
        super(context); 
    }; 
    
    @Override 
    public void load() {
        addObject(new Grid(context, 15, 13, 13, 30, 30));
    }; 
    
    @Override 
    public void exit() {}; 
    
    @Override 
    public void update(float dt) {
        updateObjects(dt);
    };
}
