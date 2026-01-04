package demineur.game;

import demineur.engine.*;
import java.awt.Color;
public class Button extends GameObject {
    
    public final int WIDTH = 161;
    public final int HEIGHT = 34; 
    
    boolean pressed = false; 
    int type = 0; 
   
    Rect rect;
    
    public Button(Context context, int x, int y, int type_) {
        super(context);
        rect = new Rect(x, y, WIDTH, HEIGHT); 
        type = type_;
    }
    
    @Override 
    public void update(float dt) {
        int offset = renderBg();
        switch (type) {
            case 0:
                context.drawImage("classique", rect.x, rect.y - offset);
                break;
            case 1:
                context.drawImage("arcade", rect.x, rect.y - offset);
                break;
            case 2:
                context.drawImage("quitter", rect.x, rect.y - offset);
                break;
        };
        handleAction();
    }; 
    
    private void handleAction() {
        if (context.mouseClicked && rect.contains(context.mouseX, context.mouseY)) {
            switch(type) {
                case 0:
                    context.sceneManager.transition("classique", (long)2.0, "circle");
                    break;
                case 1:
                    context.sceneManager.transition("arcade", (long)2.0, "circle");
                    break;
                case 2:
                    context.quit();
                    break;
            }   
        }
    }
    
    private int renderBg() {
        int offset;
        if (rect.contains(context.mouseX, context.mouseY)) {
            context.drawImage("btn_down", rect.x, rect.y);
            context.hovered = true;
            offset = 0;
        } else {
            context.drawImage("btn_up", rect.x, rect.y);
            offset = 3;
        }
        return offset;
    }
    
    
    
}
