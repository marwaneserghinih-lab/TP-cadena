
package demineur.engine;

public class SceneManager extends GameObject {
    
    java.util.HashMap<String, Scene> scenes; 
    public String current; 
    private long transitionTimer = 0;
    
    public SceneManager(Context context) {
        super(context); 
        scenes = new java.util.HashMap<String, Scene>();        
    }; 
    
    @Override 
    public void update(float dt) {
        scenes.get(current).update(dt); 
    };
    
    public void addScene(String name, Scene scene) {
        scenes.put(name, scene); 
    };
    
    public void transition(String scene, long transitionTime, String transitionType) {
        transitionTimer = transitionTime; 
        
    };
};
