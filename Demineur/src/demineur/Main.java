package demineur;

import demineur.engine.Context;
import demineur.engine.*;
import demineur.scenes.*;


public class Main {
    Context context;
    
    public Main() {
        enableVsync();
        SceneManager sceneManager = new SceneManager(context);
        context = new Context("Super Démineur", sceneManager); 
        loadAssets();
        loadObjects(sceneManager);
    };
    
    
    public void enableVsync() {
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.opengl.fbobject", "false");
    }
    
    public void loadAssets() {
        context.loadImage("marwane_std", "src/assets/marwane_std.png");
        context.loadImage("enter", "src/assets/enter.png");
    };
    
    public void loadObjects(SceneManager sceneManager) {
        MainScene mainScene = new MainScene(context); 
        Menu menu = new Menu(context);
        sceneManager.addScene("main", mainScene);
        sceneManager.addScene("menu", menu);
        sceneManager.current = "main"; 
        context.addObject(sceneManager);
    }; 
    
    public static void main(String[] args) {
        new Main(); 
    };
}
