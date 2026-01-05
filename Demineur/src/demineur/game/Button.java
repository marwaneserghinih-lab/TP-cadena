package demineur.game;

import demineur.engine.*;
public class Button extends GameObject {
    /*
    Classe représentant un boutton, les bouttons étant très similaires entre
    eux, seul "type" permet de les différentier.
    */
    
    // Dimensions d'un boutton.
    public final int WIDTH = 161;
    public final int HEIGHT = 34; 
    
    // type du boutton.
    int type = 0; 
   
    // rectangle du boutton.
    Rect rect;
    
    public Button(Context context, int x, int y, int type_) {
        super(context);
        rect = new Rect(x, y, WIDTH, HEIGHT); 
        type = type_;
    };
    
    
    // chaque boutton est explicite par le nom de l'image assocée.
    
    @Override 
    public void update(float dt) {
        int offset = renderBg();
        
        // on affichera un texte différent en fonction du boutton.
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
            case 3:
                context.drawImage("menu", rect.x, rect.y - offset);
                break;
            case 4:
                context.drawImage("recommencer", rect.x, rect.y - offset);
                break;
            case 5:
                context.drawImage("facile", rect.x, rect.y - offset);
                break;
            case 6:
                context.drawImage("normal", rect.x, rect.y - offset);
                break;
                
            case 7:
                context.drawImage("difficile", rect.x, rect.y - offset);
                break;           
        };
        handleAction();
    }; 
    
    private void handleAction() {
        /*
        Gère l'action du boutton.
        */
        
        // si le boutton est pressé
        if (context.mouseClicked && rect.contains(context.mouseX, context.mouseY)) {
            switch(type) {
                // faire l'action
                case 0:
                    context.sceneManager.transition("difficultes", (long)1.5, "slide");
                    break;
                case 1:
                    context.sceneManager.transition("arcade", (long)1.5, "circle");
                    break;
                case 2:
                    context.quit();
                    break;
                case 3:
                    context.sceneManager.transition("menu", (long)1.5, "blur");
                    break;
                case 4:
                    if (context.currentMode.equals("classique"))
                        context.sceneManager.transition("classique", (long)1.5, "slide");
                    else 
                        context.sceneManager.transition("arcade", (long)1.5, "slide");
                    break;
                case 5:
                    context.sceneManager.transition("classique", (long)1.5, "circle");
                    context.nbBombs = 15;
                    break;
                case 6:
                    context.sceneManager.transition("classique", (long)1.5, "circle");
                    context.nbBombs = 25;
                    break; 
                case 7:
                    context.sceneManager.transition("classique", (long)1.5, "circle");
                    context.nbBombs = 35;
                    break;
                    
            };   
        };
    };
    
    private int renderBg() {
        /*
        Affiche le fond du boutton.
        */
        int offset;
        if (rect.contains(context.mouseX, context.mouseY)) {
            context.drawImage("btn_down", rect.x, rect.y);
            context.hovered = true;
            offset = 0;
        } else {
            context.drawImage("btn_up", rect.x, rect.y);
            offset = 3;
        };
        return offset;
    };
};
