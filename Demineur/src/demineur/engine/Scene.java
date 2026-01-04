package demineur.engine;

public abstract class Scene {
    public java.util.ArrayList<GameObject> objects;
    public Context context;
    
    public Scene(Context context_) {
        context = context_;
        objects = new java.util.ArrayList<GameObject>(); 
    }
    
    public void addObject(GameObject object) {
        objects.add(object); 
    }
    
    public void updateObjects(float dt) {
        for (GameObject o : objects) {
            o.update(dt); 
        }
    }
    
    public abstract void update(float dt);
    public abstract void load();
    public abstract void exit(); 
}
