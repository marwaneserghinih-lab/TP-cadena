package demineur.scenes;

import demineur.engine.*;
import java.awt.Color;

public class Menu extends Scene {
    public Menu(Context context) {
        super(context);
    };
    
    @Override
    public void exit() {
        
    }
    
    @Override 
    public void load() {
        
    }
    
    @Override 
    public void update(float dt) {
        context.drawRect(new Rect(0, 0, context.getDisplayWidth(), context.getDisplayHeight()), new Color(70, 170, 255));
        context.setPixelated(0.2f);
    }
}
