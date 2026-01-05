
package demineur.scenes;

import demineur.engine.*;
import demineur.game.*;

public class Classique extends Scene {
    /*
    Scène du mode de jeu "classique".
    */
    public Classique(Context context) {
        super(context); 
    }; 
    
    @Override
    public void update(float dt) {
         updateObjects(dt);
    }; 
    
    @Override
    public void load(){
        objects.clear();
        addObject(new Grid(context, context.nbBombs, 13, 13, 30, 30, "classique"));
    }; 
    
    @Override
    public void exit(){};
}
