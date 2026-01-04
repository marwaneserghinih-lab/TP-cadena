package demineur.engine;

public abstract class GameObject {
    /*
    La classe GameObject, comme son nom l'indique, indique la structure 
    d'un objet dans le jeu. Il doit contenir une référence au contexte, ainsi
    qu'une methode update() qui sera apelée chaque frame.
    */
    
    // Référence au contexte.
    public Context context;
    // Flag qui controle si l'objet est actualisé
    protected boolean update_flag; 
    // Flag qui controle si l'objet est encore en vie.
    protected boolean alive_flag; 
    
    public GameObject(Context context_) {
        context = context_;     
        update_flag = true; 
        alive_flag = true;
    };
    
    public void kill() {
        /*
        Supprime l'objet.
        */
        alive_flag = false; 
    };
    
    // Hooks de chargement / déchargement (apelés depuis la Scene).
    public void load() {}; 
    public void exit() {}; 
    
    public abstract void update(float dt); 
};
