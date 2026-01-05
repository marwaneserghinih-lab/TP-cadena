package demineur.engine;

public abstract class Scene {
    /*
    Une scène est un objet géré par le SceneManager qui peut executer du code 
    via load, update et exit (comme un GameObject classique), mais qui 
    contient également la capacité de contenir d'autres objets et d'actualiser
    contextuellement les objets qu'elle contient selon si elle est active ou 
    non.
    */
    public java.util.ArrayList<GameObject> objects;
    public Context context;
    
    public Scene(Context context_) {
        context = context_;
        objects = new java.util.ArrayList<GameObject>(); 
    };
    
    public void addObject(GameObject object) {
        /*
        Ajoute un objet a la scène.
        object (GameObject):
            l'objet a ajouter.
        */
        objects.add(object); 
    };
    
    public void updateObjects(float dt) {
        /*
        Actualise les objets contenus dans la scène.
        dt (float):
            le delta time.
        */
        for (GameObject o : objects) {
            o.update(dt); 
        };
    };
    
    // Hooks
    public abstract void update(float dt);
    public abstract void load();
    public abstract void exit(); 
};
