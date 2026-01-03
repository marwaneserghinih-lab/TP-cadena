package demineur.engine;

public abstract class Scene {
    java.util.ArrayList<GameObject> objects;
    public Context context;
    
    public Scene(Context context_) {
        context = context_;
        objects = new java.util.ArrayList<GameObject>(); 
    }
    
    public void addObject(GameObject object) {
        objects.add(object); 
    }
    
    public void update(float dt) {
        for (GameObject o : objects) {
            o.update(dt); 
        }
    }
    
    public abstract void load();
    public abstract void exit(); 
}
