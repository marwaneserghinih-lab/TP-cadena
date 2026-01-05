package demineur;

import demineur.engine.Context;
import demineur.engine.*;
import demineur.scenes.*;


public class Main {
    Context context;
    
    public Main() {
        enableVsync();
        SceneManager sceneManager = new SceneManager(context);
        context = new Context("Super Démineur"); 
        context.sceneManager = sceneManager;
        sceneManager.context = context;
        loadAssets();
        loadObjects(sceneManager);
    };
    
    
    public void enableVsync() {
        /*
        Active la VSync (meilleure fluidité).
        */
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.opengl.fbobject", "false");
    }
    
    public void loadAssets() {
        /*
        Charge les ressources (images en l'occurence).
        */
        context.loadImage("marwane_std", "src/assets/marwane_std.png");
        context.loadImage("enter", "src/assets/enter.png");
        context.loadImage("title", "src/assets/title.png");
        context.loadImage("btn_down", "src/assets/btn_down.png");
        context.loadImage("btn_up", "src/assets/btn_up.png");
        context.loadImage("classique", "src/assets/classique.png");
        context.loadImage("quitter", "src/assets/quitter.png");
        context.loadImage("arcade", "src/assets/arcade.png");
        context.loadImage("select", "src/assets/select.png");
        context.loadImage("perdu", "src/assets/perdu.png");
        context.loadImage("menu", "src/assets/menu.png");
        context.loadImage("gagne", "src/assets/gagne.png");
        context.loadImage("recommencer", "src/assets/recommencer.png");
        context.loadImage("facile", "src/assets/facile.png");
        context.loadImage("normal", "src/assets/normal.png");
        context.loadImage("difficile", "src/assets/difficile.png");
        context.loadImage("arcade_hud", "src/assets/arcade_hud.png");
        context.loadImages("elements", "src/assets/elements.png", 16, 16);
        context.loadImages("nombres", "src/assets/nombres"+ ".png", 16, 16);
    };
    
    public void loadObjects(SceneManager sceneManager) {
        /*
        Charge les scènes.
        */
        sceneManager.addScene("main", new demineur.scenes.Main(context));
        sceneManager.addScene("menu", new Menu(context));
        sceneManager.addScene("classique", new Classique(context));
        sceneManager.addScene("arcade", new Arcade(context));
        sceneManager.addScene("perdu", new Perdu(context));
        sceneManager.addScene("gagne", new Gagne(context));
        sceneManager.addScene("difficultes", new Difficulte(context));
        sceneManager.current = "main"; 
        context.addObject(sceneManager);
    }; 
    
    public static void main(String[] args) {
        new Main(); 
    };
};
